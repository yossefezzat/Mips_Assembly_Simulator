

public class VirtualMachine {

	Registers registers ;
	MemData [] memory;
	Integer ProgramCounter;
	boolean syscall;
	boolean jalFlag;
	
	public VirtualMachine() {
		this.syscall = false;
		this.jalFlag = false;
		this.ProgramCounter = 0;
		this.registers = new Registers();
		this.registers.setRegistersMain();
		memory = new MemData[50];
		for(int i = 0 ; i < 50 ; i++) {
			this.memory[i] = new MemData();
		}
		
	}
	
	
	public int getRegister(String regCode){
		int index = -1;
		for( index = 0  ; index < this.registers.registers.size() ; index++) {
			if(this.registers.registers.get(index).code.equals(regCode)) {
				return index; 
			}
		}
		return index;
	}
	
	public String setRegisters() {
		String regs = "";
		for(int i = 0 ; i < registers.registers.size() ; i++ ) {
			regs += "  " + registers.registers.get(i).name + "\t" + registers.registers.get(i).code +"\t" + registers.registers.get(i).valueReg + "\n";
		}
		return regs;
	}
	
	public String setMemory() {
		String mem = "";
		for(int i=0 ; i < this.memory.length; i++) {
			mem += " " +"0x"+ this.memory[i].hexa+ Integer.toHexString((i*4)+4096) + "\t   " + this.memory[i].value + "\n";
		}
		return mem;
	}
	
	
	public Integer binaryTodecimal(String imm) {
		Integer decimal = Integer.parseInt(imm, 2);
		return decimal;
	}
	
	
	/********************************* Syscall **********************************/
	
	public void syscall() {
		int indexRegv0 = getRegister("00010");
		Data Reg1 = this.registers.registers.get(indexRegv0);
		if(Reg1.valueReg == 10) {
			this.syscall = true;
		}
		
	}
	
	/***************************** Start I-type *********************************/
	
	public void addi(String reg1Code , String reg2Code , String immediate) {
		int indexReg1 = getRegister(reg1Code);
		int indexReg2 = getRegister(reg2Code);
		Integer value = binaryTodecimal(immediate);
		
		Data reg1 = this.registers.registers.get(indexReg1);
		
		Data reg2 = this.registers.registers.get(indexReg2);
		
		reg1.valueReg = reg2.valueReg + value;
		
		this.registers.registers.set(indexReg1, reg1);
	}
	
	public void andi(String reg1Code , String reg2Code , String immediate) {
		int indexReg1 = getRegister(reg1Code);
		int indexReg2 = getRegister(reg2Code);
		Integer value = binaryTodecimal(immediate);
	
		Data reg1 = this.registers.registers.get(indexReg1);
		Data reg2 = this.registers.registers.get(indexReg2);
		
		reg1.valueReg = reg2.valueReg & value;
		this.registers.registers.set(indexReg1, reg1);
	}
	
	public void ori(String reg1Code , String reg2Code , String immediate) {
		int indexReg1 = getRegister(reg1Code);
		int indexReg2 = getRegister(reg2Code);
		Integer value = binaryTodecimal(immediate);
		
		Data reg1 = this.registers.registers.get(indexReg1);
		Data reg2 = this.registers.registers.get(indexReg2);
		
		reg1.valueReg = reg2.valueReg | value;
		
		this.registers.registers.set(indexReg1, reg1);
	}
	public void slti(String reg1Code , String reg2Code , String immediate) {
		int indexReg1 = getRegister(reg1Code);
		int indexReg2 = getRegister(reg2Code);
		Integer value = binaryTodecimal(immediate);
		
		Data reg1 = this.registers.registers.get(indexReg1);
		Data reg2 = this.registers.registers.get(indexReg2);
		
		boolean check = reg2.valueReg <  value;
		if(check) 
			reg1.valueReg = 1;
		else
			reg1.valueReg = 0; 
	}
	
	public void lw(String reg1Code , String reg2Code , String immediate) {
		int indexReg1 = getRegister(reg1Code);
		Integer value = binaryTodecimal(immediate);
		Data reg1 = this.registers.registers.get(indexReg1);

		Integer indexInMem = value/4;  // index in memory in array of memory data 
		MemData memIndex  = memory[indexInMem];
		reg1.valueReg = Integer.parseInt(memIndex.value , 2);
		this.registers.registers.set(indexReg1, reg1);
		
	}
	
