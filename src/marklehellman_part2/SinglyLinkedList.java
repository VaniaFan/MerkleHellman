/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marklehellman_part2;

/**
 * SinglyLinkedList is a list consisting of a series of SinglyNodes and a
 * pointer pointing to the next node.
 *
 * @author YutingFan
 */
public class SinglyLinkedList {

    private SinglyNode head;
    private int length;

    /**
     * Constructor for a SinglyLinkedList with no argument; Theta(1) for no best
     * case or worse case.
     */
    public SinglyLinkedList() {
        this.head = new SinglyNode();
        this.head.setNext(null);
        this.length = 0;
    }

    /**
     * Add a character node containing the String value to the end of the
     * SinglyLinkedList; O(n) indicating worst case, Omega(1) indicating best
     * case.
     *
     * @param value a string to be added to the end the SinglyLinkedList
     */
    public void addObjAtEnd(String value) {
        SinglyNode node = new SinglyNode(value);
        SinglyNode current = this.head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(node);
        node.setNext(null);
        this.length++;
    }

    /**
     * Add a character node containing the String value to the front of the
     * SinglyLinkedList; Theta(1) for no best case or worse case.
     *
     * @param value a string to be added to the front of the SinglyLinkedList
     */
    public void addObjAtFront(String value) {
        SinglyNode node = new SinglyNode(value);
        SinglyNode head = this.head;
        node.setNext(head.getNext());
        this.head.setNext(node);
        this.length++;
    }

    /**
     * Override the toString() in class java.lang.Object; O(n) indicating worst
     * case, Omega(1) indicating best case.
     *
     * @return a String containing the values of the data fields of each node
     */
    @Override
    public String toString() {
        String result = "";
        SinglyNode tmp = this.head;
        int i = 1;
        while (tmp.getNext() != null) {
            tmp = tmp.getNext();
            result += tmp.getValue();
        }
        return result;
    }

    /**
     * Return a string of node values that is segmented by ", "; O(n) indicating
     * worst case, Omega(1) indicating best case; Pre-condition is to ensure
     * the list is not empty by checking tmp.getNext() != null.
     *
     * @return a string of node values that is segmented by ", ". precondition:
     * the list is not empty
     */
    public String toSegmentedString() {
        String result = "";
        SinglyNode tmp = this.head;
        while (tmp.getNext() != null) {
            tmp = tmp.getNext();
            result += tmp.getValue() + ", ";
        }
        return result.substring(0, result.length() - 2);
    }

    /**
     * Returns true if the list is empty false otherwise; Theta(1) for no best
     * case or worse case.
     *
     * @return true if the list empty false otherwise
     */
    public boolean isEmpty() {
        return this.head.getNext() == null;
    }

    /**
     * Counts the number of nodes in the list; Theta(1) for no best case or
     * worse case.
     *
     * @return the number of nodes in the singly linked list, exclusive of dummy
     * head
     */
    public int countNodes() {
        return this.length;
    }

    /**
     * Get the head of this singly linked list; Theta(1) for no best case or
     * worse case.
     *
     * @return the head of this singly linked list
     */
    public SinglyNode getHead() {
        return this.head;
    }

    /**
     * Reverse the SinglyLinkedList; O(n) indicating worst case, Omega(1)
     * indicating best case; Post-condition is to ensure the list is not empty
     * by checking node.getNext()!=null
     *
     * @return the reversed SinglyLinkedList
     */
    public SinglyLinkedList reverse() {
        String[] resultArray = new String[this.countNodes()];
        SinglyLinkedList resultList = new SinglyLinkedList();
        SinglyNode node = this.head;
        int i = 0;
        while (node.getNext() != null) {
            node = node.getNext();
            resultArray[i++] = node.getValue();
        }
        for (i = resultArray.length - 1; i >= 0; i--) {
            resultList.addObjAtEnd(resultArray[i]);
        }
        return resultList;
    }
}
