package game.logic;

import java.util.LinkedList;
import java.util.ListIterator;

import game.logic.modstack.IModification;
import game.logic.modstack.IModificationTransaction;

public class Transaction implements IModificationTransaction {
	
	private boolean hasBegin = false;
	
	private LinkedList<IModification> modList = new LinkedList<>();
	
	/*---Inherited methods-----------------------------------------------------*/

	@Override
   public IModificationTransaction begin() {
	   hasBegin = true;
	   modList.clear();
	   return this;
   }

	@Override
   public IModificationTransaction add(IModification modif) {
		if (hasBegin) {
			modList.add(modif);
		}	
	   return this;
   }

	@Override
   public IModificationTransaction commit() {
	   if (hasBegin) {
	   	for (IModification modif : modList) {
	   		modif.apply();
	   	}
	   	hasBegin = false;
	   }
	   return this;
   }

	@Override
   public void revert() {
		// Warning : has to execute from last element
	   if (!hasBegin) {
	   	ListIterator<IModification> it = modList.listIterator(modList.size());
	   	IModification modif;
	   	while (it.hasPrevious()) {
	   		modif = it.previous();
	   		modif.cancel();
	   	}
	   }
   }

}
