package ru.stqa.ptf.sandbox;

public class First {
    public static void main(String[] args) {
        printHello("Hello");
        printHello("Hello");
        printHello("Hello");
        int i = 3;
        System.out.println(area(i));
        int b = 4;
        System.out.println(area(i,b));
    }


    public static void printHello(String value) {
        System.out.println(value);
    }

    public static double area(double l) {
        return l * l;
    }

    public static double area(double a, double b) {
        return a * b;
    }
}