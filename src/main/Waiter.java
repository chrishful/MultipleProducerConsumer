package main;

import java.util.Arrays;
import java.util.Random;

public class Waiter implements Runnable{

    private final static int MAX_WAITER_MILLIS = 4000;
    private final static int N_COURSES = 3;
    private Table[] tables;
    private String waiterName;
    private String[] customerNames;
    private String[][] courses;
    Random rng = new Random();


    public Waiter(Table[] tables, String waiterName, String[] customerNames, String[][] courses){
        this.tables = tables;
        this.waiterName =  waiterName;
        this.customerNames = customerNames;
        this.courses = courses;
    }

    public String[] getCustomerNames() {
        return customerNames;
    }

    @Override
    public void run(){
            for(int i = 0; i < customerNames.length; i++){
                int k = i;
                    Thread rn = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int j = 0; j < N_COURSES; j++){
                            tables[k].serve(courses[k][j]);
                            System.out.println(waiterName + " is serving " + customerNames[k] + " " + courses[k][j]);
                            try {
                                Thread.sleep(rng.nextInt(MAX_WAITER_MILLIS));
                            }catch(InterruptedException ie){ie.getMessage();};
                        }
                    }
                });
                rn.start(); //starts a thread for each customer


        }
    }

    @Override
    public String toString() {
        String s = new String();
        for(int i = 0; i < customerNames.length; i++){
            for(int j = 0; j < 3; j++) {
                s += courses[i][j] + " ";
            }
        }
        return "Waiter{" +
                "tables=" + Arrays.toString(tables) +
                ", waiterName='" + waiterName + '\'' +
                ", customerNames=" + Arrays.toString(customerNames) +
                ", courses=" + s +
                '}';
    }
}
