package MySQL_Queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc_Insert {

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

			// 3.. Insert a new employee in the database

			System.out.println("Inserting  a new  employee to database\n");
			String sqlInsert = "insert into employees values('Wright','Eric','eric@gmail.com',''HR',3300.00)";
			System.out.println("SQL query is " + sqlInsert + "\n");

			// 4...Verify this by getting a list of Employees

			String select = "select * from employees";
			myRs = myStmt.executeQuery(select);

			// 5.. Process the Result Set

			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + " " + myRs.getString("first_name"));

			}

		} catch (Exception exc) {
			exc.printStackTrace();
		} 
		finally {
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

	}

}
