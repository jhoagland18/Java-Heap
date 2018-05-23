/* Copyright (C) 2018 Jackson Hoagland - All Rights Reserved
 * You may use and modify this code freely.
*/

//Created by Jackson Hoagland on May 5, 2018
import java.util.Arrays;

/**
 * A priority queue of type Integer.
 * Integers in this priority queue are organized by size, such that lowest items have the highest priority.
 * Enqueue and dequeue is O(log(n)).
 *
 * As the size of the queue grows, the internal capacity is automatically scaled by a factor of two.
 * Starting queue size is 10 elements.
 * The internal capacity does not shrink.
 *
 * Not synchronized. Multiple threads should not modify this object concurrently.
 *
 * This project was done of my own accord, using only Wikipedia's definition of a heap to help me conform to the common heap rules.
 * https://en.wikipedia.org/wiki/Binary_heap
 */

public class Heap {

    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    private int data[];
    private int numElements = 0;

    /**
     * Returns a Heap instance with default capacity
     */
    public Heap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Returns a Heap instance with specified initial capacity.
     *
     * @param initialCapacity the initial capacity for this heap.
     */
    public Heap(int initialCapacity) {
        data = new int[initialCapacity];
    }

    /**
     * Inserts an integer into the heap.
     *
     * @param a the integer to insert into the heap.
     */
    public void put(int a) {
        if(numElements == data.length) {
            resize();
        }

        data[numElements++] = a;

        upHeap();
    }

    /**
     * Removes and returns the highest priority integer in the heap.
     * For this implementation, the lowest integer will be returned.
     *
     * @return the highest priority integer in the heap
     */
    public int poll() {
        return downHeap();
    }

    /**
     * Returns the number of elements in this heap.
     *
     * @return the number of elements in the heap.
     */
    public int getNumElements() {
        return numElements;
    }

    /**
     * Moves the last element in the heap to its proper location to satisfy the heap rules.
     */
    private void upHeap() {
        int currentIndex = numElements - 1;
        int currentValue = data[currentIndex];

        int parentIndex = getParent(currentIndex);
        int parentValue = data[parentIndex];

        while(parentIndex>=0 && currentValue<parentValue) {
            //swap parent and child
            int temp = parentValue;
            data[parentIndex] = currentValue;
            data[currentIndex] = parentValue;

            //update variables control variables
            currentIndex = parentIndex;
            parentIndex = getParent(currentIndex);

            currentValue = data[currentIndex];
            parentValue = data[parentIndex];
        }
    }

    /**
     * Sifts the heap down to re-satisfy the heap rules.
     *
     * @return the first (highest priporty) element in the heap.
     */
    private int downHeap() {
        int toReturn = data[0];

        data[0] = data[numElements - 1];

        //begin down heap
        //start at top
        int currentIndex = 0;
        int currentValue = data[currentIndex];

        int leftChildIndex = getChildLeft(currentIndex);
        int rightChildIndex = getChildRight(currentIndex);

        //populate left and right children if they are not out of bounds
        Integer leftChildValue = numElements > leftChildIndex ? data[leftChildIndex] : null;
        Integer rightChildValue = numElements > rightChildIndex ? data[rightChildIndex] : null;

        //while child has value
        while(leftChildValue != null) {

            //break if parent bigger than both children, or bigger than left and right is empty
            if(currentValue < leftChildValue && (rightChildValue == null || currentValue < rightChildValue)) {
                break;
            }

            //right child empty
            if(rightChildValue == null) {
                //swap current and left
                int temp = currentValue;
                data[currentIndex] = leftChildValue;
                data[leftChildIndex] = currentValue;

                currentIndex = leftChildIndex;
            } else {
                boolean leftIsLarger = true;

                //compare left and right children
                if(rightChildValue>leftChildValue) {
                    leftIsLarger = false;
                }

                if(leftIsLarger) {
                    //swap current and left child
                    int temp = currentValue;
                    data[currentIndex] = rightChildValue;
                    data[rightChildIndex] = currentValue;

                    currentIndex = rightChildIndex;
                } else {
                    //swap current and right child
                    int temp = currentValue;
                    data[currentIndex] = leftChildValue;
                    data[leftChildIndex] = currentValue;

                    currentIndex = leftChildIndex;
                }
            }

            //update children index
            leftChildIndex = getChildLeft(currentIndex);
            rightChildIndex = getChildRight(currentIndex);

            //update children values
            leftChildValue = numElements > leftChildIndex ? data[leftChildIndex] : null;
            rightChildValue = numElements > rightChildIndex ? data[rightChildIndex] : null;
        }
        numElements--;
        return toReturn;
    }


    /**
     * Returns the location of an element's parent based on the index of the child.
     *
     * @param index the index of the element whose parent location you want to identify.
     * @return index of the parent element.
     */
    private int getParent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Returns the location of an element's left child based on the parent's index.
     *
     * @param index is the index of the parent.
     * @return index of the left child.
     */
    private int getChildLeft(int index) {
        return (index * 2) + 1;
    }

    /**
     * Returns the location of an element's left child based on the parent's index.
     *
     * @param index is the index of the right child.
     * @return index of the right child.
     */
    private int getChildRight(int index) {
        return (index * 2) + 2;
    }

    /**
     * Doubles the array size and copies the original array into the copy.
     */
    private void resize() {
        data = Arrays.copyOf(data,data.length*2);
    }

    /**
     * Returns a copy of the underlying array used in the heap implementation.
     *
     * @return a copy of the underlying array used in the heap implementation.
     */
    public int[] getData() {
        return Arrays.copyOf(data,data.length);
    }
}
