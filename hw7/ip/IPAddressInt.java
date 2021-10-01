package il.ac.tau.cs.software1.ip;

import java.nio.ByteBuffer;

public class IPAddressInt implements IPAddress {
	private int address;

	IPAddressInt(int address) {
		this.address = address;
	}

	@Override
	public String toString() {
		String result = String.format("%1$d.%2$d.%3$d.%4$d",this.getOctet(0),this.getOctet(1),this.getOctet(2),this.getOctet(3));
		return result;
		
	}

	@Override
	public boolean equals(IPAddress other) {
		return this.toString().equals(other.toString());
	}

	@Override
	public int getOctet(int index) {

		
		ByteBuffer b = ByteBuffer.allocate(4);
		b.putInt(this.address);
		byte octet = b.get(index);
		int i = (int) (octet & 0xFF);
		return i;
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
