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
 * This class manages shirt credentials, including adding, removing, and
 * updating shirt information. It interacts with a server to perform these
 * operations.
 * 
 * WARNING: This implementation is not threadsafe and must be cleared (using the
 * clear method) after each unit test to ensure test isolation and reliability.
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

	public List<TShirt> getShirts() {
		List<TShirt> shirts;

		String jsonResponse = Server.sendRequest("get shirts,");
		System.out.println(jsonResponse);
		shirts = parseShirtsFromJson(jsonResponse);
		System.out.println(shirts.get(0).getMaterial());

		return shirts;
	}

	@Override
	public boolean addShirt(Shirt shirt) {
		if (shirt == null) {
			throw new IllegalArgumentException(NAME_OF_SHIRT_MUST_NOT_BE_NULL);
		}

		Gson gson = new Gson();
		String jsonShirt = gson.toJson(shirt);
		String requestPayload = "add shirt," + jsonShirt;
		String response = Server.sendRequest(requestPayload);

		return response.equals("true");

	}

	@Override
	public boolean removeShirt(String shirtName) {

		String confirmation = Server.sendRequest("remove shirt," + shirtName);
		return confirmation.equals("true");
	}

	public List<TShirt> parseShirtsFromJson(String json) {
		Gson gson = new Gson();
		Type shirtListType = new TypeToken<List<TShirt>>() {
		}.getType();
		return gson.fromJson(json, shirtListType);
	}

}
