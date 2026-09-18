package org.example._04_Comparable;

public class Employee implements  Comparable<Employee>{

    public Employee(int id,String name){
        this.id=id;
        this.name=name;
    }



    int id;
    String name;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

//    public int compareTo(Employee o){
//        return Integer.compare(this.id,o.id);
//    }

    public int compareTo(Employee e){
        return this.name.compareTo(e.name);
    }

}
