package data_structures;

import org.testng.Assert;
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
    root.left.right = new TreeNode<>();
    root.left.right.item = 26;

    root.right.left = new TreeNode<>();
    root.right.left.item = 70;
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


  @Test
  public void searchBSTTest() {
    Assert.assertEquals(searchBST(root, 3), 3);
    Assert.assertEquals(searchBST(root, 50), 50);
    Assert.assertEquals(searchBST(root, 25), 25);
    Assert.assertEquals(searchBST(root, 75), 75);
    Assert.assertEquals(searchBST(root, 26), 26);
    Assert.assertEquals(searchBST(root, 70), 70);
    Assert.assertEquals(searchBST(root, 99), 99);
    Assert.assertEquals(searchBST(root, 998), -1);
  }

  @Test
  public void insertBSTTest() {

  }


  //*********************************************************
  //  树的而一些方法
  //*********************************************************

  private int searchBST(TreeNode<Integer> root, int key) {
    TreeNode<Integer> node = root;
    if (node == null)
      return -1;

    if (node.item == key) {
      return key;
    }
    if (key > (node.item)) {
      return searchBST(node.right, key);
    } else {
      return searchBST(node.left, key);
    }
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

  private class TreeNode<E> {
    E item;
    TreeNode left;
    TreeNode right;
    //红黑树属性
    boolean black = false;
  }


}