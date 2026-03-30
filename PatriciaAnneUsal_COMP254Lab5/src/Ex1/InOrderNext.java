import Ex1.BinaryTree;
import Ex1.LinkedBinaryTree;
import Ex1.Position;
import java.util.Iterator;

public class InOrderNext {

    public static <E> Position<E> inorderNext(BinaryTree<E> T, Position<E> p) {

        if (p == null) return null;

        //check if r child exists
        if (T.right(p) != null) {
            Position<E> q = T.right(p);
            while (T.left(q) != null) {
                q = T.left(q);
            }
            return q;
        }

        //find next node - up
        Position<E> q = p;
        Position<E> parent = T.parent(q);

        while (parent != null && q == T.right(parent)) {
            q = parent;
            parent = T.parent(q);
        }
        return parent;
    }
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

    System.out.println("Test\n");
    System.out.println();

    Iterator<Position<String>> it = tree.positions().iterator();

    while (it.hasNext()) {
        Position<String> p = it.next();
        Position<String> next = InOrderNext.inorderNext(tree, p);

        System.out.print("inorderNext(" + p.getElement() + ") = ");

        if (next != null) {
            System.out.println(next.getElement());
        } else {
            System.out.println("null");
        }
    }
}