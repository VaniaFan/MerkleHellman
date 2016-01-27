/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marklehellman_part2;

/**
 * This is the class of a SinglyNode which consists of a value field and a
 * pointer to the next node
 *
 * @author YutingFan
 */
public class SinglyNode {

    private String value;
    private SinglyNode next;

    /**
     * Constructor of SinglyNode with no arguments; Theta(1) for no best case or worse case.
     */
    public SinglyNode() {
        this.value = "";
        this.next = null;
    }

    /**
     * Constructor of a SinglyNode with a string in the data field; Theta(1) for no best case or worse case.
     *
     * @param value a string
     */
    public SinglyNode(String value) {
        this.value = value;
        this.next = null;
    }

    /**
     * Constructor of a SinglyNode with a string in the data field and the
     * pointer pointing to the next node; Theta(1) for no best case or worse case.
     *
     * @param value the data field of the SinglyNode
     * @param next pointer pointing to the next node
     */
    public SinglyNode(String value, SinglyNode next) {
        this.value = value;
        this.next = next;
    }

    /**
     * Return the value of a SinglyNode; Theta(1) for no best case or worse case.
     *
     * @return the value of a SinglyNode
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value of a SinglyNode; Theta(1) for no best case or worse case.
     *
     * @param value the value of a SinglyNode
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Return the pointer to the next node; Theta(1) for no best case or worse case.
     *
     * @return the pointer to the next node
     */
    public SinglyNode getNext() {
        return this.next;
    }

    /**
     * Set the pointer to the next node; Theta(1) for no best case or worse case.
     *
     * @param next the pointer to the next node
     */
    public void setNext(SinglyNode next) {
        this.next = next;
    }

    /**
     * Override the toString() in class java.lang.Object; Theta(1) for no best case or worse case.
     *
     * @return the data field of a string in the string
     */
    @Override
    public String toString() {
        return this.value.toString();
    }
}
