package game.logic.modstack;

public interface IModificationTransaction {
	public IModificationTransaction begin();
	public IModificationTransaction add(IModification modif);
	public IModificationTransaction commit();
	public void revert();
}
