
public class MemData {

	String value ; 
	String hexa ;
	
	public MemData() {
		// TODO Auto-generated constructor stub
		this.value = "00000000000000000000000000000000";
		this.hexa = "0000";
	}
	
	public void to32bitBinary(int decimal) {
		String tobinary = Integer.toBinaryString(decimal);
		for(int i = tobinary.length() ; i < 32 ; i++) {
			tobinary = "0" + tobinary;
		}
		this.value = tobinary;
	}
}
