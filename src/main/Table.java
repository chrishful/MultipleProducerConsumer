package main;

public class Table {
    private String course;
    private boolean isEmpty = true;

    public Table() {
    }

    public synchronized void serve(String course) {
        while(!isEmpty){
            try {
                this.wait();
            } catch (InterruptedException ie) {
                ie.getMessage();
            }
        }
        isEmpty = false;
        this.notify();

        this.course = course;
    }

    public synchronized String eat() {
        while (isEmpty) {
            try {
                this.wait();
            } catch (InterruptedException ie) {
                ie.getMessage();
            }

        }
        isEmpty = true;
        this.notify();
        return this.course;
       }
    }
