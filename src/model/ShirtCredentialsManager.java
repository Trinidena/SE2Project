package model;

import java.util.List;

import model.shirt.Shirt;

/**
 * Interface for interacting with the Persistence Layer Specifically for
 * managing credentials associated with shirts in a password manager.
 * 
 * @author CS3212
 * @version Spring 2024
 */
public abstract class ShirtCredentialsManager {

	/**
	 * Add a new shirt with the specified credentials to the manager
	 * 
	 * @precondition shirtName != null && !shirtName.isEmpty() && username != null
	 *               && password != null && !getShirtNames().contains(shirtName)
	 * @param shirtName name of the shirt
	 * @param username  username for the shirt
	 * @param password  password for the shirt
	 * 
	 * @return true if shirt added successfully, false if shirt not added
	 *         successfully
	 */
	public abstract boolean addShirt(Shirt shirtName);

	/**
	 * Remove a shirt with the specified name
	 * 
	 * @precondition shirtName != null && getShirtNames().contains(shirtName)
	 * @param shirtName name of the shirt
	 * 
	 * @return true if shirt removed successfully, false if shirt not removed
	 *         successfully
	 */
	public abstract boolean removeShirt(String shirtName);

}
