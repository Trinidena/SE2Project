package model.server;

/** Data object supporting the local ShirtCredentialsManager. 
 * Stores credentials for a single Shirt.
 * 
 * @author CS3212
 * @version Spring 2024
 */
public class ShirtCredentials {
    private String name;
    private String username;
    private String password;
    
    /** Create a new ShirtCredentials instance with the specified info.
     * 
     * @precondition name != null &&
     *               username != null &&
     *               password != null
     * @postcondition getName() == name &&
     *                getUsername() == username &&
     *                getPassword() == password
     * 
     * @param name name of the shirt
     * @param username username for the shirt
     * @param password password for the shirt
     */
    public ShirtCredentials(String name, String username, String password) {
        if (name == null) {
            throw new IllegalArgumentException("name of shirt must not be null");
        }
        if (username == null) {
            throw new IllegalArgumentException("username for shirt must not be null");
        }
        if (password == null) {
            throw new IllegalArgumentException("password for shirt must not be null");
        }
        this.name = name;
        this.username = username;
        this.password = password;
    }
    
    /** Return the username for the shirt
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the username for the shirt
     */
    public String getUsername() {
        return this.username;
    }
    
    /** Return the password for the shirt
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the password for the shirt
     */
    public String getPassword() {
        return this.password;
    }
    
    /** Return the name of the shirt
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the name of the shirt
     */
    public String getName() {
        return this.name;
    }
    
}
