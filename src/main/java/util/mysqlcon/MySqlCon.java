package util.mysqlcon;

import org.apache.commons.lang3.StringUtils;
import pemilukm.teti.GlobalVar;
import util.hash.AES;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.io.*;

import com.opencsv.CSVWriter;

public class MySqlCon {
    public static String username = "";
    public static String password = "";
    public static String address = "";
    public static String dbName = "";
    private static Connection conn = null;


    public static void addVote(String nim, int cand) throws SQLException {
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        String encryptedNIM = AES.encrypt(nim, GlobalVar.AESKey);
        String query = "insert into BALLOTS (NIMHASH, VOTE, WAKTU)" + " values (?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, encryptedNIM);
        preparedStmt.setInt(2, cand);
        preparedStmt.setTimestamp(3, timeStamp);

        //execute prepared statement
        preparedStmt.execute();

        File file = new File("./bak_BALLOTS.csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file, true);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            String[] row = {encryptedNIM, String.valueOf(cand), timeStamp.toString()};
            writer.writeNext(row);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            //Auto-generated catch block
            e.printStackTrace();
        }

        query = "update VOTERS set STATUS = 2 where NIM = ?";
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, nim);
        preparedStmt.execute();

        file = new File("./bak_VOTERS.csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file, true);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            String[] row = {nim, "2"};
            writer.writeNext(row);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            //Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static int cekNIM(String nim) throws SQLException {
        String query = "select * from VOTERS where NIM = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, nim);
        ResultSet rs = preparedStmt.executeQuery();
        //if row exist
        if (rs.next()) {
            int cek = rs.getInt("STATUS");
            System.out.println(nim + " : " + cek);
            return cek;
        } else {
            System.out.println(nim + " Tidak Valid!");
            return 0;
        }
    }

    public static boolean cekConn() throws SQLException {
        return conn.isClosed();
    }

    public static void backupDbtoSql(String pDir) {
        try {
            String savePath = "\"" + pDir + "/backup/" + "backup.sql\"";

            /* NOTE: Used to create a cmd command */
            String executeCmd = "mysqldump -u " + username + " -p " + password + " --database " + dbName
                    + " -r /tmp/bak.sql";

            /* NOTE: Executing the command here */
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
//			runtimeProcess.getOutputStream().write(password.getBytes());
            int processComplete = runtimeProcess.waitFor();

            /*
             * NOTE: processComplete=0 if correctly executed, will contain other values if
             * not
             */
            System.out.println(executeCmd);
            if (processComplete == 0) {
                System.out.println("Backup Complete");
            } else {
                System.out.println("Backup Failure");
            }

        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
    }

    /**
     * Creates a new database
     */
    public static void newDb() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + address + "/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
            Statement statement = conn.createStatement();
            //statement.executeUpdate("CREATE DATABASE " + dbName);
            statement.executeUpdate(String.format("CREATE TABLE %s.voters (HASH_NIM VARCHAR(255) PRIMARY KEY,VOTE INT NOT NULL,WAKTU TIMESTAMP NOT NULL);", dbName));
            System.out.println("Created table at: " + dbName);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public static void newDb(String pUsername, String pPassword, String pAddress, String pDbname) {
        username = pUsername;
        password = pPassword;
        address = pAddress;
        dbName = pDbname;
        newDb();
    }

    /**
     * Connects to a database
     */
    public static void startConnection() {
        if (StringUtils.isNotEmpty(username) || StringUtils.isNotEmpty(password)
                || StringUtils.isNotEmpty(address) || StringUtils.isNotEmpty(dbName)) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://" + address + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username,
                        password);
                System.out.println("DBConnected: " + dbName);
                GlobalVar.connActive = true;
            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

        } else {
            System.out.println("MySql Information Not Complete");
        }
    }

    public static void startConnection(String pUsername, String pPassword, String pAddress, String pDbname) {
        username = pUsername;
        password = pPassword;
        address = pAddress;
        dbName = pDbname;
        startConnection();
    }
}
