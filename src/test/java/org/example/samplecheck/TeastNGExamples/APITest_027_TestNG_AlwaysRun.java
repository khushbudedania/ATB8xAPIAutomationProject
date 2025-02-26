package org.example.samplecheck.TeastNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest_027_TestNG_AlwaysRun {

    @Test
        public void test01(){
        System.out.println(1);
            Assert.assertTrue(false);
        }

        @Test(dependsOnMethods = "test01",alwaysRun = true)
        public void test02(){
            System.out.println(2);
            Assert.assertTrue(true);
        }

        @Test
        public void test03(){
            System.out.println(3);
            Assert.assertTrue(true);
        }
    }
//Always run means every time run even it depends method failed.

