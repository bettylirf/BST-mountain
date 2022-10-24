package project5;

/**
 * This class represents a Hiker that is used to store the number of each supply that it has.
 * It provides a constructor with no parameter that constructs a new Hiker object.
 * 
 * @author Betty Li
 *
 */

public class Hiker {
	private int numOfFood;
	private int numOfAxe;
	private int numOfRaft;
	
	/**
	 * Constructs a new Hiker object. Set the initial number of food to one, and the initial
	 * number of axe and raft each to zero.
	 */
	public Hiker(){
		this.numOfFood = 1;
		this.numOfAxe = 0;
		this.numOfRaft = 0;
	}
	
	/**
	 * Check whether the hiker has enough supply. Return true if the number of each category
	 * of supply is greater than or equal to zero, false otherwise.
	 * @return true if the number of each category of supply is greater than or equal to 
	 * zero, false otherwise.
	 */
	public boolean checkSupply() {
		return this.numOfFood >= 0 && this.numOfAxe >= 0 && this.numOfRaft >= 0;
	}
	
	/**
	 * Pick up supply from the specified rest-stop, and adds the supply to the hiker's own
	 * supply.
	 * @param stop RestStop object to pick up supply from.
	 */
	public void pickSupply(RestStop stop) {
		if(stop.getSupplies().isEmpty())
			return;
		for(SupplyType supply: stop.getSupplies()) {
			if(supply == SupplyType.FOOD)
				this.numOfFood ++;
			else if(supply == SupplyType.AXE)
				this.numOfAxe ++;
			else
				this.numOfRaft ++;
		}
	}
	
	/**
	 * Remove supply from the hiker's own supply according to the supply at the specified
	 * rest-stop.
	 * @param stop RestStop object to remove supply accordingly.
	 */
	public void removeSupply(RestStop stop) {
		if(stop.getSupplies().isEmpty())
			return;
		for(SupplyType supply: stop.getSupplies()) {
			if(supply == SupplyType.FOOD)
				this.numOfFood --;
			else if(supply == SupplyType.AXE)
				this.numOfAxe --;
			else
				this.numOfRaft --;
		}
	}
	
	/**
	 * Remove supply from the hiker's own supply according to the type of obstacles that
	 * he needs to deal with
	 * @param stop RestStop object that might have certain obstacles
	 */
	public void fightObstacle(RestStop stop) {
		if(stop.getObstacles().isEmpty())
			return;
		for(ObstacleType obstacle: stop.getObstacles()) {
			if(obstacle == ObstacleType.TREE)
				this.numOfAxe --;
			else
				this.numOfRaft --;	
		}
	}
	
	/**
	 * Add supply to the hiker's own supply according to the type of obstacles that
	 * he is to deal with.
	 * @param stop RestStop object that might have certain obstacles
	 */
	public void reverseObstacle(RestStop stop) {
		if(stop.getObstacles().isEmpty())
			return;
		for(ObstacleType obstacle: stop.getObstacles()) {
			if(obstacle == ObstacleType.TREE)
				this.numOfAxe ++;
			else
				this.numOfRaft ++;	
		}
	}
	
	/**
	 * Update the supply of the hiker if he needs to get through the specified rest-stop.
	 * @param stop RestStop object that the hiker is currently at
	 */
	public void moveForward(RestStop stop) {
		this.numOfFood -= 1;
		pickSupply(stop);
		fightObstacle(stop);
	}
	
	/**
	 * Update the supply of the hiker if he needs to go back from this rest-stop.
	 * @param stop RestStop object that the hiker is currently at
	 */
	public void goBack(RestStop stop) {
		this.numOfFood += 1;
		reverseObstacle(stop);
		removeSupply(stop);
	}
}
