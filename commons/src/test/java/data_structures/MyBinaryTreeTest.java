package data_structures;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by why on 4/29/2017.
 */
public class MyBinaryTreeTest {

    private TreeNode<Integer> root;

    @BeforeClass
    public void before() {
        root = new TreeNode();
        root.item = 50;
        root.left = new TreeNode<>();
        root.left.item = 25;
        root.right = new TreeNode<>();
        root.right.item = 75;

        root.left.left = new TreeNode<>();
        root.left.left.item = 3;
        root.right.right = new TreeNode<>();
        root.right.right.item = 99;

    }

    @Test
    public void testOrderTraverse() {
        System.out.println("preOrderTraverse------------");
        preOrderTraverse(root);
        System.out.println("inOrderTraverse------------");
        inOrderTraverse(root);
        System.out.println("postOrderTraverse------------");
        postOrderTraverse(root);
    }

    public void preOrderTraverse(TreeNode<Integer> node) {
        if (node == null)
            return;

        System.out.println(node.item + "->");
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    public void inOrderTraverse(TreeNode<Integer> node) {
        if (node == null)
            return;

        inOrderTraverse(node.left);//中序遍历左子树
        System.out.println(node.item + "->");//显示节点数据，可以更改为其他节点操作
        inOrderTraverse(node.right);//左后中序遍历右子树
    }

    public void postOrderTraverse(TreeNode<Integer> node) {
        if (node == null)
            return;

        postOrderTraverse(node.left);//中序遍历左子树
        postOrderTraverse(node.right);//左后中序遍历右子树
        System.out.println(node.item + "->");//显示节点数据，可以更改为其他节点操作
    }

    @Test
    public void test() {
        int i = find(root, 5);
        System.out.println(i);
    }

    private int find(TreeNode<Integer> root, int i) {
        TreeNode<Integer> node = root;
        if (node == null)
            return -1;

        if (node.item.equals(i)) {
            return i;
        }
        if (i > node.item) {
            return find(node.right, i);
        } else {
            return find(node.left, i);
        }

    }


    public class TreeNode<E> {
        E item;
        TreeNode<E> left;
        TreeNode<E> right;

    }

}