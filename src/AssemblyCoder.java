import java.util.ArrayList;

public class AssemblyCoder {
	ArrayList<InstructionParser> instructions;
	Assembler assembly;
	String[] codelines;

	public AssemblyCoder(String code) {
		// TODO Auto-generated constructor stub
		instructions = new ArrayList<InstructionParser>();
		assembly = new Assembler();
		this.codelines = assembly.codeSpliiter(code);
		evaluateInstructions(this.codelines);
	}

	public void evaluateInstructions(String[] lines) {
		InstructionParser inst;
		for (int i = 0; i < lines.length; i++) {
			inst = new InstructionParser(lines[i], i);
			inst.runInstruction(inst.tempList);
//			if (validate == false) {
//				System.out.println(" error in code line number: " + (i+1));
//				break;
//			}
			this.instructions.add(inst);
		}
	}

	public String getMachineCode() {
		String allmachineCode = "";

		I_Type objI;
		R_Type objR;
		ArrayList<String> parse;

		for (int i = 0; i < this.instructions.size(); i++) {
			parse = new ArrayList<String>();
			if (this.instructions.get(i).lineCode.type.equals("R")) {
				objR = new R_Type();
				parse = objR.evaluateR(this.instructions.get(i).lineCode);

			} else if (this.instructions.get(i).lineCode.type.equals("I")) {
				objI = new I_Type(this.codelines);
				parse = objI.evaluateI(this.instructions.get(i).lineCode);

			} else {
				System.out.println("not defined a type !! ");
			}
			for (String str : parse) {
				allmachineCode += str + " ";
			}
			allmachineCode += "\n";
		}
		return allmachineCode;
	}

	public String getLineLine(int i) {
		String machineCodeLine = "";
		ArrayList<String> parse = new ArrayList<String>();
		I_Type objI;
		R_Type objR;
		L_type objL;
		J_Type objJ;

		if (this.instructions.get(i).lineCode.type.equals("R")) {
			objR = new R_Type();
			parse = objR.evaluateR(this.instructions.get(i).lineCode);

		} else if (this.instructions.get(i).lineCode.type.equals("I")) {
			objI = new I_Type(this.codelines);
			parse = objI.evaluateI(this.instructions.get(i).lineCode);

		} else if (this.instructions.get(i).lineCode.type.equals("L")){
			objL = new L_type(this.codelines);
			parse = objL.evaluateL(this.instructions.get(i).lineCode);
			
		} else if (this.instructions.get(i).lineCode.type.equals("J")){
			objJ = new J_Type(this.codelines);
			parse = objJ.evaluateJ(this.instructions.get(i).lineCode);
		} else {
			System.out.println("not defined a type !! ");
		}
		for (String str : parse) {
			if(!(this.instructions.get(i).lineCode.type.equals("L")))
				machineCodeLine += str + " ";
			else 
				machineCodeLine = str;
		}
		if(!(i == this.instructions.size()-1) )  /// because it add in last line
			machineCodeLine += "\n";
		
		return machineCodeLine;
	}

//	public static void main(String[] args) {
//		String code = "sw $s0, ($ra)\naddi $s0 , $s1 , 4\nandi $t0 , $t2 , 2";
//		AssemblyCoder asm = new AssemblyCoder();
//		System.out.println(asm.getMachineCode(code));
//	}

}
