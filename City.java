/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Anirudh Cherukuri
 *	@since	January 14, 2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String state;
	private String city;
	private String type;
	private int population;

	// constructor
	public City(String stateIn, String cityIn, String typeIn, int popIn) {
		state = stateIn;
		city = cityIn;
		type = typeIn;
		population = popIn;
	}

	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other) {
		if(population != other.getPopulation())
			return population - other.getPopulation();
		if(!state.equalsIgnoreCase(other.getState()))
			return state.compareTo(other.getState());
		return city.compareTo(other.getCity());
	}
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other) {
		return other.getCity().equalsIgnoreCase(other.getState());
	}
	
	/**	Accessor methods */
	public String getState() {
		return state;
	}
	public String getCity() {
		return city;
	}
	public String getType() {
		return type;
	}
	public int getPopulation() {
		return population;
	}
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %12d", state, city, type,
						population);
	}
}