package tek.tdd.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tek.tdd.base.BaseSetup;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUtility extends BaseSetup {

    Logger LOGGER = LogManager.getLogger(DatabaseUtility.class);

    public ResultSet executeQuery(String query){
        try{
            String url = getProperty("db.url");
            String username = getProperty("db.username");
            String password = getProperty("db.password");

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //First Step for UserProfile APi Validation
    public List<Map<String, Object>> getResultFromDBQuery(String query) throws SQLException {
        LOGGER.debug("Executing Query: {} ", query);
        String url = getProperty("db.url");
        String username = getProperty("db.username");
        String password = getProperty("db.password");
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();

            List<Map<String, Object>> data = new ArrayList<>();
            while (resultSet.next()){
                Map<String, Object> row = new HashMap<>();
                for(int col = 1; col <= metaData.getColumnCount(); col++){
                    row.put(metaData.getColumnName(col), resultSet.getObject(col));
                }
                data.add(row);
            }
            LOGGER.debug("Query Result: {} ", data);
            return data;
        }catch (SQLException se){
            throw new RuntimeException(se);
        }finally {
            if(connection != null){
                connection.close();
            }
        }

    }
}
