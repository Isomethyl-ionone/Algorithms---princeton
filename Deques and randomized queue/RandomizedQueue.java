/* *****************************************************************************
 *  Name: Evgenia
 *  Date: 07/11/18
 *  Description: RandomizedQueue
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;         // number of elements on queue

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        a = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty()                 // is the randomized queue empty?
    {
        return n == 0;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public int size()                                               // return the number of items on the randomized queue
    {
        return n;
    }

    public void enqueue(Item item)                                  // add the item
    {
        if (item == null) throw new java.lang.IllegalArgumentException();
        if (n == a.length)
            resize(2 * a.length);                    // double size of array if necessary
        a[n++] = item;                                              // add item
    }

    public Item dequeue()                                           // remove and return a random item
    {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        int localrandom = StdRandom.uniform(n);
        Item item = a[localrandom];
        a[localrandom] = a[n - 1];
        a[n - 1] = null;
        n--;

        if (n > 0 && n == a.length / 4)
            resize(a.length / 2);       // shrink size of array if necessary
        return item;
    }

    public Item sample()                                            // return a random item (but do not remove it)
    {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        return a[StdRandom.uniform(n)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }


    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        private final Item[] copyArray;

        public RandomizedQueueIterator() {
            copyArray = (Item[]) new Object[n];
            for (int index = 0; index < n; index++) {
                copyArray[index] = a[index];
            }
            StdRandom.shuffle(copyArray);
            i = 0;
        }

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (copyArray[i] == null) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!hasNext()) throw new NoSuchElementException();
            return copyArray[i++];
        }
    }

    // public String toString() {
    //     String randq = "";
    //     for (Item s : this) {
    //         randq += (String) s;
    //         randq += " ";
    //     }
    //     return randq;
    // }

    public static void main(String[] args)   // unit testing (optional)
    {

        // RandomizedQueue<String> myrq = new RandomizedQueue<String>();
        // myrq.enqueue("1");
        // System.out.println(myrq);
        // myrq.enqueue("2");
        // System.out.println(myrq);
        // myrq.enqueue("3");
        // System.out.println(myrq);
        // myrq.enqueue("4");
        // System.out.println(myrq);
        // System.out.println(myrq.size());
        // myrq.enqueue("5");
        // System.out.println(myrq);
        // System.out.println(myrq.size());
        // myrq.dequeue();
        // System.out.println(myrq);
        // myrq.dequeue();
        // System.out.println(myrq);
    }
}

