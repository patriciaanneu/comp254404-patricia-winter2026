package Ex2;

import Ex1.LinkedBinaryTree;
import Ex1.Position;
import Ex1.Tree;
import java.util.Iterator;

public class TreeHeight {

    public static <E> int printSubHeight(Tree<E> tree, Position<E> p) {

        //track max height of children
        int maxChildHeight = -1;

        Iterator<Position<E>> it = tree.children(p).iterator();

        while (it.hasNext()) {
            Position<E> child = it.next();
            int childHeight = printSubHeight(tree, child);

            if (childHeight > maxChildHeight) {
                maxChildHeight = childHeight;
            }
        }

        //height of current node
        int height;

        if (maxChildHeight == -1) {
            height = 0;
        } else {
            height = maxChildHeight + 1;
        }

        System.out.println(p.getElement() + " - " + height);
        return height;
    }


    //test
    public static void main(String[] args) {
        LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();

        Position<String> A = tree.addRoot("A");
        Position<String> B = tree.addLeft(A, "B");
        Position<String> C = tree.addRight(A, "C");
        Position<String> D = tree.addLeft(B, "D");
        Position<String> E = tree.addRight(B, "E");
        Position<String> F = tree.addLeft(C, "F");
        Position<String> G = tree.addRight(C, "G");

        //check if tree is empty
        if (tree == null || tree.isEmpty()) {
            System.out.println("Tree is empty.");
            return;
        } else {
            printSubHeight(tree, tree.root());
        }
    }
}
