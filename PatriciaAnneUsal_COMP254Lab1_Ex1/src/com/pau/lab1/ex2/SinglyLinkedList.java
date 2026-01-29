package com.pau.lab1.ex2;

public class SinglyLinkedList<E> {
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() { return element ; }
        public Node<E> getNext() { return next; }
        public void setNext(Node<E> n) { next = n; }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public SinglyLinkedList() { } //constructs an initially empty list
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
//returns the first element
    public E first() {
        if (isEmpty()) return null;
        return head.getElement();
    }
//returns the last element
    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }
//adds an element to the front of the list
    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (size == 0)
            tail = head;
        size++;
    }
//adds and element to the end of the list
    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty())
            head = newest;
        else
            tail.setNext(newest);
        tail = newest;
        size++;
    }
//removes and returns the first element of the list
    public E removeFirst() {
        if (isEmpty()) return null;
        E answer = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0)
            tail = null;
        return answer;
    }
//produces a string representation of the content of the list
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = head;
        while (walk != null) {
            sb.append(walk.getElement());
            if (walk != tail)
                sb.append(", ");
            walk = walk.getNext();
        }
        sb.append(")");
        return sb.toString();
    }

    public void swapNodes(Node<E> node1, Node<E> node2) {
        if (node1 == null || node2 == null) {
            System.out.println("Cannot swap null nodes");
            return;
        }
        if (node1 == node2) {
            System.out.println("Both nodes are the same");
            return;
        }

        Node<E> prev1 = null;
        Node<E> prev2 = null;
        Node<E> current = head;

        while (current != null && (prev1 == null || prev2 == null)) {
            if (current.getNext() == node1) {
                prev1 = current;
            }
            if (current.getNext() == node2) {
                prev2 = current;
            }
            current = current.getNext();
        }

        boolean node1InList = (node1 == head) || (prev1 != null);
        boolean node2InList = (node2 == head) || (prev2 != null);

        if (!node1InList || !node2InList) {
            System.out.println("One or both nodes are not in the list");
            return;
        }

        if (node1.getNext() == node2) {
            if (prev1 != null) {
                prev1.setNext(node2);
            } else {
                head = node2;
            }
            node1.setNext(node2.getNext());
            node2.setNext(node1);
            if (node2 == tail) {
                tail = node1;
            }
            return;
        }

        if (node2.getNext() == node1) {
            if (prev2 != null) {
                prev2.setNext(node1);
            } else {
                head = node1;
            }
            node2.setNext(node1.getNext());
            node1.setNext(node2);

            if (node1 == tail) {
                tail = node2;
            }
            return;
        }

        Node<E> node1Next = node1.getNext();
        Node<E> node2Next = node2.getNext();

        if (prev1 != null) {
            prev1.setNext(node2);
        } else {
            head = node2;
        }

        if (prev2 != null) {
            prev2.setNext(node1);
        } else {
            head = node1;
        }

        node1.setNext(node2Next);
        node2.setNext(node1Next);

        if (node1 == tail) {
            tail = node2;
        } else if (node2 == tail) {
            tail = node1;
        }
    }

    public Node<E> getNodeAt(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    public static void main(String[] args) {
        SinglyLinkedList<String> list1 = new SinglyLinkedList<String>();
        list1.addLast("A");
        list1.addLast("B");
        list1.addLast("C");
        list1.addLast("D");
        list1.addLast("E");
        System.out.println("List: " + list1);

        Node<String> nodeB = list1.getNodeAt(1);
        Node<String> nodeE = list1.getNodeAt(4);
        list1.swapNodes(nodeB, nodeE);
        System.out.println("List After the Swap: " + list1);
    }
}
