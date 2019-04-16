package project.dungeon.actors.statuses;

public final class Dead implements Status {

	@Override
	public String getName() {
		return "Lost";
	}

	@Override
	public void tick() {
		return;
	}
}