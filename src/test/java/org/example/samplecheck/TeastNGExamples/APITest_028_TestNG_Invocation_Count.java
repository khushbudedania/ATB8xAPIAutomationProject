package org.example.samplecheck.TeastNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest_028_TestNG_Invocation_Count {

    @Test(invocationCount = 2)
    public void test01(){
        Assert.assertTrue(true);
    }
}
//Invocation_Count means how many times you want to run.