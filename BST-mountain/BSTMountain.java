package project5;

/**
 * This class represents a mountain. 
 * It is an implementation of binary search tree and stores a collection of RestStop objects.
 * Each node in the binary search tree is a representation of rest-stop on the mountain.
 * The order of the RestStop data stored in the tree is based on the alphanumeric value of
 * the field label in RestStop.
 * 
 * Nodes in this tree represent a rest-stops. The hiker can pick up their supplies at those 
 * rest-stops, and they need to use the supplies to cope with obstacles at the rest-stops.
 * The root of the tree represents the mountain top, and the paths from the root down represent 
 * possible trails. Some path leads to cliffs, as represented by leave nodes with depth smaller
 * than the depth of the tree. Other path leads to bottom of the mountain, as represented by
 * leave nodes with depth equal to the depth of the tree.
 * 
 * The class provides a constructor with no parameter that creates an empty BSTMountain.
 * 
 * @author Betty Li
 * 
 */
public class BSTMountain <T extends Comparable<RestStop>>{

	private BSTNode root;
	private boolean added;
	
	/**
	 * Constructs a new BSTMountain object with its root equal to null and no data stored in it.
	 */
	public BSTMountain() {
		root = null;
	}
	
	/**
	 * Adds a specified RestStop object to this tree according to the alphanumeric order of the label of
	 * the RestStop, and returns a boolean indicating whether the RestStop data is successfully added to
	 * the tree.
	 * @param data the RestStop object to be added to this tree.
	 * @return true if this tree changed as a result of the call, false otherwise (the specified element 
	 * is null or the tree already contains the same element).
	 */
    public boolean add(RestStop data) { 
    	added = false; 
        if (data == null) 
        	return added; 
        root = add(data, root);
        return added;
    }
    
	/**
	 * Recursively performs the adding action, data is the RestStop object to be added to the tree.
	 *  Return true if the data is successfully added, false if a duplicate
	 * is found in the tree.
	 * @param data RestStop object to be added to this tree
	 * @param node BSTNode in the tree whose data is currently compared to the RestStop to determine
	 * whether it should be added.
	 * @return true if the data is successfully added to the tree, false otherwise;
	 */
	private BSTNode add (RestStop data, BSTNode node) {
		//if the current node is null, we have successfully found the location to add the data
        if (node == null) {
            added = true; 
            return new BSTNode(data); 
        }
        //decide how comparisons should be done 
        int comp = node.data.compareTo(data); 
        
        //find the location to add the new value 
        if (comp > 0 ) { //add to the left subtree
        	node.left = add(data, node.left);
        }           
        else if (comp < 0 ) {//add to the right subtree
            node.right = add(data, node.right); 
        }
        else { //duplicate found, do not add 
            added = false; 
            return node; 
        }
        //update height of the current node
        node.height = updateHeight(node);
        return node; 
    }
    
	/**
	 * Update the height of the node in the tree, return the new height of the node.
	 * @param node BSTNode whose height is to be updated
	 * @return int that represents the new height of the node
	 */
    public int updateHeight(BSTNode node) {
    	//if the node is a leave node, its height is zero
    	if(node.left == null && node.right == null)
    		return 0;
    	else if(node.left == null) 
    		return node.right.height + 1;
    	else if(node.right == null) 
    		return node.left.height + 1;
    	else
    		return Math.max(node.left.height, node.right.height) + 1;
    }
    
    /**
     * Wrapper method of the recursive findWay method. Terminate the method if the root of the tree
     * is null, else call the recursive findWay method.
     */
    public void findWay() {
    	if(this.root == null)
    		return;
    	findWayRec(root, new Hiker(), root.height, "");
    }
    
    /**
     * Recursively finds the way to the bottom of the mountain. Node is the current position of the
     * hiker in the tree; hiker is the Hiker object that is going down the mountain; currentHeight
     * determines which path leads to cliff and which leads to the bottom of the mountain; toReturn
     * is the current String representation of the path we have traveled. Return true of the hiker can
     * get through the current RestStop, and the current path is possibly leading to the bottom of the
     * mountain; false if it's a cliff, or it would lead to cliff, or the hiker run out of supply.
     * @param node BSTNode that contains all data related to the current rest-stop that the hiker
     * is at.
     * @param hiker Hiker object that is the representation of the hiker going down the mountain.
     * @param currentHeight int that is the height of the subtree that is possibly leading to the 
     * bottom of the mountain
     * @param toReturn String that represents the current path the hiker has traveled.
     * @return true if there's enough supply and the current path is possibly leading to the bottom
     * of the mountain, false otherwise.
     */
    public boolean findWayRec(BSTNode node, Hiker hiker, int currentHeight, String toReturn) { 
    	if(node == null || !(node.height == currentHeight))
    		return false;
    	//pick up supply and fight obstacle with the supply
    	hiker.moveForward((RestStop)node.data);
    	
    	//if the hiker has enough supply
    	//he could get through this rest-stop
    	if(hiker.checkSupply()){
    		toReturn += ((RestStop)node.data).getLabel() + " ";
    		//if the height of this node equals to zero
    		//it is the bottom of this mountain
        	if(node.height == 0) {
        		//print the path
            	System.out.println(toReturn.trim());
            	return true;
            }
        	//travel to the left child of this node
        	if(findWayRec(node.left, hiker, currentHeight - 1, toReturn))
        		hiker.goBack((RestStop)node.left.data);
        	//travel to the right child of this node
        	if(findWayRec(node.right, hiker, currentHeight - 1, toReturn))
        		hiker.goBack((RestStop)node.right.data);
        	return true;
    	}
    	else { //the hiker doesn't have enough supply to get through this node
    		//reverse the supply of the hiker to the status before getting to this node
        	hiker.goBack((RestStop)node.data);
        	return false;
    	}
    }
    
	/**
	 * This class is a private internal class in the BSTMountain class.
	 * It store one piece data of the RestStop data type, reference to its two children, and the
	 * height of this node.
	 * 
	 * This class provide a one-parameter constructor that sets the data.
	 * - Node(RestStop data) in which the data is of the RestStop data type.
	 * 
	 * @author Betty Li
	 */
    private class BSTNode implements Comparable<BSTNode> {
        RestStop data;
        BSTNode left;
        BSTNode right;
        int height;

		/**
		 * Constructs a new Node object with specified data
		 * Set the pointer to its two children to null, and set the height to zero.
		 * @param data value of RestStop data type to be used for this node
		 */
        public BSTNode(RestStop data){ 
            this.data = data;
            this.height = 0;
            this.left = null;
            this.right = null;
        }
		/**
		 * Compares this node to another node, return the result of the comparison between
		 * the RestStop data stored in these two node.
		 * @param other BSTNode that this node is compared to
		 */
		@Override
		public int compareTo(BSTNode other) {
			return ((RestStop)this.data).compareTo((RestStop)other.data);
		}
    }
}