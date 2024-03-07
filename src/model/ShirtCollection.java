package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShirtCollection {

	private final Map<Integer, TShirt> shirts;

	/**
	 * Creates a new collection
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public ShirtCollection() {
		this.shirts = new HashMap<Integer, TShirt>();
	}

	/**
	 * Clears the map of any previous shirt names
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public void clear() {
		this.shirts.clear();
	}

	/**
	 * checks if the map contains a shirt name with the specified key
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param id the id to check
	 * @return true or false if the shirt exists
	 */
	public boolean containsKey(int id) {
		for (TShirt currentBaby : this.shirts.values()) {
			if (currentBaby.hashCode() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds the shirt with the specified key
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param id the key to search by
	 * @return the found shirt or null
	 */
	public TShirt findByKey(int id) {
		for (TShirt currentShirt : this.shirts.values()) {
			if (currentShirt.hashCode() == id) {
				return currentShirt;
			}
		}
		return null;
	}

	/**
	 * Adds a new shirt
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param newBaby the new shirt to put in the map
	 * @return null
	 */
	public boolean put(TShirt newShirt) {
		if (newShirt == null) {
			throw new IllegalArgumentException("Shirt can't be null");
		}
		if (this.shirts.containsKey(newShirt.hashCode())) {
			return false;
		}
		return (this.shirts.put(newShirt.hashCode(), newShirt) == null);
	}

	/**
	 * Adds every shirt in a list to the hashmap
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param babyList the list of babies to iterate through
	 */
	public void putAll(List<TShirt> shirtList) {
		for (TShirt currentShirt : shirtList) {
			this.shirts.put(currentShirt.hashCode(), currentShirt);
		}
	}

	/**
	 * Removes the shirt with the specified key
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param id the id to search for
	 * @return true if it can be removed, or false if not
	 */
	public boolean removeByKey(int id) {

		for (TShirt currentShirt : this.shirts.values()) {
			if (currentShirt.hashCode() == id) {
				return this.shirts.remove(currentShirt.hashCode(), currentShirt);
			}

		}
		return false;
	}

	/**
	 * Checks if the map of babies is empty
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if empty, or false if not
	 */
	public boolean isEmpty() {
		return this.shirts.isEmpty();
	}

	/**
	 * The size of the shirt map
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the size of this.computers
	 */
	public int size() {
		return this.shirts.size();
	}

	/**
	 * Returns all the values of the map
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the values of this.computer
	 */
	public Collection<TShirt> values() {
		return this.shirts.values();
	}
}
