package model.accounts;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Account {
    String userName, hashedPassWord;

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

    Account(String userName, String passWord) {
        this.userName = userName;
        this.hashedPassWord = encrypted(passWord);
    }

    public boolean checkPassword(String checkingPassWord) {
        return encrypted(checkingPassWord).equals(hashedPassWord);
    }

    public void setHashedPassWord(String hashedPassWord) {
        this.hashedPassWord = encrypted(hashedPassWord);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
