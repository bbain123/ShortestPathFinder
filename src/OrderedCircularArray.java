/**
 * Class that represents a circular array with attributes front, rear, and count
 * @author Brendan Bain 251086487
 * @param <T> the generic type of CellData objects being stored 
 */
public class OrderedCircularArray<T> implements SortedListADT<T> {
	private CellData[] list;
	private int front;
	private int rear;
	private int count;
	
	/**
	 * Constructor that initializes front, rear, count, and list
	 */
	public OrderedCircularArray() {
		front = 1;
		rear = 0;
		count = 0;
		list = (CellData<T>[])new CellData[5];
	} 
	
	/**
	 * Method used to add CellData objects into the list. The list is sorted
	 * in ascending order of CellData values
	 * @param id the id of new CellData being added
	 * @param value the value of new CellData being added
	 */
	public void insert (T id, int value) {
		if (size() == list.length) {
			CellData[] larger = (CellData[])new CellData[list.length *2];

			for (int i = front; i < list.length; i ++) { 	//transfers data from front - end into larger
				larger[i] = list[i];
			}
			for (int i = 0; i < front; i++) {           	//transfers data from beginning - 1 before front to rear of larger
				larger[list.length + i] = list[i];
				rear = list.length + i;
			}
			list = larger;
		}
			
		if (isEmpty()) {
			list[front] = new CellData(id, value);
			rear = front;
			count++;
		}
		
		else {
			int target = front;								//position thats going to be changed
			boolean set = false;
			while (value > list[target].getValue() ) {
				target = (target + 1) % (list.length);
				if (list[target] == null) {
					list[target] = new CellData(id, value);
					rear = target;
					count++;
					set = true;
					break;
				}
			}
			if (!set) {
				rear = (rear + 1) % (list.length);
				int curr = rear;
				while (curr != target) {
					if (curr >= 1) {
						list[curr] = list[curr-1];
						curr--;
					}
					else if (curr == 0) {
						list[curr] = list[list.length-1];
						curr = list.length -1;
					}
				}
				list[target] = new CellData(id, value);
				count++;
			}
		}
	}
	
	/**
	 * Method to get numeric value of CellData with the id specified by id from the ordered list
	 * @param id the id of desired CellData
	 * @return the value stored in CellData. Null if
	 * CellData object is not in the list with the id specified
	 */
	public int getValue(T id) throws InvalidDataItemException {
		int curr = front;
		while (curr <= rear) {
			if (list[curr].getId().equals(id))
				return list[curr].getValue();
			else
				curr = (curr + 1) % list.length;
		}
		throw new InvalidDataItemException("Could not find item at " + id);
	}
	
	/**
	 * Removes CellData with the specified id in the ordered list
	 * @param id the id of CellData to be removed
	 */
	public void remove (T id) throws InvalidDataItemException {
		int curr = front;
		boolean found = false;
		int loopTally = 0;
		
		while (!found & loopTally < count) {			//used to perform linear search for id
			
			if (list[curr].getId().equals(id)) {       
				found = true;
				while (curr != rear) {                   //shifts all data over one index
					list[curr] = list[(curr + 1)%list.length];
					curr = (curr + 1) % list.length;
				}
				list[curr] = null;
				if (rear > 0)
					rear--;
				else
					rear = list.length - 1;
				count--;
			}
			
			else 
				curr = (curr + 1) % list.length;
			loopTally++;
		}
		
		if (!found)
			throw new InvalidDataItemException ("Could not find item at " + id + " to remove");
	}
	
	/**
	 * Changes the numeric value of CellData with specified id to newValue
	 * @param id the id of CellData to be changed
	 * @param value the new value 
	 */
	public void changeValue (T id, int newValue) throws InvalidDataItemException {
		remove(id);
		insert(id, newValue);
	}
	
	/**
	 * removes CellData with the smallest numeric value
	 * @return the id of the CellData being removed
	 */
	public T getSmallest() throws EmptyListException{
		if (isEmpty())
			throw new EmptyListException("empty list");
		else {
			T temp = (T)list[front].getId();
			list[front] = null;
			if (!(count == 1))
				front = (front + 1) % list.length;
			count--;
			return temp;
		}
	}
	
	/**
	 * Checks to see if ordered list is empty
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return count == 0;
	}
	
	/**
	 * gets the number of CellData items in ordered list
	 * @return number of CellData items in ordered list
	 */
	public int size() {
		return count;
	}
	
	/**
	 * gets the position of front of ordered list
	 * @return front value
	 */
	public int getFront() {
		return front;
	}
	
	/**
	 * gets the position of rear of ordered list
	 * @return rear value
	 */
	public int getRear() {
		return rear;
	}
}
