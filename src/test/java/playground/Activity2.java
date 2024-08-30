package playground;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Activity2 {
    //Write a method that takes first name and last name as paramaeters
    // and returns full name in format LASTNAME, FirstName;

    public static String fullNameGenerator(String firstName, String lastName){
       if((firstName == null || firstName.isEmpty())
               || (lastName == null || lastName.isEmpty()))
           throw new RuntimeException("First Name or last name can't be null or empty");
       lastName = lastName.toUpperCase();
       firstName = firstName.substring(0, 1).toUpperCase()
                   + firstName.substring(1).toLowerCase();
        return lastName+ ", " + firstName ;
    }

    @Test(dataProvider = "positiveTesting")
    public void testGettingFullName(String firstName, String lastName, String expectedFullName){
        String name = fullNameGenerator(firstName, lastName);
        System.out.println(name);
        Assert.assertEquals(name, expectedFullName, "FullName should match the format");
    }

    @DataProvider(name = "positiveTesting")
    public String [][] positiveTesting(){
        String[][] data = {
                {"Muhammad", "Abbas", "ABBAS, Muhammad"},
                {"Saeed", "Khan", "KHAN, Saeed"},
                {"Mike", "Johnson", "JOHNSON, Mike"}
        };
        return data;
    }

    @Test
    public void negativeTesting(){
        try{
            fullNameGenerator(null, null);
            Assert.fail("Test supposed to throw Exception");
        }catch (RuntimeException re){
            Assert.assertTrue(true, "Catch Exception passing the test");
        }
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testNegativeWithExpectedException(){
        fullNameGenerator("", null);
    }

    //Using lambda
    @Test
    public void testNegativeWithAssertTrhow(){
        Assert.assertThrows(RuntimeException.class, () -> {
            fullNameGenerator("", "");
        });
    }
}


