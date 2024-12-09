package org.example.samplecheck.TeastNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class APITest_023_TestNG_Groups {

    @Test(groups = {"sanity", "qa", "preprod"}, priority = 1)
    public void sanityRun(){
        System.out.println("Sanity");
        System.out.println("QA");
        assertTrue(true);
    }

    @Test(groups = {"qa","preprod", "reg"})
    public void RegRun(){
        System.out.println("Reg");
        assertTrue(false);
    }

    @Test(groups = {"dev","stage"})
    public void SmokeRun(){
        System.out.println("Smoke");
        assertTrue(false);
    }

    @Test(groups = {"sanity", "qa", "preprod"})
    public void sanityRun1(){
        System.out.println("Sanity");
        System.out.println("QA");
        assertTrue(true);
    }

    @Test(groups = {"qa","preprod", "reg"})
    public void RegRun2(){
        System.out.println("Reg");
        assertTrue(false);
    }

    @Test(groups = {"dev","stage"})
    public void SmokeRun3(){
        System.out.println("Smoke");
        assertTrue(false);
    }
}
//add this one in .xml file.
// <groups>
//           <run>
//        <include name = "qa"></include>
//        <exclude name= "reg"></exclude>
//           </run>
//       </groups>
