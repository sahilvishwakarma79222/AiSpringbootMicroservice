package org.example._05_Comparator;

import java.util.Comparator;

public class Employee {

    int id;
    String name;

    public Employee(int id,String name){
        this.id=id;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public static void main(String[] args) {

                        java.util.Comparator<Employee> e1 =new Comparator<Employee>(){
                            @Override
                            public int compare(Employee e1,Employee e2){
                                return e1.name.compareTo(e2.name);
                            }
                        };

    }
}
