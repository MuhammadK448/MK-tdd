package tek.tdd.api.test;

import com.mysql.cj.protocol.Resultset;
import org.testng.annotations.Test;

import java.sql.*;

public class DatabaseConnectivityTest {
    /*
     * Step 1: Create Connection
     * Step 2: Create Statement
     * Step 3: Execute Query
     * Step 4: Get Result Set
     * */

    // Step 1:
    /*
     * jdbc = Java Database Connector
     * mysql = Server type
     * 3306 = Port name
     * tek_insurance_app = Database name
     * tek-database- serv.....com = host name
     */
    @Test
    public void databaseConnectionTest(){

        String url = "jdbc:mysql://tek-database-server.mysql.database.azure.com:3306/tek_insurance_app";
        String username = "tek_student_user";
        String password = "FEB_2024";
        try{
            //Create Connection
            Connection connection = DriverManager.getConnection(url, username, password);

            //Step 2:
            Statement statement = connection.createStatement();
            //Step 3:
            ResultSet resultset = statement.executeQuery("select * from tek_insurance_app.primary_person where id = 9276;");
            //Step 4: => loop through each row to get values
            while (resultset.next()){
                System.out.println(resultset.getString("email"));
                System.out.println(resultset.getString("id"));
                System.out.println(resultset.getString("date_of_birth"));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("=======");
            ex.getErrorCode();
        }

    }
}
