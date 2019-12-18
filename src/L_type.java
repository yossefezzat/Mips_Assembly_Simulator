import java.util.ArrayList;

public class L_type {
	ArrayList<String> machineCode; 
	String [] lines; 
	
	
	public L_type(String [] lines) {
		// TODO Auto-generated constructor stub
		this.machineCode = new ArrayList<String>();
		this.lines = lines;
	}
	
	public String to32bits(int index) {
		String tobinary = "";
		try {
			tobinary = Integer.toBinaryString(index*4);
			for(int i = tobinary.length() ; i < 32 ; i++) {
				tobinary = "0" + tobinary;
			}
		} catch (Exception e) {
		}
		return tobinary;
	}
	
	public ArrayList<String> evaluateL(instructionData rType){
		for(int i = 0 ; i < this.lines.length ; i++) {
			if(this.lines[i].trim().equals(rType.immediate)) {
				this.machineCode.add(to32bits(i));
				break;
			}
		}
		
		return this.machineCode;
	}
	
}
