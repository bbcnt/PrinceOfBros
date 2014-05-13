
package game.logic.modstack;

public class ModificationStack implements IModificationStack {
	
	private int unitNumber;
	private int unitSize;
	
	// Quick accessors
	private int size;
	private int capacity;
	
	private int pt_row, pt_col, pt_pos;
	
	private IModificationTransaction[][] transactions;
	
	public ModificationStack(int unitNumber) {
		init(unitNumber, 0);
	}
	public ModificationStack(int unitNumber, int unitSize) {
		init(unitNumber, unitSize);
	}
	private void init(int unitNumber, int unitSize) {
		this.unitNumber = unitNumber > 0 ? unitNumber : 5;
		this.unitSize 	 = unitSize > 0 ? unitSize : 100;
		
		size 		= 0;
		capacity = unitNumber * unitSize;
		
		resetPointer();
		
		transactions = new IModificationTransaction[unitNumber][unitSize];
	}
	
	/**
	 * For test only
	 * 
	 * TODO remove this methods when test ok.
	 */
	@Deprecated
	public void printStatus() {
		
		for (int i = 0 ; i < transactions.length ; i++) {
			for(int j = 0 ; j < transactions[i].length ; j++) {
				if (transactions[i][j] == null) {
					System.out.print("-");
				}
				else {
					System.out.print("+");
				}
				System.out.print(" ");
			}
			
			System.out.println();
		}
		
		System.out.println("Current pointer :");
		System.out.println(" - pos : " + pt_pos + " ("+pt_row+","+pt_col+")");
		
		
	}
	
	/*---Getters---------------------------------------------------------------*/
	
	public int getUnitNumber() {
		return unitNumber;
	}
	
	public int getUnitSize() {
		return unitSize;
	}
	
	public int size() {
		return size;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public boolean isFull() {
		return size >= capacity;
	}
	
	public boolean isEmpty() {
		return size <= 0;
	}
	
	/*---Modifiers-------------------------------------------------------------*/
	
	public void setCapacity(int capacity) {
		if (capacity > 0) {
			this.capacity = capacity;
		}
	}
	
	private void resetPointer() {
		pt_row = 0;
		pt_col = 0;
		pt_pos = 0;
	}
	
	private void increasePointer() {
		
		pt_pos++;
		if (pt_pos >= capacity) {
			resetPointer();
		}
		else {
			pt_col++;
			
			// Test reach end of unit
			if (pt_col == unitSize) {
				pt_col = 0;
				pt_row++;
				
				// Test reach end of all units
				if (pt_row == unitNumber) {
					pt_row = 0;
					pt_pos = 0;
				}
			}
		}
	}
	
	public void decreasePointer() {
		
		// In array bounds
		if (pt_pos > 0) {
			pt_pos--;
			
			if (pt_col > 0) {
				pt_col--;
			}
			else {
				pt_col = unitSize - 1;
				
				if (pt_row > 0) {
					pt_row--;
				}
				else {
					pt_row = unitNumber - 1;
				}
			}
		}
		else {
			// Set pointer to the last cell
			pt_pos = capacity - 1;
			
			pt_row = pt_pos / unitSize;
			pt_col = pt_pos % unitSize;
		}
	}
	
	/*---Inherited methods-----------------------------------------------------*/
	
	@Override
   public void push(IModificationTransaction transaction) {
	   transactions[pt_row][pt_col] = transaction;
	   
	   if (!isFull()) {
	   	size++;
	   }
	   
	   increasePointer();
   }
	
	@Override
   public IModificationTransaction pop() {
		if (isEmpty()) return null;
		
		// Warning : has to be decreased first, pointer is on the next cell
		decreasePointer();
		
		IModificationTransaction transaction = transactions[pt_row][pt_col];
		
		transactions[pt_row][pt_col] = null;
		size--;
		
	   return transaction;
   }
}
