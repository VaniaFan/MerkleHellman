/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marklehellman_part2;

import java.math.BigInteger;

/**
 * PrivateKey holds the private key, consisting of a superincreasing list,
 * number q that is greater than the sum, and a number r that is in the range
 * [1, q) and is coprime to q.
 *
 * @author YutingFan
 */
public class PrivateKey {

    private SinglyLinkedList wList;
    private BigInteger qInteger;
    private BigInteger rInteger;

    /**
     * Constructor for a PrivateKey class with no argument. Theta(1) for no best
     * case or worse case.
     */
    public PrivateKey() {
        this(new SinglyLinkedList(), null, null);
    }

    /**
     * Constructor of a PrivateKey consisting of a SinglyLinkedList, and two
     * BigIntegers; Theta(1) for no best case or worse case.
     *
     * @param wList The superincreasing SinglyLinkedList
     * @param qInteger the chosen integer q that is greater than the sum of w
     * @param rInteger the random integer r in the range of [1,q)
     */
    public PrivateKey(SinglyLinkedList wList, BigInteger qInteger, BigInteger rInteger) {
        this.wList = wList;
        this.qInteger = qInteger;
        this.rInteger = rInteger;
    }

    /**
     * Return the wList (superincreasing list of w); Theta(1) for no best case
     * or worse case.
     *
     * @return the supreincreasing wList
     */
    public SinglyLinkedList getWList() {
        return this.wList;
    }

    /**
     * Return the integer q; Theta(1) for no best case or worse case.
     *
     * @return the integer q
     */
    public BigInteger getQInteger() {
        return this.qInteger;
    }

    /**
     * Return the integer r; Theta(1) for no best case or worse case.
     *
     * @return the integer r
     */
    public BigInteger getRInteger() {
        return this.rInteger;
    }
}
