package commonJavaCoding.SingleTonDesignPractice;

public class Employee {


    public static Employee obj;
    private Employee(){

    }
    public static Employee getInstance(){
        if(obj==null){
            obj=new Employee();
        }
        return obj;
    }

    private long id;
    private String name;

}
