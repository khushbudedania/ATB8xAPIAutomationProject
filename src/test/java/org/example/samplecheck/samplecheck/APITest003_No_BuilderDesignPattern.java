package org.example.samplecheck.samplecheck;

public class APITest003_No_BuilderDesignPattern {
    public void step1(){
        System.out.println("Step 1");
    }
    public void step2(){
        System.out.println("Step 2");
    }
    public void step3(String param1){
        System.out.println("Step 3");
    }

    public static void main(String[] args) {
        APITest003_No_BuilderDesignPattern bd = new APITest003_No_BuilderDesignPattern();
        bd.step1();
        bd.step2();
        bd.step3("khushbu");

        //bd.step1().step2().step3("khushbu");
    }
}
