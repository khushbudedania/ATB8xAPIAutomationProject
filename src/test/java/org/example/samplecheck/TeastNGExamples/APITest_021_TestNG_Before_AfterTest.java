package org.example.samplecheck.TeastNGExamples;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITest_021_TestNG_Before_AfterTest {

    @BeforeTest
    public void gettoken() {
        System.out.println("1");
        }

    @BeforeTest
    public void getBookingID () {
            System.out.println("2");
        }

    @Test
    public void test_put()
    {
        System.out.println("3");
    }
    @AfterTest
    public void gettoken1() {
        System.out.println("close");
    }

    }
//if u want to run before the test_put() method you write @beforetest(Annotation).