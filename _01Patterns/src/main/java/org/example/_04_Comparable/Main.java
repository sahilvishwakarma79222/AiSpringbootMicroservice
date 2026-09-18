package org.example._04_Comparable;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        ArrayList<Employee> al=new ArrayList<>();
        al.add(new Employee(3,"aahil"));
        al.add(new Employee(2,"zharvil"));
        al.add(new Employee(1,"tibha"));
        System.out.println(al);
        System.out.println("after sorting");

        Collections.sort(al);
        System.out.println(al);

    }

}
