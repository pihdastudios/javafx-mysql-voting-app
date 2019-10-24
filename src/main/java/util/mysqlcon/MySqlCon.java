package util.mysqlcon;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;

public class MySqlCon {
	private static Connection conn = null;
	public static String username = "";
	public static String password = "";
	public static String address = "";
	public static String dbName = "";

	private boolean active;

	public static void addVote(String nim, int cand) throws SQLException {
		java.sql.Date startDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		String query = " insert into voters (nim, candidate, date_created)" + " values (?, ?, ?)";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, nim);
		preparedStmt.setInt(2, cand);
		preparedStmt.setDate(3, startDate);

		// execute the prepared statement
		preparedStmt.execute();
	}

	public static void backupDbtoSql(String pDir) {
		try {

			String folderPath = pDir + "\\backup";

			/* Creating Folder if it does not exist */
			File f1 = new File(folderPath);
			f1.mkdir();

			String savePath = "\"" + pDir + "\\backup\\" + "backup.sql\"";

			/* NOTE: Used to create a cmd command */
			String executeCmd = "mysqldump -u" + username + " -p" + password + " --database " + dbName
					+ " -r " + savePath;

			/* NOTE: Executing the command here */
			Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();

			/*
			 * NOTE: processComplete=0 if correctly executed, will contain other values if
			 * not
			 */
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
			conn = DriverManager.getConnection("jdbc:mysql://" + address + "/", username, password);
			Statement statement = conn.createStatement();
			statement.executeUpdate("CREATE DATABASE " + dbName);
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
				conn = DriverManager.getConnection("jdbc:mysql://" + address + "/" + dbName, username,
						password);
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
