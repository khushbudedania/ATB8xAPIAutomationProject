package org.example.samplecheck.TeastNGExamples;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class APITest_026_TestNG_Enabled {

        @Test
        public void test01(){
            Assert.assertTrue(true);
        }

        @Test(enabled = false)
        public void test02(){
            Assert.assertTrue(true);
        }

        @Test
        public void test03(){
            Assert.assertTrue(true);
        }

    }
//Enabled = false means they don't run the test case.
