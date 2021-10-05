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
        

        System.out.println("Spliced queue will look like: ");
        while(!firstQueue.isEmpty()) {  
            System.out.print(Integer.toString(firstQueue.dequeue()) + " ");
        }
    }
}
