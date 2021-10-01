package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends MyAbstractSpaceship{
	
	protected List<Weapon> weapons;
	private int NumOfStealthCruiser;
	
	public Fighter(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons){
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.weapons = weapons;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int speeadCost = Math.round(1000*getMaximalSpeed());
		return 2500 + speeadCost + weaponsMaintenanceCost();
	}
	
	protected int weaponsMaintenanceCost() {
		int MaintenanceCost = 0;
		for(Weapon weapon : weapons) {
			MaintenanceCost += weapon.getAnnualMaintenanceCost();
		}
		
		return MaintenanceCost;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	@Override
	public String toString() {
		return super.toString() + 
				"	Weapons=" + getWeapons().toString() + System.lineSeparator();
	}

	@Override
	public int getFirePower() {
		int firePower = 0;
		for(Weapon weapon : weapons) {
			firePower += weapon.getFirePower();
		}
		return super.getFirePower() + firePower;
	}
	
	protected int getNumOfStealthCruiser() {
		return NumOfStealthCruiser;
	}
	
	protected void addStealthCruiser() {
		NumOfStealthCruiser++;
	}
	
	
}
