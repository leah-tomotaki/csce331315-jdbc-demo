import java.sql.*;

import javax.naming.spi.DirStateFactory.Result;

/*
CSCE 315
9-27-2021 Lab
 */
public class jdbcpostgreSQL {

  //Commands to run this script
  //This will compile all java files in this directory
  //javac *.java
  //This command tells the file where to find the postgres jar which it needs to execute postgres commands, then executes the code
  //Windows: java -cp ".;postgresql-42.2.8.jar" jdbcpostgreSQL
  //Mac/Linux: java -cp ".:postgresql-42.2.8.jar" jdbcpostgreSQL

  //MAKE SURE YOU ARE ON VPN or TAMU WIFI TO ACCESS DATABASE
  public static void main(String args[]) {

    //Building the connection with your credentials
    Connection conn = null;
    String teamName = "<ADD TEAM NAME HERE>"
    String dbName = "csce315331_"+teamName+"_db";
    String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
    dbSetup myCredentials = new dbSetup(); 

    //Connecting to the database
    try {
        conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
     } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
     }

     System.out.println("Opened database successfully");

     try{
       //create a statement object
       Statement createStmt = conn.createStatement();

       //Running a query
       //TODO: update the sql command here
       // Hint: Try using the SERIAL type to auto-generate Primary Keys
       // https://www.postgresqltutorial.com/postgresql-tutorial/postgresql-serial/
       String sqlCreateStatement = "<ADD SQL COMMAND TO CREATE A TABLE HERE>";
       
       // True if a resultSet, false if int 
       // if Result set is available, use stmt.getResultSet() to retreive 
       Boolean isResultSet = createStmt.execute(sqlCreateStatement);
       System.out.println("Table Created. Is ResultSet Available? " + isResultSet);

       
       // This executeUpdate command is useful for updating data
       // This command returns the number of rows updated 
       // Note: If you know your query will not return a result set, you don't need to persist the statement
       int rowsUpdated = conn.createStatement().executeUpdate("<ADD YOUR SQL COMMAND TO INSERT HERE>");
       System.out.println("Rows Updated: " + rowsUpdated);

       //send statement to DBMS
       //This executeQuery command is useful for data retrieval
       ResultSet result = conn.createStatement().executeQuery("SELECT * FROM <TABLE NAME>;");

       //OUTPUT
       //You will need to output the results differently depeninding on which function you use
       System.out.println("--------------------Query Results--------------------");
       while (result.next()) {
        System.out.println(result.getString("<COLUMN 1 NAME>") + " " + result.getString("<COLUMN 2 NAME>"));
       }
       //OR
       // This is just the ResultSet Object and memory location <-- Not helpful 
       System.out.println(result);
   } catch (Exception e){
       e.printStackTrace();
       System.err.println(e.getClass().getName()+": "+e.getMessage());
       System.exit(0);
   }

    //closing the connection
    try {
      conn.close();
      System.out.println("Connection Closed.");
    } catch(Exception e) {
      System.out.println("Connection NOT Closed.");
    }//end try catch
  }//end main
}//end Class
