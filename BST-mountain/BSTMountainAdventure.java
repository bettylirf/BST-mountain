package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * This application finds a way out down the mountain specified in the input
 * file (if such a way out exists). The visual feedback of the valid path to the
 * bottom of the mountain is printed to the console. 
 *
 * @author Betty Li
 *
 */
public class BSTMountainAdventure {

	public static void main(String[] args) {
		//verify that the command line argument exists 
		if (args.length == 0) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}
		
		//verify that command line argument contains a name of an existing file 
		File dataFile = new File(args[0]); 
		if (!dataFile.exists()){
			System.err.println("Error: the file "+dataFile.getAbsolutePath()+" does not exist.\n");
			System.exit(1);
		}
		if (!dataFile.canRead()){
			System.err.println("Error: the file "+ dataFile.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}
		
		//open the file for reading 
		Scanner in = null;
		try {
			in = new Scanner (dataFile) ;
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file "+ dataFile.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}

		BSTMountain<RestStop> mountain = new BSTMountain<>();
		Scanner parseLine = null;
		
		//read each line of the file
		while(in.hasNextLine()) {
			String line = in.nextLine().trim();
			parseLine = new Scanner(line);
			
			try {
				String label = parseLine.next();
				ArrayList<SupplyType> supplies = new ArrayList<>();
				ArrayList<ObstacleType> obstacles = new ArrayList<>();
				
				while(parseLine.hasNext()) {
					switch(parseLine.next()) {
					case "food": supplies.add(SupplyType.FOOD); break;
					case "axe":	supplies.add(SupplyType.AXE); break;
					case "raft": supplies.add(SupplyType.RAFT); break;
					case "river": obstacles.add(ObstacleType.RIVER); break;
					case "fallen": 
						if(parseLine.hasNext() && parseLine.next().equals("tree"))
							obstacles.add(ObstacleType.TREE);	
						break;
					default: break;
					}
				}
				//add a newly created RestStop created from this line of the file to the mountain
				mountain.add(new RestStop(label, supplies, obstacles));
			} catch(NoSuchElementException ex) { //catch exception
				//ignore and continue reading the next line of the file
				continue;
			}		
		}
		//call the findWay method to perform the actual process of finding way
		//down the mountain
		mountain.findWay();
	}

}
