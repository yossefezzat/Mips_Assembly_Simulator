import java.util.ArrayList;

public class J_Type {

	ArrayList<String> machineCode;
	String [] codelines ;

	public J_Type(String []codelines) {
		// TODO Auto-generated constructor stub
		machineCode = new ArrayList<String>();
		this.codelines = codelines;
	}
	
	public String to26bitBinary(int decimal) {
		
		String tobinary = Integer.toBinaryString(decimal);
		for(int i = tobinary.length() ; i < 26 ; i++) {
			tobinary = "0" + tobinary;
		}
		return tobinary;
	}
	
	public int indexJumb(String label) {
		int index = 0 ; 
		for(int i = 0 ; i < this.codelines.length ; i++) {
			if(codelines[i].trim().equals((label+":"))) {
				index = i;
				break;
			}
		}
		return index;
	}

	public ArrayList<String> evaluateJ(instructionData JType) {
		
		if(JType.operand.equals("jal")) {
			machineCode.add("000011");
			machineCode.add(to26bitBinary((indexJumb(JType.address.trim())+1)*4 ));
			
		}
		else if(JType.operand.equals("j")) {
			machineCode.add("000010");
			machineCode.add(to26bitBinary(indexJumb(JType.address)));
		
		}
		return this.machineCode;
	}
}
