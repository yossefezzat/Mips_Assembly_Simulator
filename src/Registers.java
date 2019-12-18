import java.util.ArrayList;

public class Registers {
	
	 ArrayList<Data> registers; 
	 
	 public Registers() {
		 registers = new ArrayList<Data>();
	}
	
	public  ArrayList<Data> setRegistersMain() {
		// add Register $zero
		Data regZero = new Data();
		regZero.name = "$0";
		regZero.number = 0;
		regZero.code = "00000";
		registers.add(regZero);
		
		// add Register $at
		Data atReg = new Data();
		atReg.name = "$at";
		atReg.number = 1 ;
		atReg.code = "00001";
		registers.add(atReg);
		
		
		// register v
		for(int i = 2 ; i < 4 ; i++ ) {
			Data objTempV = new Data();
			objTempV.name = "$v" + (i-2);
			objTempV.code = to5bits(Integer.toBinaryString(i));
			objTempV.number = i ;
			registers.add(objTempV);
		}
		
		for(int i = 4 ; i < 8 ; i++ ) {
			Data objTempV = new Data();
			objTempV.name = "$a" + (i-4);
			objTempV.code = to5bits(Integer.toBinaryString(i));
			objTempV.number = i ;
			registers.add(objTempV);
		}
		
		for(int i = 8 ; i < 16 ; i++ ) {
			Data objTempV = new Data();
			objTempV.name = "$t" + (i-8);
			objTempV.code = to5bits(Integer.toBinaryString(i));
			objTempV.number = i ;
			registers.add(objTempV);
		}
		
		for(int i = 16 ; i < 24 ; i++ ) {
			Data objTempV = new Data();
			objTempV.name = "$s" + (i-16);
			objTempV.code = to5bits(Integer.toBinaryString(i));
			objTempV.number = i ;
			registers.add(objTempV);
		}
		
		for(int i = 24 ; i < 26 ; i++ ) {
			Data objTempV = new Data();
			objTempV.name = "$t" + (i-16);
			objTempV.code = to5bits(Integer.toBinaryString(i));
			objTempV.number = i ;
			registers.add(objTempV);
		}
		
		for(int i = 26  ; i < 28 ; i++ ) {
			Data objTempV = new Data();
			objTempV.name = "$k" + (i-26);
			objTempV.code = to5bits(Integer.toBinaryString(i));
			objTempV.number = i ;
			registers.add(objTempV);
		}
		
		// register $gp
		Data gpReg = new Data();
		gpReg.name = "$gp";
		gpReg.number = 28 ;
		gpReg.code = to5bits(Integer.toBinaryString(28));
		registers.add(gpReg);
		
		// register $sp
		Data spReg = new Data();
		spReg.name = "$sp";
		spReg.number = 29 ;
		spReg.code = to5bits(Integer.toBinaryString(29));
		registers.add(spReg);
		
		// register $fp
		Data fpReg = new Data();
		fpReg.name = "$fp";
		fpReg.number = 29 ;
		fpReg.code = to5bits(Integer.toBinaryString(30));
		registers.add(fpReg);
		
		// register $ra
		Data raReg = new Data();
		raReg.name = "$ra";
		raReg.number = 31 ;
		raReg.code = to5bits(Integer.toBinaryString(31));
		registers.add(raReg);
		
		return registers;
	
	}
	
	public  void print() {
		for(int i=0 ; i < registers.size() ; i++) {
			System.out.println(registers.get(i).name + "  " + registers.get(i).number + "  " + registers.get(i).code);
		}
	}
	
	public  String to5bits(String givenBinary){
		for(int i = givenBinary.length()  ; i < 5 ; i++ ) {
			givenBinary = "0" + givenBinary;
		}
		return givenBinary;
	}
	
}
