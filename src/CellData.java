/**
 * Represents a data type that holds an object and a numeric value
 * @author Brendan Bain 251086487
 * @param <T> generic type of object to be held 
 */
public class CellData<T> {
	private T id;
	private int value;
	
	/**
	 * Constructor for CellData. Initializes id and value
	 * @param theId ID object to be stored
	 * @param theValue numeric value of data
	 */
	public CellData(T theId, int theValue) {
		id = theId;
		value = theValue;
	}
	
	/**
	 * Method to return the numeric value being held
	 * @return the numeric value 
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Method to return the ID object being held
	 * @return the ID object
	 */
	public T getId() {
		return id;
	}
	
	/**
	 * Method to set numeric value
	 * @param newValue the new value being set
	 */
	public void setValue(int newValue) {
		value = newValue;
	}
	
	/**
	 * Method to set ID object
	 * @param newId the new ID object
	 */
	public void setId(T newId) {
		id = newId;
	}
}
