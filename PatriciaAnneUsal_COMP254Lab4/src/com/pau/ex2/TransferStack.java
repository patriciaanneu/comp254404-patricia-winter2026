package com.pau.ex2;

public class TransferStack {

    public static <E> void transfer(Stack<E> S, Stack<E> T) {
        while (!S.isEmpty()) {
            T.push(S.pop());
        }
    }

    public static void main(String[] args) {
        ArrayStack<Integer> S = new ArrayStack<>();
        ArrayStack<Integer> T = new ArrayStack<>();

        S.push(1);
        S.push(2);
        S.push(3);
        S.push(4);
        S.push(5);

        System.out.println("Before transfer:");
        System.out.println("S = " + S);   // (5, 4, 3, 2, 1)
        System.out.println("T = " + T);   // ()

        transfer(S,T);

        System.out.println("\nAfter transfer:");
        System.out.println("S = " + S);   // ()
        System.out.println("T = " + T);   // (1, 2, 3, 4, 5)
    }
}
