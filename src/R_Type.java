import java.util.ArrayList;

/*
 * add
 * sub
 * and 
 * or
 * sll
 * slt
 * jr
 */

public class R_Type {
	ArrayList<String> machineCode;
	ArrayList<Data> allRegisters;
	Registers registers;

	public R_Type() {
		// TODO Auto-generated constructor stub
		machineCode = new ArrayList<String>();
		allRegisters = new ArrayList<Data>();
		registers = new Registers();
		allRegisters = registers.setRegistersMain(); // set all registers in allRegisters ArrayList
	}

	public String to5bits(String decimal) {
		if (decimal.isEmpty()) {
			return "0";
		}
		String binary = Integer.toBinaryString(Integer.parseInt(decimal));
		for (int i = binary.length(); i < 5; i++) {
			binary = "0" + binary;
		}
		return binary;
	}
	
	public String to6bits(String decimal) {
		if (decimal.isEmpty()) {
			return "0";
		}
		String binary = Integer.toBinaryString(Integer.parseInt(decimal));
		for (int i = binary.length(); i < 6; i++) {
			binary = "0" + binary;
		}
		return binary;
	}

	public String findRegister(String reg) {
		String registerCode = "00000";
		for (Data r : this.allRegisters) {
			if (r.name.equals(reg)) {
				registerCode = r.code;
			}
		}
		return registerCode;
	}

	public ArrayList<String> evaluateR(instructionData rType) {
		machineCode.add("000000"); // opcode
		
		String shamt = "00000";  String shmt$ra = "00000";
		String function;
		shamt = to5bits(rType.shmt);
		function = to6bits(rType.func);
		
		 // rs register
		
		
		if (rType.operand.equals("add")) {
			machineCode.add(findRegister(rType.rs));
			machineCode.add(findRegister(rType.rt));
			machineCode.add(findRegister(rType.rd));
			machineCode.add("00000");
			machineCode.add(function);
		}
		else if (rType.operand.equals("sub")) {
			machineCode.add(findRegister(rType.rs));
			machineCode.add(findRegister(rType.rt));
			machineCode.add(findRegister(rType.rd));
			machineCode.add("00000");
			machineCode.add(function);
		}
		else if (rType.operand.equals("and")) {
			machineCode.add(findRegister(rType.rs));
			machineCode.add(findRegister(rType.rt));
			machineCode.add(findRegister(rType.rd));
			machineCode.add("00000");
			machineCode.add(function);
		}
		else if (rType.operand.equals("or")) {
			machineCode.add(findRegister(rType.rs));
			machineCode.add(findRegister(rType.rt));
			machineCode.add(findRegister(rType.rd));
			machineCode.add("00000");
			machineCode.add(function);
		}
		else if (rType.operand.equals("sll")) {
			machineCode.add(findRegister(rType.rs));
			machineCode.add("00000");
			machineCode.add(findRegister(rType.rd));
			machineCode.add(shamt);
			machineCode.add(function);
		}
		else if (rType.operand.equals("slt")) {
			machineCode.add(findRegister(rType.rs));
			machineCode.add(findRegister(rType.rt));
			machineCode.add(findRegister(rType.rd));
			machineCode.add("00000");
			machineCode.add(function);
		}
		else if (rType.operand.equals("jr")) {
			machineCode.add("00000");
			machineCode.add(findRegister(rType.rt));
			machineCode.add("00000");
			machineCode.add("00000");
			machineCode.add(function);
		}
		
		return machineCode;
	}

}
