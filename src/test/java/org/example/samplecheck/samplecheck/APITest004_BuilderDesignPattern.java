package org.example.samplecheck.samplecheck;

public class APITest004_BuilderDesignPattern {

    //change return type of each method as a class type.
    //"this" always points to current /calling object.
    //returning the same to have  same reference.

    public APITest004_BuilderDesignPattern step1(){
        System.out.println("step1 is started");
        System.out.println("step1 is done");
        return this;
    }
    public APITest004_BuilderDesignPattern step2(){
        System.out.println("step2 is started");
        System.out.println("step2 is done");
        return this;
    }
    public APITest004_BuilderDesignPattern step3(String name){
        System.out.println("step3 is started");
        System.out.println("step3 is done");
        return this;
    }

    public static void main(String[] args) {
        APITest004_BuilderDesignPattern bp = new APITest004_BuilderDesignPattern();
        bp.step1().step2().step3("khushbu");
    }
}
