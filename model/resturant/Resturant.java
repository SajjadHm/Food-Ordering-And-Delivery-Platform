package model.resturant;

import model.enums.ResturantFoodType;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Resturant {
    private String name, location;
    private ResturantFoodType[] foodTypes;

    private final String id;
    private static int idCount;

    static {
        idCount = 1;
    }

    private String encrypted(String input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-128");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 8) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public Resturant(String name, ResturantFoodType[] foodTypes, String location) {
        this.name = name;
        this.foodTypes = foodTypes.clone();
        this.id = encrypted(String.valueOf(idCount));
        this.location = location;
        idCount++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResturantFoodType[] getFoodTypes() {
        return foodTypes;
    }

    public void setFoodTypes(ResturantFoodType[] foodTypes) {
        // TODO: empty foods menu
        this.foodTypes = foodTypes.clone();
    }

    public String getId() {
        return id;
    }

    public static int getIdCount() {
        return idCount;
    }

}
