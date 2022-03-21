package edu.umb.cs681.hw07;

public class RunnableConcurrentSingleton implements Runnable {

    public void run(){
        System.out.println("The instance of class ConcurrentSingleton is " + ConcurrentSingleton.getInstance());
    }

    public static void main(String args[]){
        new Thread(new RunnableConcurrentSingleton()).start();
        new Thread(new RunnableConcurrentSingleton()).start();
        new Thread(new RunnableConcurrentSingleton()).start();
    }
}