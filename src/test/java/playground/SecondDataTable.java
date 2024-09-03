package playground;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SecondDataTable {

    @Test(dataProvider = "testDataWithObject")
    public void somTesting(String name, Integer expectedLength){
        System.out.println("Hello" +expectedLength);
        Assert.assertEquals(name.length(), expectedLength);
    }

    @DataProvider
    public Object[][] testDataWithObject(){
        return new Object[][]{
            {"Ahmad", 5},
            {"Jamaludin", 9},
            {"May", 3}
        };
    }

    //Using POJo Class here to get the data
    @DataProvider(name = "InputFromPOJO")
    public PoJoPerson[] testWithCustomPOJO(){
        return new PoJoPerson[]{
                new PoJoPerson("Abdul", 5),
                new PoJoPerson("Samadi", 6)
        };
    }

    //Using POJO
    @Test(dataProvider = "InputFromPOJO")
    public void someTestingCustomPOJO(PoJoPerson person){
        Assert.assertEquals(person.getName().length(), person.getLength(), "name length should mathc");
    }
}
