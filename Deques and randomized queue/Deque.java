/* *****************************************************************************
 *  Name: Evgenia
 *  Date: 31/10/18
 *  Description: deque
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;    // end of queue

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public Deque() {                             // construct an empty deque
        first = null;
        n = 0;
        last = null;
    }

    public boolean isEmpty() {                 // is the deque empty?

        return n == 0;
    }

    public int size() {                     // return the number of items on the deque

        return n;
    }

    public void addLast(Item item) {         // add the item to the end

        if (item == null) throw new java.lang.IllegalArgumentException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) first = last;
        else {
            last.previous = oldlast;
            oldlast.next = last;
        }
        n++;
    }

    public void addFirst(Item item) {         // add the item to the front

        if (item == null) throw new java.lang.IllegalArgumentException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty()) last = first;
        else {
            first.next = oldfirst;
            oldfirst.previous = first;
        }
        n++;
    }

    public Item removeFirst() {             // remove and return the item from the front

        if (isEmpty()) throw new NoSuchElementException("deque is empty");
        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.previous = null;
        }
        else {
            last = null;
        }
        n--;
        return item;
    }

    public Item removeLast() {              // remove and return the item from the end

        if (isEmpty()) throw new NoSuchElementException("deque is empty");
        Item item = last.item;
        last = last.previous;
        if (last != null) {
            last.next = null;
        }
        else {
            first = null;
        }
        n--;
        return item;
    }

    public Iterator<Item> iterator() { // return an iterator over items in order from front to end

        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public boolean hasPrevious() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public Item previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.previous;
            return item;
        }
    }

    // public String toString() {
    //     String dequeStr = "";
    //     for (Item s : this) {
    //         dequeStr += (String) s;
    //         dequeStr += " ";
    //     }
    //     return dequeStr;
    // }

    public static void main(String[] args)   // unit testing (optional)
    {
        // Deque<String> deque = new Deque<String>();
        // deque.addFirst("1");
        // deque.addFirst("2");
        // deque.addLast("3");
        // deque.addLast("4");
        // deque.addLast("5");
        // deque.addLast("6");
        // deque.addFirst("7");
        // deque.addFirst("8");
        // deque.addFirst("9");
        // System.out.println(deque);
        // System.out.println(deque.size());
        // deque.removeLast();
        // System.out.println("after remove " + deque);
        // System.out.println(deque.size());
    }
}




