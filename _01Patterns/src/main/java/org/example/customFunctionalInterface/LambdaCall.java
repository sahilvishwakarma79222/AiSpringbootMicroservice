package org.example.customFunctionalInterface;

public class LambdaCall {

    public static void main(String[] args) {
//        CustomFunctionalInterface c=()->"hello";
//        String check = c.check();
//        System.out.println(check);
        CustomFunctionalInterface c=(a ,b)->{
            int d=a+b;
        return "sum of this two variable is "+d;
        };
        String check = c.check(2,5);
        System.out.println(check);
    }
}
