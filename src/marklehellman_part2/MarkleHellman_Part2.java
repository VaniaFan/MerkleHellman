/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marklehellman_part2;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * This project is to encrypt / decrypt a string with user input using
 * SinglyLinkedList
 *
 * @author YutingFan
 */
public class MarkleHellman_Part2 {

    /**
     * Take in the list of public key, and transfer the input string to
     * encrypted string; Theta(n^2) for best and worst case; Pre-condition is to
     * ensure the list is not empty by checking wListCurrent.getNext() != null.
     *
     * @param publicList the list of public key
     * @param inputStr the input string to be encrypted
     * @return the encrypted string
     */
    static String Encrypt(SinglyLinkedList publicList, String inputStr) {
        String inputBinaryStr, encryptText = "";
        int count, index;
        for (int i = 0; i < inputStr.length(); i++) {
            inputBinaryStr = charToBinary(inputStr.charAt(i));
            count = 0;
            index = 0;
            SinglyNode wListCurrent = publicList.getHead();
            while (wListCurrent.getNext() != null) {
                wListCurrent = wListCurrent.getNext();
                count += Integer.parseInt(wListCurrent.toString()) * Integer.parseInt(Character.toString(inputBinaryStr.charAt(index)));
                index++;
            }

            if (encryptText.equals("")) {
                encryptText = Integer.toString(count);
            } else {
                encryptText = encryptText + " " + Integer.toString(count);
            }
        }
        return encryptText;
    }

    /**
     * Take in the private key consisting of q, r, and the wList, and decrypt
     * the encrypted text; Theta(n^2) for best and worst case; Pre-condition is
     * to ensure the list is not empty by checking node.getNext() != null;
     * pre-condition that wList has at lease one node, head excluded.
     *
     * @param privateKey the private key consisting of q, r, and the wList
     * @param inputStr the encrypted text to be decrypted
     * @return the decrypted string
     */
    static String Decrypt(PrivateKey privateKey, String inputStr) {
        String decryptedText = "", decryptedBinary = "";
        String[] encryptedArray;
        SinglyLinkedList enBinList = new SinglyLinkedList(), wList = privateKey.getWList();
        BigInteger qInteger = privateKey.getQInteger();
        BigInteger rInteger = privateKey.getRInteger();
        BigInteger rModInverse = rInteger.modInverse(qInteger);
        encryptedArray = inputStr.split(" ");
        String partStr;

        if (wList.isEmpty()) {
            //pre-condition that wList has at lease one node, head excluded
            return "0";
        }
        SinglyLinkedList decreaseWList = wList.reverse();
        for (int i = 0; i < encryptedArray.length; i++) {
            decryptedBinary = "";
            partStr = encryptedArray[i];
            BigInteger decryptedInteger = (new BigInteger(partStr)).multiply(rModInverse).mod(qInteger);
//            Iterator wIterator = w.descendingIterator();//reverse w, move large number to head 
            enBinList = new SinglyLinkedList();

            SinglyNode node = decreaseWList.getHead();
            while (node.getNext() != null) {
                node = node.getNext();
                BigInteger tmp = decryptedInteger.subtract(new BigInteger(node.toString()));
                if (tmp.compareTo(new BigInteger("0")) < 0) {
                    enBinList.addObjAtFront("0");
                } else {
                    enBinList.addObjAtFront("1");
                    decryptedInteger = tmp;
                }
            }

            node = enBinList.getHead();
            while (node.getNext() != null) {
                node = node.getNext();
                decryptedBinary += node.getValue();
            }
            decryptedText = decryptedText + Character.toString(binaryToChar(decryptedBinary));
        }
        return decryptedText;
    }

    /**
     * Transfer a character into a binary; Theta(1) for no best case or worse
     * case; Pre-condition is that the ch is not null.
     *
     * @param ch a character to be transferred to binary
     * @return binary of the input character
     */
    static String charToBinary(char ch) {
        String chBinary = Integer.toBinaryString((int) ch);
        chBinary = String.format("%8s", chBinary).replace(' ', '0');

        return chBinary;
    }

    /**
     * Transfer a binary string to ASCII character; Theta(1) for no best case or
     * worse case; Pre-condition is that the binary string is not null.
     *
     * @param binaryStr is a binary string
     * @return ASCII code of the binary string
     */
    static char binaryToChar(String binaryStr) {
        char ch = (char) Integer.parseInt(binaryStr.substring(0, 8), 2);
        return ch;
    }

    /**
     * The main method to test the encryption and decryption.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Enter a string and I will encrypt it as single large integer");
        Scanner input = new Scanner(System.in);
        String inputStr = input.nextLine();
        while (inputStr.length() > 80) {
            System.out.println("Please enter a string with more than 80 characters!");
            input = new Scanner(System.in);
            inputStr = input.nextLine();
        }
        while (inputStr.length() == 0) {
            System.out.println("Please enter a string!");
            input = new Scanner(System.in);
            inputStr = input.nextLine();
        }
        input.close();

        System.out.println("\nClear text:\n" + inputStr);
        System.out.println("\nNumber of clear text bytes = " + inputStr.length() + "\n");

        SinglyLinkedList wList = new SinglyLinkedList();
        wList.addObjAtEnd("2");
        wList.addObjAtEnd("7");
        wList.addObjAtEnd("11");
        wList.addObjAtEnd("21");
        wList.addObjAtEnd("42");
        wList.addObjAtEnd("89");
        wList.addObjAtEnd("180");
        wList.addObjAtEnd("354");
        KeyGenerator key = new KeyGenerator();
        if (!wList.isEmpty()) {
            //precondition: wList is not empty
            key = new KeyGenerator(wList);
        }

        MarkleHellman_Part2 mh = new MarkleHellman_Part2();
        String encryptText = Encrypt(key.getPublicList(), inputStr);
        System.out.println(inputStr + " is encrypted as\n" + encryptText.replace(" ", ""));

        String decryptText = Decrypt(key.getPrivateList(), encryptText);
        System.out.println("\nResult of decryption: ");
        System.out.println(String.format("%-80s", decryptText));
        System.out.println();
    }
}
