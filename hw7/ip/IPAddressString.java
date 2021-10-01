package il.ac.tau.cs.software1.ip;

public class IPAddressString implements IPAddress {
	private String address;

	IPAddressString(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return this.address;
	}

	@Override
	public boolean equals(IPAddress other) {
		return this.address.equals(other.toString());
	}

	@Override
	public int getOctet(int index) {
		String[] octets = this.address.split("\\.");
		int octet = Integer.parseInt(octets[index]);
		return octet;
	}

	@Override
	public boolean isPrivateNetwork(){
		if(this.getOctet(0) == 10) {
			return true;
		}
		if(this.getOctet(0) == 172 && 16 <= this.getOctet(1) && this.getOctet(1) >= 31) {
			return true;
		}
		if((this.getOctet(0) == 192 && this.getOctet(1) == 168) || (this.getOctet(0) == 169 && this.getOctet(1) == 254)) {
			return true;
		}
		return false;
	}
	
}
