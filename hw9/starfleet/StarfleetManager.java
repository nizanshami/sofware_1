package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {
		List<Spaceship> fleetList = new ArrayList<Spaceship>();
		for(Spaceship ship : fleet) {
			fleetList.add(ship);
		}
		fleetList.sort(new Comparator<Spaceship>() {
			public int compare(Spaceship o1, Spaceship o2) {
				if(o1.getFirePower() != o2.getFirePower()) {
					if(o1.getFirePower() > o2.getFirePower()) {
						return -1;
					}else { return 1;}
				}
				if(o1.getCommissionYear() != o2.getCommissionYear()) {
					if(o1.getCommissionYear() > o2.getCommissionYear()) {
						return -1;
					}else { return 1;}
				}
				return o1.getName().compareTo(o2.getName());
			}
				
			
		} );
		List<String> fleetToString = new ArrayList<String>();
		for(Spaceship ship : fleetList) {
			 fleetToString.add(ship.toString());
		}
		return fleetToString;
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		Map<String, Integer> fleetMap = new HashMap<String, Integer>();
		for(Spaceship ship : fleet) {
			String key = ship.getClass().getSimpleName();
			if(!fleetMap.containsKey(key)) {
				fleetMap.put(key, 1);
			}else {
				fleetMap.replace(key,fleetMap.get(key) + 1);
			}
		}
		return fleetMap;

	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		int cost = 0;
		for(Spaceship ship : fleet) {
			cost += ship.getAnnualMaintenanceCost();
		}
		return cost;
	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		Set<String> weapoms = new HashSet<String>();
		for(Spaceship ship : fleet) {
			if(ship instanceof Fighter) {
				Fighter f = (Fighter) ship;
				for(Weapon weapon : f.getWeapons()) {
					weapoms.add(weapon.getName());
				}
			}
		}
		return weapoms;

	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		int crew = 0;
		for(Spaceship ship : fleet) {
			crew += ship.getCrewMembers().size();
		}
		return crew;

	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		float numOfOfficers = 0;
		float sumOfAges = 0;
		for(Spaceship ship : fleet) {
			for(CrewMember mem : ship.getCrewMembers()) {
				if(mem instanceof Officer) {
					sumOfAges += mem.getAge();
					numOfOfficers++;
				}
			}
		}
		return sumOfAges/numOfOfficers;
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		Map<Officer, Spaceship> fleetMap = new HashMap<Officer, Spaceship>();
		Officer maxRank;
		for(Spaceship ship : fleet) {
			maxRank = null;
			for(CrewMember mem : ship.getCrewMembers()) {
				if(mem instanceof Officer) {
					Officer f = (Officer) mem;
					if(maxRank == null) {
						maxRank = f;
					}else {
						if(maxRank.getRank().compareTo(f.getRank()) == -1) {
							maxRank = f;
						}
					}
				}
			}
			if (maxRank != null) {
				fleetMap.put(maxRank, ship);
			}
		}
		return fleetMap;
	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		List<Map.Entry<OfficerRank, Integer>> lst = new ArrayList<Map.Entry<OfficerRank, Integer>>();
		Map<OfficerRank, Integer> fleetMap = new HashMap<OfficerRank, Integer>();
		for(OfficerRank Rank : OfficerRank.values()) {
			fleetMap.put(Rank, 0);
		}
		for(Spaceship ship : fleet) {
			for(CrewMember mem : ship.getCrewMembers()) {
				if(mem instanceof Officer) {
					Officer f = (Officer) mem;
					fleetMap.put(f.getRank(), fleetMap.get(f.getRank()) + 1);
				}
			}
		}
		for(Map.Entry<OfficerRank, Integer> entry : fleetMap.entrySet()) {
			lst.add(entry);
		}
		lst.sort(Map.Entry.comparingByValue());
		return lst;
	}

}
