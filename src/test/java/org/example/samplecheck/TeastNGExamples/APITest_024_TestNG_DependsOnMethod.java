package org.example.samplecheck.TeastNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest_024_TestNG_DependsOnMethod {

        @Test
        public void serverStartedOk() {
            System.out.println("I will run first");
            Assert.assertTrue(false);
        }

        @Test(dependsOnMethods = "serverStartedOk")
        public void method1() {
            System.out.println("method1");
            Assert.assertTrue(true);
        }

        @Test(dependsOnMethods = "serverStartedOk")
        public void method2() {
            System.out.println("method2");
            Assert.assertTrue(true);
        }
    }
//Dependsonmethods depend on other method.
//if the method is false then the remaining method dosen't run.
