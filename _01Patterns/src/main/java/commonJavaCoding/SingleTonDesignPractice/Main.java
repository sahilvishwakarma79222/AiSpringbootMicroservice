package commonJavaCoding.SingleTonDesignPractice;

public class Main {


    public static void main(String[] args) {
//        Employee employee=new Employee();
//        Employee employee1=new Employee();
//        System.out.println("emp String is "+employee.toString());
//        System.out.println("emp  hash is : "+employee.hashCode());
//        System.out.println("emp 1 String is "+employee1.toString());
//        System.out.println("emp 1 hash is : "+employee1.hashCode());


//        Employee employee=Employee.getInstance();
//        Employee employee1=Employee.getInstance();
//        System.out.println("emp String is "+employee.toString());
//        System.out.println("emp  hash is : "+employee.hashCode());
//        System.out.println("emp 1 String is "+employee1.toString());
//        System.out.println("emp 1 hash is : "+employee1.hashCode());

//        Employee employee = Employee.getInstance();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                Employee emp1 = Employee.getInstance();
                        System.out.println("emp 1 String is "+emp1.toString());
                        System.out.println("emp 1 hash is : "+emp1.hashCode());
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                Employee emp2 = Employee.getInstance();
                System.out.println("emp 2 String is "+emp2.toString());
                System.out.println("emp 2 hash is : "+emp2.hashCode());
            }
        });

        t1.start();
        t2.start();


    }
}
