package MySQL_Queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc_Delete {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// 1...Get a connection to database
			// The format for jdbc : mysql ://hostname:port/databaseName

			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo" + "?useUnicode=true"
					+ "&useJDBCCompliantTimezoneShift=true" + "&useLegacyDatetimeCode=false" + "&serverTimezone=UTC",

					"root", "root");
			System.out.println("Data Base connection succesfuly!!! ");

			// 2.. create statement

			myStmt = myConn.createStatement();

			// 3.. Before the Delete to display the employee"s info
			System.out.println("Before the Deleting::::::::::::::");
			// helper method
			displayEmployee(myConn, "John", "Doe");

			// Delete THE EMPLOYEE
			System.out.println("\nExecuting the delete for :::John Doe\n");

			int rowsAffected = myStmt
					.executeUpdate("delete from employees " + "where last_name='Doe' and first_name='John'");

			// verify updating
			System.out.println("after the delete");

			// helper method
			displayEmployee(myConn, "John", "Doe");

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);

		}

	}

	// Method for

	private static void displayEmployee(Connection myConn, String firstName, String lastName) throws SQLException {

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// Prepare statement

			myStmt = myConn.prepareStatement(
					"select last_name,first_name,email  from employees where last_name=? and first_name=?");

			myStmt.setString(1, lastName);
			myStmt.setString(2, firstName);

			// Execute SQL query

			myRs = myStmt.executeQuery();

			// Process ResultSet

			boolean found = false;
			
			while (myRs.next()) {
				String theLastName = myRs.getString("last_name");
				String theFirstName = myRs.getString("first_name");
				String email = myRs.getString("email");

				System.out.printf("%s,%s,%s\n", theFirstName, theLastName, email);
				found=true;
			}
			
			if(!found){
				System.out.println("Employee NOT found " + firstName + lastName);
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			close(myStmt, myRs);
		}

	}

	//helper method
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}
		if (myStmt != null) {
			myStmt.close();

		}
		if (myConn != null) {
			myConn.close();
		}

	}
//helper method
	private static void close(Statement myStmt, ResultSet myRs) throws SQLException {

		close(null, myStmt, myRs);

	}

}
