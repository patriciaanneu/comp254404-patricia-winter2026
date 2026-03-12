package com.pau.ex1;

public class IndexOf {
    public static <E> int indexOf(PositionalList<E> list, Position<E> p) {
        if (p == null) throw new IllegalArgumentException("Position is null");

        int index = 0;
        Position<E> current = list.first();

        while (current != null) {
            if (current == p) {
                return index;
            }
            current = list.after(current);
            index++;
        }
        return -1;
    }

    //test
    public void main(String[] args) {
        PositionalList<String> list = new LinkedPositionalList<>();

        Position<String> p1 = list.addLast("A");
        Position<String> p2 = list.addLast("B");
        Position<String> p3 = list.addLast("C");
        Position<String> p4 = list.addLast("D");

        System.out.println("Index Of(p1) = " + indexOf(list, p1)); //0
        System.out.println("Index Of(p2) = " + indexOf(list, p2)); //1
        System.out.println("Index Of(p3) = " + indexOf(list, p3)); //2
        System.out.println("Index Of(p4) = " + indexOf(list, p4)); //3

        list.remove(p2);
        System.out.println("\nAfter removing p2:");
        System.out.println("Index Of(p1) = " + indexOf(list, p1));
        System.out.println("Index Of(p3) = " + indexOf(list, p3));
        System.out.println("Index Of(p4) = " + indexOf(list, p4));
    }
}
