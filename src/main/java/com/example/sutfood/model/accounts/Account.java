package com.example.sutfood.model.accounts;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Account {
    String userName, hashedPassWord, firstName, lastName;

    private String encrypted(String input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    Account(String userName, String passWord, String firstName, String lastName, boolean isPasswordHashed) {
        this.userName = userName;
        if (isPasswordHashed) this.hashedPassWord = passWord;
        else this.hashedPassWord = encrypted(passWord);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkPassword(String checkingPassWord) {
        return encrypted(checkingPassWord).equals(hashedPassWord);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashedPassWord() {
        return hashedPassWord;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHashedPassWord(String hashedPassWord) {
        this.hashedPassWord = hashedPassWord;
    }
}
