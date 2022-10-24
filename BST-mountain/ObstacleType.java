package project5;
/**
 * This enumerated type describes all possible types of obstacles at a rest-stop.
 * 
 * @author Betty Li
 *
 */
public enum ObstacleType {
	TREE  ("fallen tree"),
	RIVER ("river");
	
	//name associated with a given type of supply
	private String obstacle;
	
	/**
	 * Create a supply of given obstacle name
	 * @param obstacle String the name of the obstacle
	 */
	ObstacleType(String obstacle){
		this.setObstacle(obstacle);
	}
	
	/**
	 * Return the String name of the obstacle
	 * @return the String that is the name of the obstacle
	 */
	public String getObstacle() {
		return obstacle;
	}

	/**
	 * Set the obstacle (the name) of the obstacle
	 * @param obstacle String the name of the obstacle to be set
	 */
	public void setObstacle(String obstacle) {
		this.obstacle = obstacle;
	}
	
}
