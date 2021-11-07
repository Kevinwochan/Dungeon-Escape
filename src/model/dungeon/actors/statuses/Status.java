package model.dungeon.actors.statuses;

public interface Status {
	/*
	 * Status description
	 */
	public String getName();
	/*
	 *  Turn based effects on the character
	 */
	public void tick();
}
