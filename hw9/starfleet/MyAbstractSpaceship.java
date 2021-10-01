package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public abstract class MyAbstractSpaceship implements Spaceship, Comparable<Spaceship> {
	protected String name;
	protected int commissionYear;
	protected float maximalSpeed;
	protected Set<? extends CrewMember> crewMembers;
	
	
	
	public MyAbstractSpaceship(String name, int commissionYear, float maximalSpeed,
			Set<? extends CrewMember> crewMembers) {
		this.name = name;
		this.commissionYear = commissionYear;
		this.maximalSpeed = maximalSpeed;
		this.crewMembers = crewMembers;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getCommissionYear() {
		return commissionYear;
	}
	
	@Override
	public float getMaximalSpeed(){
		return maximalSpeed;
	}
	
	@Override
	public Set<? extends CrewMember> getCrewMembers() {
		return crewMembers;
	}
	
	@Override
	public int getFirePower(){
		return 10;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return 0;
	}

	@Override
	public String toString() {
		String s = 
				getClass().getSimpleName() + System.lineSeparator() + 
				"	Name=" + getName() + System.lineSeparator() + 
				"	CommissionYear=" + getCommissionYear() + System.lineSeparator() + 
				"	MaximalSpeed=" + getMaximalSpeed() +System.lineSeparator() +
				"	FirePower=" + getFirePower() + System.lineSeparator() + 
				"	CrewMembers=" + getCrewMembers().size() + System.lineSeparator() + 
				"	AnnualMaintenanceCost=" + getAnnualMaintenanceCost() + System.lineSeparator();
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyAbstractSpaceship other = (MyAbstractSpaceship) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Spaceship arg0) {
		if(this.getFirePower() != arg0.getFirePower()) {
			if(this.getFirePower() > arg0.getFirePower()) {
				return -1;
			}else { return 1;}
		}
		if(this.getCommissionYear() != arg0.getCommissionYear()) {
			if(this.getCommissionYear() > arg0.getCommissionYear()) {
				return -1;
			}else { return 1;}
		}
		return this.getName().compareTo(arg0.getName());
	}
	
}
