import java.util.ArrayList;


/*
 * Parse all the input instruction 
 * 
 * @get all the instructions for assembler to run 
 * 
 * @show errors for every line if exist 
 * 
 * lets go !! :)
 * 
 */

public class InstructionParser {

	String instruction;
	ArrayList<String> tempList;
	instructionData lineCode;

	public boolean formatter(int num) {
		if (tempList.get(num).matches("[$][s][0-7]"))
			return true;
		if (tempList.get(num).matches("[$][t][0-9]"))
			return true;
		if (tempList.get(num).matches("[$][a][0-3]"))
			return true;
		if (tempList.get(num).matches("[$][v][01]"))
			return true;
		if (tempList.get(num).equals("$sp"))
			return true;
		if (tempList.get(num).equals("$ra"))
			return true;
		return false;
	}

	public InstructionParser(String inputLine, int lineNumber) {
		// TODO Auto-generated constructor stub
		this.instruction = "";
		lineCode = new instructionData();
		tempList = new ArrayList<String>();
		String[] splitedLine;
		lineCode.lineNumber = lineNumber;
		splitedLine = inputLine.split("( )|(,)");
		for (int i = 0; i < splitedLine.length; i++) {
			if (splitedLine[i].equals(" ") || splitedLine[i].equals("\\t") || splitedLine[i].equals("")) {
				continue;
			}
			tempList.add(splitedLine[i]);
		}

//        for(String ss : tempList) {
//        	System.out.println(ss);
//        }

	}

	public boolean check4Var(ArrayList<String> input) {
		/************* start of R type of 4 parameters **************/

		if (input.get(0).equals("add")) {
			lineCode.type = "R";
			lineCode.operand = "add";
			lineCode.rd = input.get(1);
			lineCode.rs = input.get(2);
			lineCode.rt = input.get(3);
			lineCode.shmt = "0";
			lineCode.func = "32";
		} else if (input.get(0).equals("sub")) {
			lineCode.type = "R";
			lineCode.operand = "sub";
			lineCode.rd = input.get(1);
			lineCode.rs = input.get(2);
			lineCode.rt = input.get(3);
			lineCode.shmt = "0";
			lineCode.func = "34";
		} else if (input.get(0).equals("and")) {
			lineCode.type = "R";
			lineCode.operand = "and";
			lineCode.rd = input.get(1);
			lineCode.rs = input.get(2);
			lineCode.rt = input.get(3);
			lineCode.shmt = "0";
			lineCode.func = "36";
		} else if (input.get(0).equals("or")) {
			lineCode.type = "R";
			lineCode.operand = "or";
			lineCode.rd = input.get(1);
			lineCode.rs = input.get(2);
			lineCode.rt = input.get(3);
			lineCode.shmt = "0";
			lineCode.func = "37";
		} else if (input.get(0).equals("sll")) {
			lineCode.type = "R";
			lineCode.operand = "sll";
			lineCode.imm = true;
			lineCode.rd = input.get(1);
			lineCode.rs = input.get(2);
			lineCode.shmt = input.get(3);
			lineCode.func = "0";
		} else if (input.get(0).equals("slt")) {
			lineCode.type = "R";
			lineCode.operand = "slt";
			lineCode.rd = input.get(1);
			lineCode.rs = input.get(2);
			lineCode.rt = input.get(3);
			lineCode.shmt = "0";
			lineCode.func = "42";
		}

		/************* end of R type of 4 parameters **************/

		/************* start of I type of 4 parameters **************/

		else if (input.get(0).equals("addi")) {
			lineCode.type = "I";
			lineCode.operand = "addi"; // 001000
			lineCode.imm = true;
			lineCode.rt = input.get(1);
			lineCode.rs = input.get(2);
			lineCode.immediate = input.get(3);
			lineCode.func = "0";
		} else if (input.get(0).equals("andi")) {
			lineCode.type = "I";
			lineCode.operand = "andi"; // 001100
			lineCode.imm = true;
			lineCode.rt = input.get(1);
			lineCode.rs = input.get(2);
			lineCode.immediate = input.get(3);
			lineCode.func = "0";
		} else if (input.get(0).equals("ori")) {
			lineCode.type = "I";
			lineCode.operand = "ori"; // 001101
			lineCode.imm = true;
			lineCode.rt = input.get(1);
			lineCode.rs = input.get(2);
			lineCode.immediate = input.get(3);
			lineCode.func = "0";
		} else if (input.get(0).equals("beq")) {
			lineCode.type = "I";
			lineCode.operand = "beq"; // 000100
			lineCode.labelCheck = true;
			lineCode.rs = input.get(1);
			lineCode.rt = input.get(2);
			lineCode.imm = true;
			lineCode.immediate = input.get(3);
			lineCode.func = "0";
		} else if (input.get(0).equals("bne")) {
			lineCode.type = "I";
			lineCode.operand = "bne"; // 000101
			lineCode.labelCheck = true;
			lineCode.rs = input.get(1);
			lineCode.rt = input.get(2);
			lineCode.immediate = input.get(3);
			lineCode.func = "0";
		} else if (input.get(0).equals("slti")) {
			lineCode.type = "I";
			lineCode.operand = "slti"; // 001010
			lineCode.imm = true;
			lineCode.rt = input.get(1);
			lineCode.rs = input.get(2);
			lineCode.immediate = input.get(3);
			lineCode.func = "0";
		}
		/************* end of I type of 4 parameters **************/
		else {
			return false;
		}
		// to be checked
		if (formatter(1) && formatter(2)) {
			lineCode.rd = input.get(1);
			lineCode.rs = input.get(2);
			input.set(3, input.get(3).trim());
			if (formatter(3) && lineCode.imm == false) {
				lineCode.rt = input.get(3);
				
			} else if (input.get(3).matches("\\d+") && lineCode.imm == true) {
				
				if (input.get(0).equals("sll")) { // handling sll immediate value
					lineCode.shmt = input.get(3);
				} else {
					lineCode.immediate = input.get(3).trim();
				}
			} else if (input.get(3).matches("[a-zA-z]+") && lineCode.labelCheck == true) {
				lineCode.immediate = input.get(3);
			} else {
				System.out.println("error in paramter 3 ");
				return false;
			}

		} else {
			System.out.println("error in register operand  or register 1  ");
			return false;
		}

		return true;
	}