	public void sw(String reg1Code , String reg2Code , String immediate) {
		int indexReg1 = getRegister(reg1Code);
		Data reg1 = this.registers.registers.get(indexReg1);
		
		Integer indexInMem = Integer.parseInt(immediate , 2);  // index in memory in array of memory data
		MemData memoryline = memory[indexInMem/4];  // get the line of memory of immediate String to decimal 
		
		memoryline.to32bitBinary(reg1.valueReg);
		memory[indexInMem/4] = memoryline;
	}
	
	/*****************************  End I-type	*********************************/
	
	/***************************** Start R-type *********************************/
	
	public void add(String reg1Code , String reg2Code , String reg3Code) {
		
		int indexReg1 = getRegister(reg1Code);
		int indexReg2 = getRegister(reg2Code);
		int indexReg3 = getRegister(reg3Code);
		
		
		Data reg1 = this.registers.registers.get(indexReg1);
		Data reg2 = this.registers.registers.get(indexReg2);
		Data reg3 = this.registers.registers.get(indexReg3);
		reg1.valueReg = reg2.valueReg + reg3.valueReg ;
		
		this.registers.registers.set(indexReg1, reg1);
	}
	
	public void sub(String reg1Code , String reg2Code , String reg3Code) {
		
		int indexReg1 = getRegister(reg1Code);
		int indexReg2 = getRegister(reg2Code);
		int indexReg3 = getRegister(reg3Code);
		
		Data reg1 = this.registers.registers.get(indexReg1);
		Data reg2 = this.registers.registers.get(indexReg2);
		Data reg3 = this.registers.registers.get(indexReg3);
		
		reg1.valueReg = reg2.valueReg - reg3.valueReg ;
		
		this.registers.registers.set(indexReg1, reg1);
	}
	
	public void and(String reg1Code , String reg2Code , String reg3Code) {
		int indexReg1 = getRegister(reg1Code);
		int indexReg2 = getRegister(reg2Code);
		int indexReg3 = getRegister(reg3Code);
		
		
		Data reg1 = this.registers.registers.get(indexReg1);
		Data reg2 = this.registers.registers.get(indexReg2);
		Data reg3 = this.registers.registers.get(indexReg3);
		
		reg1.valueReg = reg2.valueReg & reg3.valueReg;
		this.registers.registers.set(indexReg1, reg1);
	}
	
	public void or(String reg1Code , String reg2Code , String reg3Code) {
		int indexReg1 = getRegister(reg1Code);
		int indexReg2 = getRegister(reg2Code);
		int indexReg3 = getRegister(reg3Code);
		
		
		Data reg1 = this.registers.registers.get(indexReg1);
		Data reg2 = this.registers.registers.get(indexReg2);
		Data reg3 = this.registers.registers.get(indexReg3);
		
		reg1.valueReg = reg2.valueReg | reg3.valueReg;
		this.registers.registers.set(indexReg1, reg1);
	}
	
	public void slt(String reg1Code , String reg2Code , String reg3Code) {
		
		int indexReg1 = getRegister(reg1Code);
		int indexReg2 = getRegister(reg2Code);
		int indexReg3 = getRegister(reg3Code);
		
		Data reg1 = this.registers.registers.get(indexReg1);
		Data reg2 = this.registers.registers.get(indexReg2);
		Data reg3 = this.registers.registers.get(indexReg3);
		
		boolean check = reg2.valueReg < reg3.valueReg ;
		if(check)
			reg1.valueReg = 1;
		else 
			reg1.valueReg = 0;
		this.registers.registers.set(indexReg1, reg1);
	}
	
	public void jal(String address) {
		System.out.println("jal index : " + this.ProgramCounter );
		int index_ra = this.getRegister("11111");
		Data registerRa = this.registers.registers.get(index_ra);
		registerRa.valueReg = this.ProgramCounter+1 ;
 		Integer jalTo = Integer.parseInt(address , 2);
		this.ProgramCounter = (jalTo / 4)-1 ;
		this.registers.registers.set(index_ra, registerRa);
		jalFlag = true;
	}
	
	public void sll(String reg1Code , String reg2Code , String shmt) {
		
		int indexReg1 = getRegister(reg1Code);
		int indexReg2 = getRegister(reg2Code);
		int shamtValue = this.binaryTodecimal(shmt);
		Data reg1 = this.registers.registers.get(indexReg1);
		Data reg2 = this.registers.registers.get(indexReg2);
		
		reg1.valueReg = reg2.valueReg << shamtValue ;
		
		this.registers.registers.set(indexReg1, reg1);
	}
	
