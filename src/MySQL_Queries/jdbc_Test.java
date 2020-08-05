package MySQL_Queries;

import java.sql.*;



public class jdbc_Test {

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
            
			// 2.. Create a statement
			myStmt = myConn.createStatement();

			// 3.. Execute Sql query
			myRs = myStmt.executeQuery("select * from employees"); // table name

			// 4.. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + "," + myRs.getString("first_name"));

			}

		} catch (Exception exc) {
			exc.printStackTrace();

		} finally {
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
