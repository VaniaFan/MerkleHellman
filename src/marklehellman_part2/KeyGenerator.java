/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marklehellman_part2;

import java.math.BigInteger;
import java.util.Random;

/**
 * KeyGenerator generates and encapsulates the public and private key.
 *
 * @author YutingFan
 */
public class KeyGenerator {

    private SinglyLinkedList publicList;
    private PrivateKey privateList;

    /**
     * Constructor of KeyGenerator with no arguments; Theta(1) for no best case
     * or worse case.
     */
    public KeyGenerator() {
        this.publicList = new SinglyLinkedList();
        this.privateList = new PrivateKey();
    }

    /**
     * Constructor of KeyGenerator with a SinglyLinkedList; Theta(1) for no best
     * case or worse case.
     *
     * @param wList the superincreasing wlist that is needed to generate public
     * and private keys
     */
    public KeyGenerator(SinglyLinkedList wList) {
        generateKey(wList);
    }

    /**
     * A method to generate public and private keys taking wList as its
     * parameter; Theta(n) for no best case or worse case; Pre-condition is to
     * ensure the wList is not empty by checking wListCurrent.getNext() != null.
     *
     * @param wList the list of wi which comes from the main method
     */
    public void generateKey(SinglyLinkedList wList) {
        BigInteger wListSum = new BigInteger("0"), qInteger, rInteger;
        int wSumBitLen, qLen;
        Random random;
        SinglyLinkedList bList;//b is the public key list

        SinglyNode wListCurrent = wList.getHead();
        while (wListCurrent.getNext() != null) {
            wListCurrent = wListCurrent.getNext();
            wListSum = wListSum.add(new BigInteger((String) wListCurrent.getValue()));
            //wListSum is used to calculate a random q that is bigger than wListSum
        }

        wSumBitLen = wListSum.bitLength();
        random = new Random();
        do {
            qInteger = new BigInteger(wSumBitLen, random);
        } while (qInteger.compareTo(wListSum) <= 0);

        qLen = qInteger.bitLength();
        do {
            rInteger = new BigInteger(qLen, random);
        } while (Integer.parseInt(rInteger.gcd(qInteger).toString()) != 1);

        bList = new SinglyLinkedList();
        wListCurrent = wList.getHead();
        while (wListCurrent.getNext() != null) {
            wListCurrent = wListCurrent.getNext();
            wListSum = wListSum.add(new BigInteger((String) wListCurrent.getValue()));

            bList.addObjAtEnd(rInteger.multiply(new BigInteger((String) wListCurrent.getValue())).mod(qInteger) + "");
        }

        this.publicList = bList;
        this.privateList = new PrivateKey(wList, qInteger, rInteger);
    }

    /**
     * Return the public key; Theta(1) for no best case or worse case.
     *
     * @return the public key
     */
    public SinglyLinkedList getPublicList() {
        return this.publicList;
    }

    /**
     * Return the private key; Theta(1) for no best case or worse case.
     *
     * @return the private key
     */
    public PrivateKey getPrivateList() {
        return this.privateList;
    }
}
