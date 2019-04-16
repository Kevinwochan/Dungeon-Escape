package project.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import project.dungeon.puzzles.BoulderSwitch;
import project.dungeon.puzzles.Levels;
import project.dungeon.puzzles.Maze;
import project.dungeon.puzzles.Puzzle;
import project.dungeon.puzzles.TreasureHunt;
import project.dungeon.tiles.Floor;
import project.dungeon.tiles.MazeTile;
import project.dungeon.tiles.Wall;
 
/*
 * recursive backtracking algorithm
 * shamelessly borrowed from the ruby at
 * http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking
 * and https://rosettacode.org/wiki/Maze_generation#Java
 */
public class MazeGenerator {
	int size;
	private final int x;
	private final int y;
	private final int[][] maze;
 
	public MazeGenerator(int size) {
		this.size = size;
		this.x = (size-1)/2;
		this.y = (size-1)/2;
		maze = new int[this.x][this.y];
		generateMaze(0, 0);
	}
	
	public void display() {
		// iterate over m[][i]
		for (int i = 0; i < y; i++) {
			// line 1
			for (int j = 0; j < x; j++) {
				System.out.print((maze[j][i] & 1) == 0 ? "# # " : "# . ");
			}
			System.out.println("#");
			// line 2
			for (int j = 0; j < x; j++) {
				System.out.print((maze[j][i] & 8) == 0 ? "# . " : ". . ");
			}
			// end line
			System.out.println("#");
		}
		
		// draw the bottom line
		for (int j = 0; j < x; j++) {
			System.out.print("# # ");
		}
		System.out.println("#");
	}
	
	private void generateMaze(int cx, int cy) {
		DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs));
		for (DIR dir : dirs) {
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			if (between(nx, x) && between(ny, y)
					&& (maze[nx][ny] == 0)) {
				maze[cx][cy] |= dir.bit;
				maze[nx][ny] |= dir.opposite.bit;
				generateMaze(nx, ny);
			}
		}
	}
 
	private static boolean between(int v, int upper) {
		return (v >= 0) && (v < upper);
	}
 
	private enum DIR {
		N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;
		private DIR opposite;
 
		// use the static initializer to resolve forward references
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}
 
		private DIR(int bit, int dx, int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	};
 
	public static void main(String[] args) throws IOException {
		MazeGenerator maze = new MazeGenerator(9);
		maze.display();
		maze.generate();
	}

	public MazeTile[][] generate() throws IOException {
		MazeTile[][] m = new MazeTile[size][size];
		// iterate over m[][i]
		int a = 0; int b = 0;
		for (int i = 0; i < y; i++) {
			// line 1
			for (int j = 0; j < x; j++) {
				if ((maze[j][i] & 1) == 0) {
					m[a++][b] = new Wall();
					m[a++][b] = new Wall();
				}else {
					m[a++][b] = new Wall();
					m[a++][b] = new Floor();
				}
			}
			m[a++][b++] = new Wall();
			a = 0;
			// line 2
			for (int j = 0; j < x; j++) {
				if ((maze[j][i] & 8) == 0) {
					m[a++][b] = new Wall();
					m[a++][b] = new Floor();
				}else {
					m[a++][b] = new Floor();
					m[a++][b] = new Floor();
				}
			}
			m[a++][b++] = new Wall();
			a = 0;
		}
		
		// draw the bottom line
		for (int j = 0; j < x; j++) {
			m[a++][b] = new Wall();
			m[a++][b] = new Wall();
		}
		m[a++][b++] = new Wall();
		return m;
		
	}
	 
	public Puzzle generatePuzzle(int size) throws IOException {
		Random r = new Random();
		int max = Levels.values().length-1;
		int pick = r.nextInt((max - 1) + 1) + 1; //r.nextInt(Levels.values().length);
		
		Levels level = Levels.values()[pick];
		switch(level) {
			case TREASUREHUNT:
				return new TreasureHunt(size);
			case BOULDERSWITCH:
				return new BoulderSwitch(size);
			case MAZE:
				return new Maze(size);
			default:
				return new Puzzle(size);
		}
	}
	
}
