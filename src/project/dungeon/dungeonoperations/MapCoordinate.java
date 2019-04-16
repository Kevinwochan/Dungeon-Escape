package project.dungeon.dungeonoperations;

import java.util.LinkedList;
import java.util.Random;

/*
 * Contains a x and y value 
 * a (-1,-1) means player inventory
 */
public class MapCoordinate {
	
	int x;
	int y;

	/*
	 * Constructor class
	 */
	public MapCoordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Constructor class for random coordinate
	 */
	public MapCoordinate(int size){
		Random r = new Random();
		this.x = r.nextInt(size);
		this.y = r.nextInt(size);
	}

	public MapCoordinate() {
		this.x = -1;
		this.y = -1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	/*
	 * @param the size of the map
	 * @return a MazeCoordinate Reflecting the coordinate by the center of the grid
	 */
	public MapCoordinate reflect(int mapSize) {
		return new MapCoordinate(mapSize - x, mapSize - y);
	}
	/*
	 * @this point of reflection
	 * @param coordinate to reflect 
	 * @return a coordinate co reflected upon this
	 */
	public MapCoordinate reflect(MapCoordinate co) {
		// find the line represented by this
		// int f = y/x;
		// find the vector for the perpendicular distance from co to the line
		// multiple the vector by 2
		MapCoordinate vector = this.subtract(co);
		return new MapCoordinate(-1*vector.getX(), vector.getY());
	}

	/*
	 * @param a MazeCoordinate
	 * @return a MazeCoordinate
	 */
	public MapCoordinate subtract(MapCoordinate coOffset) {
		return new MapCoordinate (x-coOffset.getX(), y-coOffset.getY());
	}

	/*
	 * @param a MazeCoordinate
	 * @return a MazeCoordinate
	 */	
	public MapCoordinate add(MapCoordinate coOffset) {
		return new MapCoordinate (x+coOffset.getX(), y+coOffset.getY());
	}
	/*
	 * @param a MazeCoordinate
	 * @return randomised MazeCoordinate
	 */	
	public void random (int size) {
		Random r = new Random();
		x = r.nextInt(size);
		y = r.nextInt(size);
	}
	@Override
	public String toString() {
		return "MazeCoordinate: (" + x + "," + y + ")";
	}

	/*
	 * @return a list of coordinate above,below,left and right
	 * of the coordinate
	 */
	public LinkedList<MapCoordinate> adjCoordinates() {
		LinkedList<MapCoordinate> list = new LinkedList<>();
		MapCoordinate left = this.add(new MapCoordinate(-1,0));
		list.add(left);
		MapCoordinate right = this.add(new MapCoordinate(1,0));
		list.add(right);
		MapCoordinate above = this.add(new MapCoordinate(0,1));
		list.add(above);
		MapCoordinate below = this.add(new MapCoordinate(0,-1));
		list.add(below);
		return list;
	}
}