	public void jr(String reg1Code) {
		int indexReg1 = getRegister(reg1Code);
		Data reg1 = this.registers.registers.get(indexReg1);
		this.ProgramCounter = reg1.valueReg-1;
		
	}
	
	/*********************************** End R-Type  *************************************/
	

	
	
	public void virtualMachine(String machineCode , String []codelines) {
		machineCode = machineCode.trim(); // remove the last space in the string of machine code :D <3 7sbayah w n3m el wakel  
		String [] machineCodeParts = machineCode.split(" ");
		boolean j_type = false;
		
	/************************************* J-type *********************************************/	
		
		if(machineCodeParts[0].equals("000011")) {
			j_type = true ;
			this.jal(machineCodeParts[1]);
		}
			
	/************************************* J-type *********************************************/	
		
		else if(  this.ProgramCounter == Integer.parseInt(machineCodeParts[0] , 2)/4 && codelines[Integer.parseInt(machineCodeParts[0] , 2)/4].trim().equals("syscall") ) {
			System.out.println(Integer.parseInt(machineCodeParts[0] , 2)/4);
		    System.out.println("syscall here ");
		    this.syscall();
	   }
		
	/************************************* I-Type *********************************************/
		
		else if(machineCodeParts[0].equals("001000")) {
			this.addi(machineCodeParts[1], machineCodeParts[2], machineCodeParts[3]);
		}
		else if(machineCodeParts[0].equals("001100")) {
			this.andi(machineCodeParts[1], machineCodeParts[2], machineCodeParts[3]);
		}
		else if(machineCodeParts[0].equals("001101")) {
			this.ori(machineCodeParts[1], machineCodeParts[2], machineCodeParts[3]);
		}
		else if(machineCodeParts[0].equals("001010")) {
			this.slti(machineCodeParts[1], machineCodeParts[2], machineCodeParts[3]);
		}
		else if(machineCodeParts[0].equals("010011")) {
			this.lw(machineCodeParts[2], machineCodeParts[1], machineCodeParts[3]);
		}
		else if(machineCodeParts[0].equals("101011")) {
			this.sw(machineCodeParts[2], machineCodeParts[1], machineCodeParts[3]);
		}
		
	/************************************* R-Type *******************************************/
		
		// ay trim();  bsheel feha ay space b3d el parsing bta3 el machinecode t2keed 2n mfesh space 
		else if(machineCodeParts[0].equals("000000") && machineCodeParts[machineCodeParts.length-1].equals("100000")) {
			this.add(machineCodeParts[3].trim(), machineCodeParts[2].trim(), machineCodeParts[1].trim());
		}
		
		else if(machineCodeParts[0].equals("000000") && machineCodeParts[machineCodeParts.length-1].equals("100010")) {
			this.sub(machineCodeParts[3].trim(), machineCodeParts[2].trim(), machineCodeParts[1].trim());
		}
		
		else if(machineCodeParts[0].equals("000000") && machineCodeParts[machineCodeParts.length-1].equals("100100")) {
			this.and(machineCodeParts[3].trim(), machineCodeParts[2].trim(), machineCodeParts[1].trim());
		}
		
		else if(machineCodeParts[0].equals("000000") && machineCodeParts[machineCodeParts.length-1].equals("100101")) {
			this.and(machineCodeParts[3].trim(), machineCodeParts[2].trim(), machineCodeParts[1].trim());
		}
		
		else if(machineCodeParts[0].equals("000000") && machineCodeParts[machineCodeParts.length-1].equals("000000")) {
			this.sll(machineCodeParts[2].trim(), machineCodeParts[1].trim(), machineCodeParts[4].trim());
		}
		
		else if(machineCodeParts[0].equals("000000") && machineCodeParts[machineCodeParts.length-1].equals("101010")) {
			this.slt(machineCodeParts[2].trim(), machineCodeParts[1].trim(), machineCodeParts[3].trim());
		}
		else if(machineCodeParts[0].equals("000000") && machineCodeParts[machineCodeParts.length-1].equals("001000")) {
			this.jr(machineCodeParts[2].trim());  // edit register in function
		}
		if(!jalFlag)
			this.ProgramCounter++;
		this.jalFlag = false;
	}
	
}
