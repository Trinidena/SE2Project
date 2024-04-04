package model.shirt;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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
     * Clears the collection of all shirts.
     */
    public void clear() {
        this.shirts.clear();
    }

    /**
     * Checks if the collection contains a shirt associated with the specified ID.
     * 
     * @param id The ID to check.
     * @return true if a shirt with the specified ID exists, false otherwise.
     */
    public boolean containsKey(int id) {
        return this.shirts.containsKey(id);
    }

    /**
     * Retrieves the shirt associated with the specified ID.
     * 
     * @param id The ID of the shirt to find.
     * @return The shirt if found, null otherwise.
     */
    public Shirt findByKey(int id) {
        return this.shirts.get(id);
    }

    /**
     * Adds a new shirt to the collection. The shirt is indexed by its hash code.
     * 
     * @param newShirt The new shirt to add.
     * @return true if the shirt was added successfully, false if a shirt with the same ID already exists.
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
     * Adds all shirts from a given list to the collection.
     * 
     * @param shirtList The list of shirts to add.
     */
    public void putAll(List<Shirt> shirtList) {
        shirtList.forEach(shirt -> this.shirts.put(shirt.hashCode(), shirt));
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
     * Replaces the details of the shirt associated with the specified ID.
     * 
     * @param id The ID of the shirt to replace.
     * @param newShirt The new shirt details to apply.
     * @return true if the shirt was found and replaced, false otherwise.
     */
    public boolean replaceByKey(int id, Shirt newShirt) {
        if (this.shirts.containsKey(id) && newShirt != null) {
            this.shirts.put(id, newShirt);
            return true;
        }
        return false;
    }

    /**
     * Checks if the collection is empty.
     * 
     * @return true if the collection has no shirts, false otherwise.
     */
    public boolean isEmpty() {
        return this.shirts.isEmpty();
    }

    /**
     * Returns the number of shirts in the collection.
     * 
     * @return The size of the collection.
     */
    public int size() {
        return this.shirts.size();
    }

    /**
     * Retrieves a collection of all shirts.
     * 
     * @return A collection of Shirt objects.
     */
    public Collection<Shirt> values() {
        return this.shirts.values();
    }
}
