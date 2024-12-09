package org.example.samplecheck.PayLoadManagement;

public class Person_POJO {

        private String name;
        private Integer age;

        public Person_POJO() {
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
//POJO
//POJO, which stands for Plain Old Java Object, is a standard Java class.
// Default Constructor
// Encapsulation
//1) private,
//2) getter and setter

//Restrictions
// POJOs should not extend predefined classes or implement specified interface.

//Benefits of Using POJOs
//Simplicity: POJOs are easy to understand and maintain.
// Reusability: They can be used across different Java programs without restriction.
//Flexibility: POJOs can be easily modified and extended.
//Framework Independence.
