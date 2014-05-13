package game.logic.modstack;

public interface IModificationStack {
	public void push(IModificationTransaction transaction);
	public IModificationTransaction pop();
	
}
