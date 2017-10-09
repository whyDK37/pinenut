package functionaljava;

import fj.data.Either;

import static fj.data.Either.left;
import static fj.data.Either.right;

public abstract class Tree {
    private Tree() {
    }

    public abstract Either<Empty, Either<Leaf, Node>> toEither();

    public static final class Empty extends Tree {
        public Empty() {
        }

        public Either<Empty, Either<Leaf, Node>> toEither() {
            return left(this);
        }
    }

    public static final class Leaf extends Tree {
        public final int n;

        public Leaf(int n) {
            this.n = n;
        }

        public Either<Empty, Either<Leaf, Node>> toEither() {
            return right(Either.<Leaf, Node>left(this));
        }
    }

    public static final class Node extends Tree {
        public final Tree left;
        public final Tree right;

        public Node(Tree left, Tree right) {
            this.left = left;
            this.right = right;
        }

        public Either<Empty, Either<Leaf, Node>> toEither() {
            return right(Either.<Leaf, Node>right(this));
        }
    }
}