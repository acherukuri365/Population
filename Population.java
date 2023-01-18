import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Anirudh Cherukuri
 *	@since	January 14, 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	public Population() {
		cities = new ArrayList<City>();
	}
	
	public static void main(String[] args) {
		Population pop = new Population();
		pop.run();
	}

	public void run() {
		loadCitiesList();
		printIntroduction();

		char input = ' ';
		long startMill = 0;
		long endMill = 0;
		while(input != '9') {
			printMenu();
			input = Prompt.getChar("Enter selection");
			switch(input) {
				case '1':
					startMill = System.currentTimeMillis();
					sortAscendingPopulation();
					endMill = System.currentTimeMillis();
					System.out.println("\nElapsed Time " + (endMill - startMill) + " milliseconds\n");
					break;
				case '2':
					startMill = System.currentTimeMillis();
					sortDescendingPopulation(0, cities.size() - 1);
					endMill = System.currentTimeMillis();
					System.out.println("\nElapsed Time " + (endMill - startMill) + " milliseconds\n");
					break;
				case '3':
					startMill = System.currentTimeMillis();
					sortAscendingName();
					endMill = System.currentTimeMillis();
					System.out.println("\nElapsed Time " + (endMill - startMill) + " milliseconds\n");
					break;
				case '4':
					startMill = System.currentTimeMillis();
					sortDescendingName(0, cities.size() - 1);
					endMill = System.currentTimeMillis();
					System.out.println("\nElapsed Time " + (endMill - startMill) + " milliseconds\n");
					break;
				case '5':
					startMill = System.currentTimeMillis();
					sortAscendingPopulation();
					endMill = System.currentTimeMillis();
					System.out.println("\nElapsed Time " + (endMill - startMill) + " milliseconds\n");
					break;
				case '6':
					startMill = System.currentTimeMillis();
					sortAscendingPopulation();
					endMill = System.currentTimeMillis();
					System.out.println("\nElapsed Time " + (endMill - startMill) + " milliseconds\n");
					break;
				case '9':
					System.out.println("\nThanks for using Population!");
					break;
				default:
					System.out.println("\nPlease enter a valid selection\n");
			}
		}
	}

	public void sortAscendingPopulation() {
		for(int outer = cities.size() - 1; outer > 0; outer--) {
			int max = 0;
			for(int inner = 0; inner <= outer; inner++) {
				if(cities.get(inner).getPopulation() > cities.get(max).getPopulation())
					max = inner;
			}
			// swap(cities, max, outer);
			City temp = cities.get(max);
			// cities.get(max) = cities.get(outer);
			// cities.get(outer) = temp;
			String state = cities.get(outer).getState();
			String city = cities.get(outer).getCity();
			String type = cities.get(outer).getType();
			int population = cities.get(outer).getPopulation();

			cities.remove(max);
			cities.add(max, new City(state, city, type, population));

			cities.remove(outer);
			cities.add(outer, new City(temp.getState(), temp.getCity(), temp.getType(),
				temp.getPopulation()));
		}

		// System.out.println("\nFifty least populous cities");
		// System.out.printf("%9s %21s %22s %20s\n", "State", "City", "Type", "Population");
		// for(int i = 0; i < 50; i++) {
		// 	if(i < 9)
		// 		System.out.print(" ");
		// 	System.out.println((i + 1) + ": " + cities.get(i).toString());
		// }

		System.out.println("\nFifty least populous cities");
		printList();
	}

	public void sortDescendingPopulation(int left, int right) {
		if(left < right) {
			int mid = left + (right - left) / 2;
			sortDescendingPopulation(left, mid);
			sortDescendingPopulation(mid + 1, right);

			mergeSort(left, mid, right);
		}

		System.out.println("\nFifty most populous cities");
		printList();
	}

	public void mergeSort(int left, int mid, int right) {
		int leftSide = mid - left + 1;
		int rightSide = right - mid;

		List<City> leftList = new ArrayList<City>();
		List<City> rightList = new ArrayList<City>();


		for (int i = 0; i < leftSide; i++) 
		{
			leftList.add(cities.get(left + i));
		}
		for (int i = 0; i < rightSide; i++) {
			rightList.add(cities.get(mid + 1 + i));
		}

		int leftIndex = 0;
		int rightIndex = 0;
		int index = left;

		while(leftIndex < leftSide && rightIndex < rightSide) 
		{
			if (leftList.get(leftIndex).compareTo(rightList.get(rightIndex)) >= 0) 
			{
				cities.add(index, new City(leftList.get(leftIndex).getState(),
					leftList.get(leftIndex).getCity(), leftList.get(leftIndex).getType(),
					leftList.get(leftIndex).getPopulation()));
				cities.remove(index + 1);
				leftIndex++;
			}
			else 
			{
				cities.add(index, new City(rightList.get(rightIndex).getState(),
					rightList.get(rightIndex).getCity(), rightList.get(rightIndex).getType(),
					rightList.get(rightIndex).getPopulation()));
				cities.remove(index + 1);
				rightIndex++;
			}
			index++;
		}

		while (leftIndex < leftSide) 
		{
			cities.add(index, new City(leftList.get(leftIndex).getState(),
				leftList.get(leftIndex).getCity(), leftList.get(leftIndex).getType(),
				leftList.get(leftIndex).getPopulation()));
			cities.remove(index + 1);
			leftIndex++;
			index++;
		}

		while (rightIndex < rightSide) 
		{
			cities.add(index, new City(rightList.get(rightIndex).getState(),
				rightList.get(rightIndex).getCity(), rightList.get(rightIndex).getType(),
				rightList.get(rightIndex).getPopulation()));
			cities.remove(index + 1);
			rightIndex++;
			index++;
		}
	}

	public void sortAscendingName() {
		for(int outer = 0; outer < cities.size() - 1; outer++) {
			for(int inner = outer + 1; inner > 0; inner--) {
				if(cities.get(inner).getCity().compareTo(cities.get(inner - 1).getCity()) < 0) {
					City temp = cities.get(inner);
					String state = cities.get(inner - 1).getState();
					String city = cities.get(inner - 1).getCity();
					String type = cities.get(inner - 1).getType();
					int population = cities.get(inner - 1).getPopulation();

					cities.add(inner, new City(state, city, type, population));
					cities.remove(inner + 1);

					cities.add(inner - 1, new City(temp.getState(), temp.getCity(), temp.getType(),
						temp.getPopulation()));
					cities.remove(inner);
				}
				else
					inner = 0;
			}
			// City city = cities.get(outer);
			// int index = outer - 1;

			// while(index >= 0 && compareCities(city, index)) {//city.getCity().compareTo(cities.get(index - 1).getCity()) < 0) {
			// 	// cities.get(index).getCity() = cities.get(index - 1);
			// 	cities.add(index + 1, new City(cities.get(index).getState(), cities.get(index).getCity(),
			// 		cities.get(index).getType(), cities.get(index).getPopulation()));
			// 	cities.remove(index + 2);
			// 	index--;
			// }
			// // cities.get(index) = num;
			// cities.add(index + 1, new City(city.getState(), city.getCity(),
			// 		city.getType(), city.getPopulation()));
			// cities.remove(index + 2);
		}

		System.out.println("\nFifty cities sorted by name");
		printList();
	}

	// public boolean compareCities(City city, int index) {
	// 	if((cities.get(index).getCity()).compareTo(city.getCity()) == 0 )
	// 		return cities.get(index).getPopulation() < city.getPopulation();
	// 	else
	// 		return (cities.get(index).getCity()).compareTo(city.getCity()) > 0;
	// }

	public void sortDescendingName(int left, int right) {
		if(left < right) {
			int mid = left + (right - left) / 2;
			sortDescendingName(left, mid);
			sortDescendingName(mid + 1, right);

			merge(left, mid, right);
		}

		System.out.println("\nFifty cities sorted by name descending");
		printList();
	}

	public void merge(int left, int mid, int right) {
		int leftSide = mid - left + 1;
		int rightSide = right - mid;

		List<City> leftList = new ArrayList<City>();
		List<City> rightList = new ArrayList<City>();


		for (int i = 0; i < leftSide; i++) 
		{
			leftList.add(cities.get(left + i));
		}
		for (int i = 0; i < rightSide; i++) {
			rightList.add(cities.get(mid + 1 + i));
		}

		int leftIndex = 0;
		int rightIndex = 0;
		int index = left;

		while(leftIndex < leftSide && rightIndex < rightSide) 
		{
			if (leftList.get(leftIndex).getCity().compareTo(rightList.get(rightIndex).getCity()) >= 0) 
			{
				cities.add(index, new City(leftList.get(leftIndex).getState(),
					leftList.get(leftIndex).getCity(), leftList.get(leftIndex).getType(),
					leftList.get(leftIndex).getPopulation()));
				cities.remove(index + 1);
				leftIndex++;
			}
			else 
			{
				cities.add(index, new City(rightList.get(rightIndex).getState(),
					rightList.get(rightIndex).getCity(), rightList.get(rightIndex).getType(),
					rightList.get(rightIndex).getPopulation()));
				cities.remove(index + 1);
				rightIndex++;
			}
			index++;
		}

		while (leftIndex < leftSide) 
		{
			cities.add(index, new City(leftList.get(leftIndex).getState(),
				leftList.get(leftIndex).getCity(), leftList.get(leftIndex).getType(),
				leftList.get(leftIndex).getPopulation()));
			cities.remove(index + 1);
			leftIndex++;
			index++;
		}

		while (rightIndex < rightSide) 
		{
			cities.add(index, new City(rightList.get(rightIndex).getState(),
				rightList.get(rightIndex).getCity(), rightList.get(rightIndex).getType(),
				rightList.get(rightIndex).getPopulation()));
			cities.remove(index + 1);
			rightIndex++;
			index++;
		}
	}

	public void loadCitiesList() {
		FileUtils file = new FileUtils();
		Scanner read = file.openToRead(DATA_FILE);
		// read.useDelimiter("[\t\n]");

		while(read.hasNextLine()) {
			String line = read.nextLine();
			// String state = read.next();
			// line = line.substring(state.length());
			// String city = read.next();
			// String type = read.next();
			// String pop = read.next();
			String state = line.substring(0, line.indexOf("\t")).trim();
			line = line.substring(line.indexOf("\t") + 1);

			String city = line.substring(0, line.indexOf("\t")).trim();
			String type = line.substring(line.indexOf("\t") + 1, line.lastIndexOf("\t")).trim();
			String pop = line.substring(line.lastIndexOf("\t"));
			int population = Integer.parseInt(pop.trim());
			cities.add(new City(state, city, type, population));
		}

		System.out.println(cities.size() + " cities in database\n");
	}

	public void printList() {
		System.out.printf("%9s %21s %22s %20s\n", "State", "City", "Type", "Population");
		for(int i = 0; i < 50; i++) {
			if(i < 9)
				System.out.print(" ");
			System.out.println((i + 1) + ": " + cities.get(i).toString());
		}
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
}