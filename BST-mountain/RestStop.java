package project5;

import java.util.ArrayList;

/**
 * This class represents a single rest-stop on the mountain. 
 * It provides a three-parameter constructor that creates a new RestStop object.
 * - label, the String name of the rest-stop;
 * - supplies, an ArrayList that contains all supplies at the rest-stop; 
 * - obstacles, an ArrayList that contains all obstacles at the rest-stop.
 * 
 * 
 * @author Betty Li
 *
 */
public class RestStop implements Comparable<RestStop>{
	private String label;
	private ArrayList<SupplyType> supplies;
	private ArrayList<ObstacleType> obstacles;
	
	/**
	 * Constructs new RestStop object.
	 * @param label String the name of this rest-stop
	 * @param supplies ArrayList containing all supplies at this rest-stop
	 * @param obstacles ArrayList containing all obstacles at this rest-stop
	 */
	public RestStop(String label, ArrayList<SupplyType> supplies, ArrayList<ObstacleType> obstacles){
		this.label = label;
		this.supplies = supplies;
		this.obstacles = obstacles;
	}
	
	/**
	 * Returns the supplies field of this RestStop object
	 * @return the supplies field, ArrayList, of this RestStop object
	 */
	public ArrayList<SupplyType> getSupplies() {
		return supplies;
	}

	/**
	 * Returns the obstacles field of this RestStop object
	 * @return the obstacles field, ArrayList, of this RestStop object
	 */
	public ArrayList<ObstacleType> getObstacles() {
		return obstacles;
	}

	/**
	 * Returns the field label of this RestStop object
	 * @return label String label field of this RestStop object
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Return the result of the label of this RestStop compared to that of the other RestStop
	 * object.
	 * @return int, the result of the label of this RestStop compared to the label of the other
	 * RestStop object.
	 */
	@Override
	public int compareTo(RestStop other) {
		return this.label.compareTo(other.getLabel());
	}
}
