package com.pau.lab1.ex3;

public class CircularlyLinkedList<E> implements  Cloneable {

    private static class Node<E> {
        private E element;
        private Node<E> next;
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() { return element; }
        public Node<E> getNext() { return next; }
        public void setNext(Node<E> n) { next = n ;}
    }

    private Node<E> tail = null;
    private int size = 0;
    public CircularlyLinkedList() { }
    public int size() { return  size; }
    public boolean isEmpty() { return size == 0; }

    public E first() {
        if (isEmpty()) return null;
        return tail.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void rotate() {
        if (tail != null)
            tail = tail.getNext();
    }

    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node<E>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (head == tail) tail = null;
        else tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }

    public CircularlyLinkedList<E> clone() throws CloneNotSupportedException {
        CircularlyLinkedList<E> other = (CircularlyLinkedList<E>) super.clone(); //copies the list
        if (size > 0) {
            Node<E> head = tail.getNext();
            Node<E> newHead = new Node<>(head.getElement(), null);
            Node<E> newTail = newHead;
            Node<E> walk = head.getNext(); //copy the remaining nodes
            while (walk != head) {
                Node<E> newest = new Node<>(walk.getElement(), null);
                newTail.setNext(newest);
                newTail = newest;
                walk = walk.getNext();
            }
            newTail.setNext(newHead);
            other.tail = newTail;
        }
        return other;
    }

    public String toString() {
        if (tail == null) return "()";
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = tail;
        do {
            walk = walk.getNext();
            sb.append(walk.getElement());
            if (walk != tail)
                sb.append(", ");
        } while (walk != tail);
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CircularlyLinkedList<String> circularList = new CircularlyLinkedList<String>();
        circularList.addFirst("A");
        circularList.addLast("B");
        circularList.addLast("C");
        System.out.println("Circular List: " + circularList);

        CircularlyLinkedList<String> clonedList = circularList.clone();
        System.out.println("Cloned List: " + clonedList);

        CircularlyLinkedList<String> rotatedClone = clonedList.clone();
        rotatedClone.rotate();
        System.out.println("Rotated Clone: " + rotatedClone);

    }
}