	public boolean check3Var(ArrayList<String> input) {

		if (input.get(0).equals("lw")) {
			lineCode.type = "I";
			lineCode.operand = "lw";
			lineCode.rt = input.get(1);
		} else if (input.get(0).equals("sw")) {
			lineCode.type = "I";
			lineCode.operand = "sw";
			lineCode.rt = input.get(1);
		} else if (input.get(0).equals("lui")) {
			lineCode.type = "I";
			lineCode.operand = "lui";
			lineCode.rt = input.get(1);
		} else {
			return false;
		}

		if (formatter(1)) { // check if paramter 2 is valid of not
			input.set(2, input.get(2).trim());
			if (input.get(2).matches("\\d*[(][$][s][p][)]") || input.get(2).matches("\\d*[(][$][r][a][)]")) {
				
				String[] thirdParam = input.get(2).split("[(]", 2);
				lineCode.immediate = thirdParam[0].trim();
				thirdParam[1] = thirdParam[1].replace(")", "");
				lineCode.rs = thirdParam[1].trim();
				
			}
//			else if (input.get(2).matches("\\d+")) {
//                 lineCode.immediate = input.get(2);
//            } else {
//                System.out.println("3 paramter function error in format code Assembly");
//                 return false;
//			}
		} else {
			System.out.println("3 paramter function error in format code Assembly paramter 1 or 2 ");
			return false;
		}
		return true;
	}

	public boolean check2Var(ArrayList<String> input) {
		if (input.get(0).equals("jal")) {
			lineCode.operand = "jal";
			lineCode.type = "J";
			lineCode.immediate = input.get(1);
		} else if (input.get(0).equals("j")) {
			lineCode.operand = "j";
			lineCode.type = "J";
			lineCode.immediate = input.get(1);
		} else if (input.get(0).equals("jr")) {
			lineCode.operand = "jr";
			lineCode.type = "R";
			lineCode.rt = input.get(1);
			lineCode.func = "8";
		} else {
			return false;
		}

		if (input.get(1).matches("[a-zA-Z]+")) {
			lineCode.address = input.get(1);
		} else if (input.get(1).equals("$ra") && ! lineCode.operand.equals("jal")) {
			lineCode.rt = "$ra";
		} else {
			System.out.println("error in second parameter");
			return false;
		}

		return true;
	}

	public boolean check1Var(ArrayList<String> input) {
		input.set(0, input.get(0).trim());
		if (input.get(0).matches("[a-zA-Z]+[:]")) {    ///handling space to be done !!!
			lineCode.immediate = input.get(0);
			lineCode.type = "L";
		} else if (input.get(0).equals("syscall")) {
			lineCode.immediate = "syscall";
			lineCode.type = "L";
		} else {
			return false;
		}
		return true;
	}

	public boolean runInstruction(ArrayList<String> input) {

		boolean V4 = check4Var(input);
		boolean V3 = check3Var(input);
		boolean V2 = check2Var(input);
		boolean V1 = check1Var(input);

		boolean instruction = V1 || V2 || V3 || V4;

		return instruction;
	}

}
