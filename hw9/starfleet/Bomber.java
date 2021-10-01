package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends Fighter{
	private int numberOfTechnicians;
	
	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians){
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		this.numberOfTechnicians =  numberOfTechnicians;
	}
	
	public int getNumberOfTechnicians() {
		return this.numberOfTechnicians;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		float discount = getNumberOfTechnicians()/10;
		int weaponsCost = Math.round(weaponsMaintenanceCost() * discount); 
		return 5000 + weaponsCost;
	}

	@Override
	public String toString() {
		return super.toString() + 
				"	NumberOfTechnicians=" + getNumberOfTechnicians() + System.lineSeparator();
	}
	
	
}
