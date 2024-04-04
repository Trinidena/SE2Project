package model.server;

import com.google.gson.Gson;

import model.shirt.Shirt;

import java.util.Arrays;
import java.util.List;

import model.shirt.Shirt;

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
	private static final String PASSWORD_FOR_SHIRT_MUST_NOT_BE_NULL = "Password for shirt must not be null";
	private static final String USERNAME_FOR_SHIRT_MUST_NOT_BE_NULL = "Username for shirt must not be null";
	private static final String NAME_OF_SHIRT_MUST_NOT_BE_EMPTY = "Name of shirt must not be empty";
	private static final String NAME_OF_SHIRT_MUST_NOT_BE_NULL = "Name of shirt must not be null";
	private static final String NO_SHIRT_WITH_THE_SPECIFIED_NAME_EXISTS = "No shirt with the specified name exists.";

	/**
	 * Retrieves a list of the names for all Shirts with credentials in the password
	 * manager
	 * 
	 * @return list of the names for all Shirts with credentials in the password
	 *         manager
	 */
	@Override
	public List<String> getShirtNames() {
		String namesStr = Server.sendRequest("get shirt names");

		List<String> names = this.splitStringByComma(namesStr);

		return names;
	}

	private List<String> splitStringByComma(String namesStr) {
		return Arrays.asList(namesStr.split(","));
	}

	private ShirtCredentials getShirt(String shirtName) {
		String shirtStr = Server.sendRequest("get shirt," + shirtName);
		List<String> shirtItems = this.splitStringByComma(shirtStr);
		try {
			ShirtCredentials shirt = new ShirtCredentials(shirtItems.get(0), shirtItems.get(1), shirtItems.get(2));
			return shirt;
		} catch (Exception e) {
			throw new IllegalArgumentException("Shirt has incorrect number of fields");
		}
	}

	@Override
	public String getShirtPassword(String shirtName) {
		if (shirtName == null) {
			throw new IllegalArgumentException(NAME_OF_SHIRT_MUST_NOT_BE_NULL);
		}
		ShirtCredentials shirt = this.getShirt(shirtName);
		if (shirt == null) {
			throw new IllegalStateException(NO_SHIRT_WITH_THE_SPECIFIED_NAME_EXISTS);
		} else {
			return shirt.getPassword();
		}
	}

	@Override
	public String getShirtUsername(String shirtName) {
		if (shirtName == null) {
			throw new IllegalArgumentException(NAME_OF_SHIRT_MUST_NOT_BE_NULL);
		}
		ShirtCredentials shirt = this.getShirt(shirtName);
		if (shirt == null) {
			throw new IllegalStateException(NO_SHIRT_WITH_THE_SPECIFIED_NAME_EXISTS);
		} else {
			return shirt.getUsername();
		}
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

}
