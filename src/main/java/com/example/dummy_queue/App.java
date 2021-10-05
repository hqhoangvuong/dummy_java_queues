package com.example.dummy_queue;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        QueueInterface<Integer> firstQueue = new ArrayQueue<Integer>();
        QueueInterface<Integer> secondQueue = new ArrayQueue<Integer>();

        for (int i = 0; i <= 5; i++) {
            firstQueue.enqueue(i);
            secondQueue.enqueue(i + 5);
        }

        firstQueue.splice(secondQueue);

        while(!firstQueue.isEmpty()) {  
            System.out.println(firstQueue.dequeue());
        }

        System.out.println( "Hello World!" );
    }
}
