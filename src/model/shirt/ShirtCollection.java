package model.shirt;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShirtCollection {

	private final Map<Integer, Shirt> shirts;

	/**
	 * Creates a new collection
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public ShirtCollection() {
		this.shirts = new HashMap<Integer, Shirt>();
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
		for (Shirt currentShirt : this.shirts.values()) {
			if (currentShirt.hashCode() == id) {
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
	public Shirt findByKey(int id) {
		for (Shirt currentShirt : this.shirts.values()) {
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
	public boolean put(Shirt newShirt) {
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
	public void putAll(List<Shirt> shirtList) {
		for (Shirt currentShirt : shirtList) {
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

		for (Shirt currentShirt : this.shirts.values()) {
			if (currentShirt.hashCode() == id) {
				return this.shirts.remove(currentShirt.hashCode(), currentShirt);
			}

		}
		return false;
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
	public boolean replaceByKey(int id, Shirt newShirt) {

		for (Shirt currentShirt : this.shirts.values()) {
			if (currentShirt.hashCode() == id) {

				currentShirt.setHasPocket(newShirt.hasPocket);
				currentShirt.setColor(newShirt.getColor());
				currentShirt.setSize(newShirt.getSize());
				currentShirt.setMaterial(newShirt.getMaterial());
				currentShirt.setSleeveLength(newShirt.getSleeveLength());
				currentShirt.setShoulderWidth(newShirt.getShoulderWidth());
				currentShirt.setBackLength(newShirt.getBackLength());
				currentShirt.setNeckStyle(newShirt.getNeckStyle());
				currentShirt.setShirtText(newShirt.getShirtText());
		
				return true;
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
	public Collection<Shirt> values() {
		return this.shirts.values();
	}
}
