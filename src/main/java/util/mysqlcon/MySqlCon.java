package util.mysqlcon;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class MySqlCon {
	public static String username = "";
	public static String password = "";
	public static String address = "";
	public static String dbName = "";
	private static Connection conn = null;
	private boolean active;


	public static void addVote(String nim, int cand) throws SQLException {
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String query = "insert into voters (hash_nim, vote, waktu)" + " values (MD5(?), ?, ?)";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, nim);
		preparedStmt.setInt(2, cand);
		preparedStmt.setTimestamp(3, timeStamp);

		// execute the prepared statement
		preparedStmt.execute();
	}

	public static boolean checkNIM(String nim) throws SQLException {
		String query = "select 1 from voters where hash_nim = MD5(?)";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, nim);
		ResultSet rs = preparedStmt.executeQuery();

		return rs.next();
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
