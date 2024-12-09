package org.example.samplecheck.TeastNGExamples;

import org.testng.annotations.Test;

public class APITest_022_TestNG_Priority {

    @Test(priority = 1)
    public void t1() {
        System.out.println("1");
    }
    @Test(priority = 3)
    public void t2() {
        System.out.println("2");
    }
    @Test(priority = 2)
    public void t3() {
        System.out.println("3");
    }
}
//they run alphabetically order.
//if you want to set the priority then they can run based on priority.