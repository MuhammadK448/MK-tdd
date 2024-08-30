package playground;
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
                   + firstName.substring(1);
        return lastName+ ", " + firstName ;

    }

    @Test
    public void getFullName(){
        String name = fullNameGenerator("Muhammad", "Khusravi");
        System.out.println(name);
    }
}


