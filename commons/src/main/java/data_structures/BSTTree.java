package data_structures;

public class BSTTree<E extends Comparable> {
    private TreeNode<E> root;
    int size;
    int modCount;


    /**
     * 插入节点
     *
     * @param key
     * @return
     */
    public boolean deleteBST(E key) {
        if (key == null) return false;

        TreeNode<E> node = innerSearchBST(root, key);
        if (node != null) {
            innerDeleteNode(node);
            return true;
        } else
            return false;
    }

    /**
     * 删除节点方法
     *
     * @param node
     */
    private void innerDeleteNode(TreeNode<E> node) {
        TreeNode<E> parent = node.parent;
//        右子树为空
        if (node.right == null) {
            if (parent.left == node) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        }
//        左子树为空
        else if (node.left == null) {
            if (parent.left == node) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
        }
//        都为空
        else if (node.left == null && node.right == null) {
            if (parent.left == node) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        //都不为空的情况
        else {
            //查找右子树的最小节点
            TreeNode<E> right = node.right;

            TreeNode<E> replaceNode;
//            如果右子树的左节点为空，则使用右子树替换移出节点
            if (right.left == null) {
                replaceNode = right;
            } else {
                TreeNode<E> left = null;
                TreeNode<E> rightParent = right;
                while (left != null) {
                    left = rightParent.left;
                }
                replaceNode = left.parent;
            }


        }

        cleanNode(node);
    }


    private void cleanNode(TreeNode<E> node) {
        node.left = null;
        node.right = null;
        node.parent = null;
        node.item = null;
    }

    /**
     * 插入节点
     *
     * @param key
     * @return
     */
    public boolean insertBST(E key) {
        if (key == null) return false;

        TreeNode<E> node = innerSearchBST(root, key);
        if (node == null) {//查找不成功
            TreeNode<E> newTreeNode = new TreeNode();
            newTreeNode.item = key;
            if (root == null) {
                root = newTreeNode;
            } else {
                innerInsertBST(root, newTreeNode);
            }

            size++;
            modCount++;
            return true;
        } else
            return false;
    }

    /**
     * 插入新节点
     *
     * @param node
     * @param newNode
     */
    private void innerInsertBST(TreeNode<E> node, TreeNode<E> newNode) {
        if (newNode.item.compareTo(node.item) < 0) {
            if (node.left != null) {
                innerInsertBST(node.left, newNode);
            } else {
                node.left = newNode;
                newNode.parent = node;
            }
        } else {
            if (node.right != null) {
                innerInsertBST(node.right, newNode);
            } else {
                node.right = newNode;
                newNode.parent = node;
            }
        }
    }

    private TreeNode<E> innerSearchBST(TreeNode<E> root, E key) {
        TreeNode<E> node = root;
        if (node == null)
            return null;

        if (node.item.equals(key)) {
            return node;
        }

        if (key.compareTo(node.item) > 0) {
            return innerSearchBST(node.right, key);
        } else {
            return innerSearchBST(node.left, key);
        }
    }

    /**
     * 左旋
     *
     * @param node
     */
    private void rotateLeft(TreeNode<E> node) {

    }

    /**
     * 右旋
     *
     * @param node
     */
    private void rotateRight(TreeNode<E> node) {

    }

    private class TreeNode<E> {
        E item;
        int bf;//节点平衡因子
        TreeNode<E> left;
        TreeNode<E> right;

        TreeNode<E> parent;
        //红黑树属性
        boolean black = false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}