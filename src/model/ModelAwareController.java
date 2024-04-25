package model;

/**
 * An interface for controllers that require a reference to the ShirtCredentialsManager.
 * This allows for a separation of concerns where the controller can interact with the
 * model layer through a defined contract.
 * 
 * @author Trinidad Dena
 */
public interface ModelAwareController {
	
	/**
     * Sets the model object (ShirtCredentialsManager) for the controller. This method provides
     * the controller with access to the application's core functionality and data management capabilities.
     *
     * @param manager The ShirtCredentialsManager instance that acts as the model for this controller.
     */
    void setModel(ShirtCredentialsManager manager);
    
    void setUsername(String username);
}

