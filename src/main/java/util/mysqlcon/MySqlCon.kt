package util.mysqlcon

import org.apache.commons.lang3.StringUtils
import pemilukm.teti.GlobalVar
import util.hash.AES

import javax.swing.*
import java.io.IOException
import java.sql.*
import java.io.*
import java.util.concurrent.ExecutionException

import com.opencsv.CSVWriter

object MySqlCon {
    var username = ""
    var password = ""
    var address = ""
    var dbName = ""
    private var conn: Connection? = null

    @Throws(SQLException::class)
    fun cekConn(): Boolean {
        try {
            val query = "SELECT version()"
            val preparedStmt = conn!!.prepareStatement(query)
            val rs = preparedStmt.executeQuery()
            return false
        } catch (e: Exception) {
            startConnection()
            System.err.println("Connection failed, got an exception! ")
            System.err.println(e.message)
            return true
        }

    }

    @Throws(SQLException::class)
    fun addVote(nim: String, cand: Int) {

        println("Koneksi -> " + cekConn())

        val timeStamp = Timestamp(System.currentTimeMillis())
        val encryptedNIM = AES.encrypt(nim, GlobalVar.AESKey)
        var query = "insert into BALLOTS (NIMHASH, VOTE, WAKTU)" + " values (?, ?, ?)"
        // create the mysql insert preparedstatement
        var preparedStmt = conn!!.prepareStatement(query)
        preparedStmt.setString(1, encryptedNIM)
        preparedStmt.setInt(2, cand)
        preparedStmt.setTimestamp(3, timeStamp)

        //execute prepared statement
        preparedStmt.execute()

        var file = File("./bak_BALLOTS.csv")
        try {
            // create FileWriter object with file as parameter
            val outputfile = FileWriter(file, true)

            // create CSVWriter object filewriter object as parameter
            val writer = CSVWriter(outputfile, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)

            val row = encryptedNIM?.let { arrayOf<String>(it, cand.toString(), timeStamp.toString()) }
            writer.writeNext(row)

            // closing writer connection
            writer.close()
        } catch (e: IOException) {
            //Auto-generated catch block
            e.printStackTrace()
        }

        println("Update $nim status to 2")
        query = "update VOTERS set STATUS = 2 where NIM = ?"
        preparedStmt = conn!!.prepareStatement(query)
        preparedStmt.setString(1, nim)
        preparedStmt.execute()

        file = File("./bak_VOTERS.csv")
        try {
            // create FileWriter object with file as parameter
            val outputfile = FileWriter(file, true)

            // create CSVWriter object filewriter object as parameter
            val writer = CSVWriter(outputfile, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)

            val row = arrayOf(nim, "2")
            writer.writeNext(row)

            // closing writer connection
            writer.close()
        } catch (e: IOException) {
            //Auto-generated catch block
            e.printStackTrace()
        }

    }

    @Throws(SQLException::class)
    fun cekNIM(nim: String): Int {
        val query = "select * from VOTERS where NIM = ?"
        val preparedStmt = conn!!.prepareStatement(query)
        preparedStmt.setString(1, nim)
        val rs = preparedStmt.executeQuery()
        //if row exist
        if (rs.next()) {
            val cek = rs.getInt("STATUS")
            GlobalVar.textNIM = rs.getString("NIM") + " " + rs.getString("NAMA") + " " + rs.getString("JURUSAN") + " " + rs.getInt("ANGKATAN")
            println("$nim status : $cek")
            return cek
        } else {
            println("$nim Tidak Valid!")
            return 0
        }
    }

    fun backupDbtoSql(pDir: String) {
        try {
            val savePath = "\"$pDir/backup/backup.sql\""

            /* NOTE: Used to create a cmd command */
            val executeCmd = ("mysqldump -u " + username + " -p " + password + " --database " + dbName
                    + " -r /tmp/bak.sql")

            /* NOTE: Executing the command here */
            val runtimeProcess = Runtime.getRuntime().exec(executeCmd)
            //			runtimeProcess.getOutputStream().write(password.getBytes());
            val processComplete = runtimeProcess.waitFor()

            /*
             * NOTE: processComplete=0 if correctly executed, will contain other values if
             * not
             */
            println(executeCmd)
            if (processComplete == 0) {
                println("Backup Complete")
            } else {
                println("Backup Failure")
            }

        } catch (ex: IOException) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.message)
        } catch (ex: InterruptedException) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.message)
        }

    }

    /**
     * Creates a new database
     */
    fun newDb() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://$address/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password)
            val statement = conn!!.createStatement()
            //statement.executeUpdate("CREATE DATABASE " + dbName);
            statement.executeUpdate(String.format("CREATE TABLE %s.voters (HASH_NIM VARCHAR(255) PRIMARY KEY,VOTE INT NOT NULL,WAKTU TIMESTAMP NOT NULL);", dbName))
            println("Created table at: $dbName")
        } catch (ex: SQLException) {
            // handle any errors
            println("SQLException: " + ex.message)
            println("SQLState: " + ex.sqlState)
            println("VendorError: " + ex.errorCode)
        }

    }

    fun newDb(pUsername: String, pPassword: String, pAddress: String, pDbname: String) {
        username = pUsername
        password = pPassword
        address = pAddress
        dbName = pDbname
        newDb()
    }

    /**
     * Connects to a database
     */
    fun startConnection() {
        if (StringUtils.isNotEmpty(username) || StringUtils.isNotEmpty(password)
                || StringUtils.isNotEmpty(address) || StringUtils.isNotEmpty(dbName)) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://$address/$dbName?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true", username,
                        password)
                println("DBConnected: $dbName")
                GlobalVar.connActive = true
            } catch (ex: SQLException) {
                // handle any errors
                println("SQLException: " + ex.message)
                println("SQLState: " + ex.sqlState)
                println("VendorError: " + ex.errorCode)
            }

        } else {
            println("MySql Information Not Complete")
        }
    }

    fun startConnection(pUsername: String, pPassword: String, pAddress: String, pDbname: String) {
        username = pUsername
        password = pPassword
        address = pAddress
        dbName = pDbname
        startConnection()
    }
}
