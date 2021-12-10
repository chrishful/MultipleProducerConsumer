package main;

import java.io.*;
import java.util.Scanner;

public class Restaurant {

    public static void main(String[] args) {

        try {
            System.out.println("File name? Please enter filepath/filename.txt, then return: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            int numServers = Integer.parseInt(br.readLine()); // reads in number of waiters
            Scanner wordParser = new Scanner(br);

                String[] waiters = new String[numServers];
                String [][] courses;
                String[] consumers;
                Waiter[] allWaiters = new Waiter[numServers];
                Table[] tables;
                for(int i = 0; i < numServers; i++){         // loops through servers
                    waiters[i] = wordParser.next();          // reads in waiter[i] name
                    String numConsumer = wordParser.next();  // reads in # of waiter[i] customers
                    tables = new Table[Integer.parseInt(numConsumer)];   //initializes # of tables and consumers
                    consumers = new String[Integer.parseInt(numConsumer)];
                    courses = new String[Integer.parseInt(numConsumer)][3];
                    for(int j = 0; j < Integer.parseInt(numConsumer); j++) {  // loops through number of customers
                         consumers[j] = wordParser.next();
                         tables[j] = new Table();
                         Thread cus = new Thread(new Customer(tables[j], consumers[j]));
                         cus.start(); //starts each customer thread
                        for(int k = 0; k < 3; k++) {             //loops through three courses
                            courses[j][k] = wordParser.next();
                        }
                    }
                    allWaiters[i] = new Waiter(tables, waiters[i], consumers, courses);
                }
                    for(int i = 0; i < allWaiters.length; i++) {
                        Thread rn = new Thread(allWaiters[i]);
                        rn.start(); //starts each waiter thread
                    }


        } catch(NumberFormatException nfe){System.err.println("incorrect file format"); System.exit(-99);}
          catch(FileNotFoundException fnfe) {System.err.println("file not found, try again");}
          catch (IOException ioe){System.err.println("Issue with IO, please enter correct file");
        }
    }
}
