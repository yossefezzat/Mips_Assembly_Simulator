import java.util.ArrayList;


public class I_Type {
	
		String op , rs= "00000" , rt , addressOrConstant="0000000000000000" ;  // op = 6 bits + rs = 5 bits + rt = 5 bits + address = 16 bits =    32 bits
		String [] lines; 
		ArrayList<String>machineCode;
		ArrayList<Data> allRegisters;
		Integer immediate;
		Registers registers;

		public I_Type(String []lines) {  // to get the label of the beq and bne to jumb to address
			// TODO Auto-generated constructor stub
			machineCode = new ArrayList<String>();
			allRegisters = new ArrayList<Data>();
			registers = new Registers();
			allRegisters = registers.setRegistersMain(); // set all registers in allRegisters ArrayList
			this.lines = lines;
		}
		
		public  String to5bits(String decimal){
			if(decimal.isEmpty()) {
				return "0";
			}
			String binary = Integer.toBinaryString(Integer.parseInt(decimal));
			for(int i = binary.length()  ; i < 5 ; i++ ) {
				binary = "0" + binary;
			}
			return binary;
		}
		
		public String findRegister(String reg){
			String registerCode = "00000";
			for(Data r : this.allRegisters) {
				if(r.name.equals(reg)) {
					registerCode = r.code;
				}
			}
			return registerCode;
		}
		
	public ArrayList<String> evaluateI(instructionData rType){
		
		
		
		if(rType.operand.equals("addi")) {
			machineCode.add("001000");
			machineCode.add(findRegister(rType.rt));
			machineCode.add(findRegister(rType.rs));
		}
		else if(rType.operand.equals("andi")) {
			machineCode.add("001100");
			machineCode.add(findRegister(rType.rt));
			machineCode.add(findRegister(rType.rs));
		}
		else if(rType.operand.equals("ori")) {
			machineCode.add("001101");
			machineCode.add(findRegister(rType.rt));
			machineCode.add(findRegister(rType.rs));
		}
		else if(rType.operand.equals("slti")) {
			machineCode.add("001010");
			machineCode.add(findRegister(rType.rt));
			machineCode.add(findRegister(rType.rs));
		}
		else if(rType.operand.equals("beq")) {
			machineCode.add(findRegister(rType.rs));
			machineCode.add(findRegister(rType.rt));
			machineCode.add("000100");
		}
		else if(rType.operand.equals("bne")) {
			machineCode.add("000101");
			machineCode.add(findRegister(rType.rs));
			machineCode.add(findRegister(rType.rt));
		}
		else if(rType.operand.equals("lw")) {
			machineCode.add("010011");
			System.out.println(rType.rs);
			machineCode.add(findRegister(rType.rs));
			System.out.println(rType.rt);
			machineCode.add(findRegister(rType.rt));
		}
		else if(rType.operand.equals("sw")) {
			machineCode.add("101011");
			machineCode.add(findRegister(rType.rs));
			machineCode.add(findRegister(rType.rt));
		}
		else if(rType.operand.equals("lui")) {
			machineCode.add("001111");
			machineCode.add(findRegister(rType.rt));
		}
		else {
			System.out.println("error in I instruction !!");
		}
		
		
		if(rType.immediate.matches("\\d+")) {
			immediate = Integer.parseInt(rType.immediate);
			addressOrConstant = Integer.toBinaryString(immediate);
			for (int i = this.addressOrConstant.length(); i < 16; i++) {
                this.addressOrConstant = "0" + this.addressOrConstant;
            }
		} else {  // beq  ,  bne  check for label in immediate  
			for(int i = 0 ; i < this.lines.length ;i++) {
				if(this.lines[i].equals(rType.immediate)) {
					addressOrConstant = Integer.toBinaryString(i);
					for (int j = 0 ; j < 16; j++) {
		                this.addressOrConstant = "0" + this.addressOrConstant;
		            }
				}
			}
			if(this.addressOrConstant.equals("0000000000000000")) {
				rType.immediate = "-"; // if label is not in the code return in immediate "-" to be detected 
			}
		}
		
		machineCode.add(this.addressOrConstant);
		

		return machineCode;
	}
}
