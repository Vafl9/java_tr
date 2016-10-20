package ru.stqa.ptf.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collection {

    public static void main(String[] args) {
        String[] arr = {
                "Java",
                "C#",
                "Python",
                "PHP"
        };


        for (int i = 0 ; i < arr.length; i++)
        {
            System.out.println(arr[i]);
        }

        for (String s : arr)
        {
            System.out.println(s);
        }

        List<String> arrayList = new ArrayList<>();
        arrayList.add("Java");

        arrayList.forEach(System.out::println);

        List<String > ar = Arrays.asList("Java",
                "C#",
                "Python",
                "PHP");



    }
}
