package playground;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UsingTestDAta {

    @Test(dataProvider = "InputData")
    public void tesAddingData(int num1, int num2, int expectedResult){
        int actualResult = num1 + num2;
        System.out.println("Actual Result: " + actualResult);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "InputData")
    private Integer[][] inputData(){
        Integer[][] data ={
                {3, 4, 7},
                {7,8, 15},
                {25, 25, 50}
        };
        return data;
    }
}
