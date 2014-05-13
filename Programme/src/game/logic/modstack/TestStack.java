package game.logic.modstack;

public class TestStack {
	
	public static void main(String[] args) {
		
		ModificationStack stack = new ModificationStack(3,3);
		
		
		for (int i = 0 ; i < 1000 ; i++) {
			
			stack.printStatus();
			
			if (Math.random() > 0.35) {
				System.out.println("Push !");
				stack.push(new Modif());
			}
			else {
				System.out.print("Pop ! ");
				IModificationTransaction trans = stack.pop();
				if (trans == null) {
					System.out.println("(failed)");
				}
				else {
					System.out.println("(success)");
				}
			}
			
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {}
			
		}
		
		
	}
	
	
	public static class Modif implements IModificationTransaction {

		@Override
      public IModificationTransaction begin() {
	      // TODO Auto-generated method stub
	      return null;
      }

		@Override
      public IModificationTransaction add(IModification modif) {
	      // TODO Auto-generated method stub
	      return null;
      }

		@Override
      public IModificationTransaction commit() {
	      // TODO Auto-generated method stub
	      return null;
      }

		@Override
      public void revert() {
	      // TODO Auto-generated method stub
	      
      }
		
	}

}
