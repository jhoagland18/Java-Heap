/* Copyright (C) 2018 Jackson Hoagland - All Rights Reserved
 * You may use and modify this code freely.
 */

//Created by Jackson Hoagland on May 5, 2018
import java.util.PriorityQueue;
import java.util.Random;

/*
 * This class is demonstrates how to use my Heap class by inserting and then removing 1 million elements.
 */

class Main {
    public static void main(String[] args) {

        int numInserts = 1000000;

        Random r = new Random();

        Heap myHeap = new Heap();

        //insert 1 million elements
        for (int i = 0; i < numInserts; i++) {
            myHeap.put(r.nextInt());
        }

        //remove 1 million elements
        while (myHeap.getNumElements() > 0) {
            myHeap.poll();
        }

        System.out.println("Done.");
    }
}