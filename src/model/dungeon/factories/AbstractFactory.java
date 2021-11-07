package model.dungeon.factories;

import java.io.IOException;

/*
 * An Abstract Factory 
 * handles the creation of objects based on a string
 */
public abstract class AbstractFactory {
	
	public abstract Object generate(String name) throws IOException;

}