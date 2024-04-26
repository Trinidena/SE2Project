package model.shirt;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages a collection of Shirt objects. This class provides functionality to
 * add, remove, and query shirts based on unique identifiers.
 * 
 * @author Trinidad Dena
 */
public class ShirtCollection {

	private final Map<Integer, Shirt> shirts;

	/**
	 * Constructs a new ShirtCollection instance.
	 */
	public ShirtCollection() {
		this.shirts = new HashMap<>();
	}

	/**
	 * Adds a new shirt to the collection. The shirt is indexed by its hash code.
	 * 
	 * @param newShirt The new shirt to add.
	 * @return true if the shirt was added successfully, false if a shirt with the
	 *         same ID already exists.
	 * @throws IllegalArgumentException If the new shirt is null.
	 */
	public boolean put(Shirt newShirt) {
		if (newShirt == null) {
			throw new IllegalArgumentException("Shirt can't be null");
		}
		int id = newShirt.hashCode();
		if (this.shirts.containsKey(id)) {
			return false;
		}
		this.shirts.put(id, newShirt);
		return true;
	}

	/**
	 * Removes the shirt associated with the specified ID from the collection.
	 * 
	 * @param id The ID of the shirt to remove.
	 * @return true if the shirt was removed, false if no such shirt exists.
	 */
	public boolean removeByKey(int id) {
		return this.shirts.remove(id) != null;
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
	 * Removes the shirt with the specified key
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param id the id to search for
	 * @param newShirt the shirt to be replaced
	 */
	public void replaceByKey(int id, Shirt newShirt) {

		for (Shirt currentShirt : this.shirts.values()) {
			if (currentShirt.hashCode() == id) {
				currentShirt.setHasPocket(newShirt.hasPocket());
				currentShirt.setColor(newShirt.getColor());
				currentShirt.setSize(newShirt.getSize());
				currentShirt.setMaterial(newShirt.getMaterial());
				currentShirt.setSleeveLength(newShirt.getSleeveLength());
				currentShirt.setShoulderWidth(newShirt.getShoulderWidth());
				currentShirt.setBackLength(newShirt.getBackLength());
				currentShirt.setNeckStyle(newShirt.getNeckStyle());
				currentShirt.setShirtText(newShirt.getShirtText());
			}
		}
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
