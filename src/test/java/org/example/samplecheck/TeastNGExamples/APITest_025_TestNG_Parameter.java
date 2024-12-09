package org.example.samplecheck.TeastNGExamples;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class  APITest_025_TestNG_Parameter {

        @Parameters("browser")
        @Test
        void demo1(String value) {
            System.out.println("Browser is " + value);


            if (value.equalsIgnoreCase("chrome")) {
                System.out.println("Start my Testing");
            }
            if (value.equalsIgnoreCase("firefox")) {
                System.out.println("Start my Firefox");
            }
        }
    }
//they can take parameter value from the xml file.

