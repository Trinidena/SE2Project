package model.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.shirt.Shirt;
import model.shirt.TShirt;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * Local implementation of the ShirtCredentialsManager interface WARNING: This
 * implementation is not threadsafe and must be cleared (using the clear method)
 * after each unit test.
 * 
 * @author CS3212
 * @version Spring 2024
 */
public class ShirtCredentialsManager extends model.ShirtCredentialsManager {

	private static final String SHIRT_WITH_THE_SPECIFIED_NAME_ALREADY_EXISTS = "Shirt with the specified name already exists.";
	private static final String USERNAME_FOR_SHIRT_MUST_NOT_BE_NULL = "Username for shirt must not be null";
	private static final String NAME_OF_SHIRT_MUST_NOT_BE_EMPTY = "Name of shirt must not be empty";
	private static final String NAME_OF_SHIRT_MUST_NOT_BE_NULL = "Name of shirt must not be null";
	private static final String NO_SHIRT_WITH_THE_SPECIFIED_NAME_EXISTS = "No shirt with the specified name exists.";

	private List<String> splitStringByComma(String namesStr) {
		return Arrays.asList(namesStr.split(","));
	}

	public List<TShirt> getShirts() {
		List<TShirt> shirts;
		try {
			String jsonResponse = Server.sendRequest("get shirts,");
			System.out.println(jsonResponse);
			shirts = parseShirtsFromJson(jsonResponse);
			System.out.println(shirts.get(0).getMaterial());
		} catch (Exception e) {
			throw new IllegalArgumentException("Shirt has incorrect number of fields", e);
		}
		return shirts;
	}

	@Override
	public boolean addShirt(Shirt shirt) {
		if (shirt == null) {
			throw new IllegalArgumentException(NAME_OF_SHIRT_MUST_NOT_BE_NULL);
		}
		if (shirt.getName().isEmpty()) {
			throw new IllegalArgumentException(NAME_OF_SHIRT_MUST_NOT_BE_EMPTY);
		}
		Gson gson = new Gson();
		String jsonShirt = gson.toJson(shirt);
		String requestPayload = "add shirt," + jsonShirt;
		String response = Server.sendRequest(requestPayload);
		if (response.equals("name already exists")) {
			throw new IllegalStateException(SHIRT_WITH_THE_SPECIFIED_NAME_ALREADY_EXISTS);
		} else {
			return response.equals("true");
		}
	}

	@Override
	public boolean removeShirt(String shirtName) {
		if (shirtName == null) {
			throw new IllegalArgumentException(NAME_OF_SHIRT_MUST_NOT_BE_NULL);
		}
		String confirmation = Server.sendRequest("remove shirt," + shirtName);
		return confirmation.equals("true");
	}

	@Override
	public boolean updateShirt(String shirtName, String username, String password) {
		if (shirtName == null) {
			throw new IllegalArgumentException(NAME_OF_SHIRT_MUST_NOT_BE_NULL);
		}
		if (shirtName.isEmpty()) {
			throw new IllegalArgumentException(NAME_OF_SHIRT_MUST_NOT_BE_EMPTY);
		}
		if (username == null) {
			throw new IllegalArgumentException(USERNAME_FOR_SHIRT_MUST_NOT_BE_NULL);
		}
		return true;
	}

	public List<TShirt> parseShirtsFromJson(String json) {
		Gson gson = new Gson();
		Type shirtListType = new TypeToken<List<TShirt>>() {
		}.getType();
		return gson.fromJson(json, shirtListType);
	}

}