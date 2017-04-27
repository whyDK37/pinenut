package HackerRank.data_structures.LinkedLists;

/**
 * Created by why on 4/27/2017.
 */
public class PrintTheElementsofaLinkedList {

    public static void main(String[] args) {


    }

    /*
  Print elements of a linked list on console
  head pointer input could be NULL as well for empty list
  Node is defined as
  class Node {
     int data;
     Node next;
  }
*/

// This is a "method-only" submission.
// You only need to complete this method.

    void Print(Node head) {
        Node currentNode = head;
        for (; ; ) {
            if (currentNode != null) {
                System.out.println(head.data);
                currentNode = currentNode.next;
            } else {
                break;
            }
        }

    }

    class Node {
        int data;
        Node next;
    }
}
