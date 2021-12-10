package main;
import java.util.Random;

public class Customer implements Runnable {
    private static final int MAX_CUSTOMER_MILLIS = 4000;
    private Table table;
    private String customerName;

    Random rng = new Random();

    public Customer(Table table, String customerName) {
        this.table = table;
        this.customerName = customerName;
    }


    @Override
    public void run() {
        for(int i = 0; i < 3; i++){
            String s = this.table.eat();
            System.out.println(customerName + " is eating " + s);
            try {
                Thread.sleep( rng.nextInt(MAX_CUSTOMER_MILLIS));
            } catch (InterruptedException ie) { ie.getMessage();
            }

        }
    }
}