package com.example.test;

public class MyClass {

    public static void main(String[] args) {
        for (int i = 0; i < 1024; i++) {
            System.out.println(" <dimen name=\"lay_px" +i+
                    "\">" + i / 2f +
                    "dp" +
                    "</dimen>");
        }

        for (int i = 12; i < 200; i++) {
            System.out.println(" <dimen name=\"lay_sp" +i+
                    "\">" + i / 2f +
                    "sp" +
                    "</dimen>");
        }

    }
}
