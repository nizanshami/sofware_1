package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class StealthCruiser extends Fighter{
	private static List<Weapon> stndWeapon = Arrays.asList(new Weapon ("Laser Cannons",10,100)
);
	
	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		addStealthCruiser();
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers){
		super(name, commissionYear, maximalSpeed, crewMembers, stndWeapon);
		 addStealthCruiser();
	}
	
	@Override
	public int getAnnualMaintenanceCost() {
		return super.getAnnualMaintenanceCost() + getNumOfStealthCruiser()*50;
	}
	
}
