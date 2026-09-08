package org.example.SingleTonClass;

public class SingleToneClass {

    public static SingleToneClass obj;
    private SingleToneClass(){
        if(obj==null){
            obj=new SingleToneClass();
        }
        else{
            obj=obj;
        }
    }
}
