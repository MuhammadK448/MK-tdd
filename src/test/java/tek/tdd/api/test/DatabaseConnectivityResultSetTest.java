package tek.tdd.api.test;

import org.testng.annotations.Test;
import tek.tdd.api.models.AccountResponse;
import tek.tdd.base.ApiTestsBase;
import tek.tdd.utility.DatabaseUtility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnectivityResultSetTest extends ApiTestsBase {
    @Test
    public void convertResultSetToMap() throws SQLException {
        DatabaseUtility dbUtility = new DatabaseUtility();
        String query = "select id, email, first_name, last_name from tek_insurance_app.primary_person order by id desc limit 1;";
        ResultSet result = dbUtility.executeQuery(query);
        ResultSetMetaData metaData = result.getMetaData();
        List<Map<String, Object>> data = new ArrayList<>();

        while (result.next()){
            Map<String, Object> row = new HashMap<>();
            for(int column = 1; column <= metaData.getColumnCount(); column++)
            {
                row.put(metaData.getCatalogName(column), result.getObject(column));
            }
            data.add(row);
        }
        System.out.println(data);
    }

    //This is easier but for specific queries
    //While Map one is generic, and we can use it for different queries by just changing the query
    @Test
    public void convertResultSetToObject() throws SQLException {
        DatabaseUtility dbUtility = new DatabaseUtility();
        String query = "select id, email, first_name, last_name from tek_insurance_app.primary_person order by id desc limit 5;";
        ResultSet result = dbUtility.executeQuery(query);
        ResultSetMetaData metaData = result.getMetaData();

        List<AccountResponse> data = new ArrayList<>();

        while (result.next()){
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setId(result.getInt("id"));
            accountResponse.setEmail(result.getString("email"));
            accountResponse.setFirstName(result.getString("first_name"));

            data.add(accountResponse);
        }
        System.out.println(data);

    }
}
