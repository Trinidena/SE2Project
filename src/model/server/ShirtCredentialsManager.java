package model.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.shirt.Shirt;
import model.shirt.TShirt;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

/**
 * Provides a local implementation of the ShirtCredentialsManager interface.
 * This class manages shirt credentials, including adding, removing, and updating shirt
 * information. It interacts with a server to perform these operations.
 * 
 * WARNING: This implementation is not threadsafe and must be cleared (using the clear method)
 * after each unit test to ensure test isolation and reliability.
 * 
 * @author Trinidad Dena
 * @version Spring 2024
 */
public class ShirtCredentialsManager extends model.ShirtCredentialsManager {

    private static final String SHIRT_WITH_THE_SPECIFIED_NAME_ALREADY_EXISTS = "Shirt with the specified name already exists.";
    private static final String NAME_OF_SHIRT_MUST_NOT_BE_EMPTY = "Name of shirt must not be empty";
    private static final String NAME_OF_SHIRT_MUST_NOT_BE_NULL = "Name of shirt must not be null";
    private Gson gson;
    private String jsonResponse;
    private List<TShirt> shirts;

    /**
     * Retrieves a list of shirts from the server.
     * 
     * @return A list of TShirt objects.
     * @throws IllegalArgumentException If the server response is not correctly formatted.
     */
    public List<TShirt> getShirts() {
        try {
            this.jsonResponse = Server.sendRequest("get shirts,");
            this.shirts = this.parseShirtsFromJson(this.jsonResponse);
        } catch (Exception e) {
            throw new IllegalArgumentException("Shirt has incorrect number of fields", e);
        }
        return this.shirts;
    }

    /**
     * Adds a new shirt to the server.
     * 
     * @param shirt The Shirt object to add.
     * @return true if the shirt was successfully added, false otherwise.
     * @throws NullPointerException If the shirt object is null.
     * @throws IllegalArgumentException If the shirt's name is empty.
     * @throws IllegalStateException If a shirt with the specified name already exists.
     */
    @Override
    public boolean addShirt(Shirt shirt) {
        Objects.requireNonNull(shirt, NAME_OF_SHIRT_MUST_NOT_BE_NULL);
        if (shirt.getName().isEmpty()) {
            throw new IllegalArgumentException(NAME_OF_SHIRT_MUST_NOT_BE_EMPTY);
        }
        this.gson = new Gson();
        String jsonShirt = this.gson.toJson(shirt);
        String requestPayload = "add shirt," + jsonShirt;
        String response = Server.sendRequest(requestPayload);
        if (response.equals("name already exists")) {
            throw new IllegalStateException(SHIRT_WITH_THE_SPECIFIED_NAME_ALREADY_EXISTS);
        } else {
            return response.equals("true");
        }
    }

    /**
     * Removes a shirt by name from the server.
     * 
     * @param shirtName The name of the shirt to remove.
     * @return true if the shirt was successfully removed, false otherwise.
     * @throws NullPointerException If the shirt name is null.
     */
    @Override
    public boolean removeShirt(String shirtName) {
        Objects.requireNonNull(shirtName, NAME_OF_SHIRT_MUST_NOT_BE_NULL);
        String confirmation = Server.sendRequest("remove shirt," + shirtName);
        return confirmation.equals("true");
    }

    /**
     * Updates a shirt's information on the server.
     * Currently, this method's implementation is a placeholder and always returns true.
     * 
     * @param shirt The Shirt object with updated information.
     * @return true.
     */
    @Override
    public boolean updateShirt(Shirt shirt) {
        this.jsonResponse = Server.sendRequest("update shirt,");
        return true;
    }

    /**
     * Parses a JSON string into a list of TShirt objects.
     * 
     * @param json The JSON string to parse.
     * @return A list of TShirt objects.
     */
    public List<TShirt> parseShirtsFromJson(String json) {
        this.gson = new Gson();
        Type shirtListType = new TypeToken<List<TShirt>>() { }.getType();
        return this.gson.fromJson(json, shirtListType);
    }
}
