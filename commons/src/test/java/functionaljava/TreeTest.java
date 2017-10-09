package functionaljava;

import fj.data.Either;
import functionaljava.Tree.Empty;
import functionaljava.Tree.Leaf;
import functionaljava.Tree.Node;
import org.junit.Test;

import static java.lang.Math.max;
import static org.testng.Assert.*;

public class TreeTest {


    static public int depth(Tree t) {
        for (Empty e : t.toEither().left())
            return 0;
        for (Either<Leaf, Node> ln : t.toEither().right()) {
            for (Leaf leaf : ln.left())
                return 1;
            for (Node node : ln.right())
                return 1 + max(depth(node.left), depth(node.right));
        }
        throw new RuntimeException("Inexhaustible pattern match on tree");
    }

    static public boolean inTree(Tree t, int value) {
        for (Empty e : t.toEither().left())
            return false;
        for (Either<Leaf, Node> ln : t.toEither().right()) {
            for (Leaf leaf : ln.left())
                return value == leaf.n;
            for (Node node : ln.right())
                return inTree(node.left, value) | inTree(node.right, value);
        }
        return false;
    }

    static public int occurrencesIn(Tree t, int value) {
        for (Empty e : t.toEither().left())
            return 0;
        for (Either<Leaf, Node> ln : t.toEither().right()) {
            for (Leaf leaf : ln.left())
                if (value == leaf.n) return 1;
            for (Node node : ln.right())
                return occurrencesIn(node.left, value) + occurrencesIn(node.right, value);
        }
        return 0;
    }

    @Test
    public void more_elaborate_searchp_test() {
        Tree t = new Node(new Node(new Node(new Node(
                new Node(new Leaf(4), new Empty()),
                new Leaf(12)), new Leaf(55)),
                new Empty()), new Leaf(4));
        assertTrue(inTree(t, 55));
        assertTrue(inTree(t, 4));
        assertTrue(inTree(t, 12));
        assertFalse(inTree(t, 42));
    }

    @Test
    public void multi_branch_tree_test() {
        Tree t = new Node(new Node(new Node(new Leaf(4),
                new Node(new Leaf(1), new Node(
                        new Node(new Node(new Node(
                                new Node(new Node(new Leaf(10), new Leaf(0)),
                                        new Leaf(22)), new Node(new Node(
                                new Node(new Leaf(4), new Empty()),
                                new Leaf(101)), new Leaf(555))),
                                new Leaf(201)), new Leaf(1000)),
                        new Leaf(4)))),
                new Leaf(12)), new Leaf(27));
        assertEquals(12, depth(t));
        assertTrue(inTree(t, 555));
        assertEquals(3, occurrencesIn(t, 4));
    }
}