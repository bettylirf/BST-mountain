package project5;
/**
 * This enumerated type describes all possible types of supply at a rest-stop.
 * 
 * @author Betty Li
 *
 */
public enum SupplyType {
	FOOD ("food"),
	RAFT ("raft"),
	AXE  ("axe");
	
	//name associated with a given type of supply
	private String supplyName;
	
	/**
	 * Create a supply of given supply name
	 * @param supplyName String the name of the supply
	 */
	SupplyType(String supplyName){
		this.setSupplyName(supplyName);
	}
	
	/**
	 * Return the supplyName of the supply
	 * @return the supplyName of the supply
	 */
	public String getSupplyName() {
		return supplyName;
	}
	
	/**
	 * Set the supplyName of the supply
	 * @param supplyName String the name of the supply to be set
	 */
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
}
