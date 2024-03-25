package model;

import java.util.List;

/** Interface for interacting with the Persistence Layer
 * Specifically for managing credentials associated with shirts in a password manager.
 * 
 * @author CS3212
 * @version Spring 2024
 */
public abstract class ShirtCredentialsManager {
    
    /** Retrieves a list of the names for all shirts with credentials in the password manager
     * 
     * @return list of the names for all shirts with credentials in the password manager
     */
    public abstract List<String> getShirtNames();
    
    /** Return the password for a specified shirt
     * 
     * @precondition shirtName != null && getShirtNames().contains(shirtName)
     * @param shirtName name of the shirt
     * 
     * @return password of the shirt if getShirtNames().contains(shirtName)
     *         null if !getShirtNames().contains(shirtName)
     */
    public abstract String getShirtPassword(String shirtName);

    /** Return the username for a specified shirt
     * 
     * @precondition shirtName != null && getShirtNames().contains(shirtName)
     * @param shirtName name of the shirt
     * 
     * @return username of the shirt if getShirtNames().contains(shirtName)
     *         null if !getShirtNames().contains(shirtName)
     */
    public abstract String getShirtUsername(String shirtName);

    /** Add a new shirt with the specified credentials to the manager
     * 
     * @precondition shirtName != null && !shirtName.isEmpty() && username != null && password != null && !getShirtNames().contains(shirtName)
     * @param shirtName name of the shirt
     * @param username username for the shirt
     * @param password password for the shirt
     * 
     * @return true if shirt added successfully, false if shirt not added successfully
     */
    public abstract boolean addShirt(String shirtName, String username, String password);
    
    /** Remove a shirt with the specified name
     * 
     * @precondition shirtName != null && getShirtNames().contains(shirtName)
     * @param shirtName name of the shirt
     * 
     * @return true if shirt removed successfully, false if shirt not removed successfully
     */
    public abstract boolean removeShirt(String shirtName);

    /** Update an existing shirt with the specified credentials
     * 
     * @precondition shirtName != null && !shirtName.isEmpty() && username != null && password != null && getShirtNames().contains(shirtName)
     * @param shirtName name of the shirt
     * @param username username for the shirt
     * @param password password for the shirt
     * 
     * @return true if shirt updated successfully, false if shirt not updated successfully
     */
    public abstract boolean updateShirt(String shirtName, String username, String password);

}
