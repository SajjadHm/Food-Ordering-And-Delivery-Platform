package model.resturant;

import model.enums.ResturantFoodType;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Resturant {
    private String name, location;
    private ResturantFoodType[] foodTypes;

    private final String id;
    private static int idCount;

    static {
        idCount = 0;
    }

    private String getID(String seed) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(seed.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 8) {
                hashText = "0" + hashText;
            }
            return hashText.substring(0, 8);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }


    public Resturant(String name, ResturantFoodType[] foodTypes, String location) {
        this.name = name;
        this.foodTypes = foodTypes.clone();
        this.id = getID(String.valueOf(idCount));
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
