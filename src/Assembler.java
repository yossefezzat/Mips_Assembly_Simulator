import java.util.Scanner;

public class Assembler {
	
	public String codeAssembly;	
	public String []lines;

	
	public Assembler() {
	}
	
	public String[] codeSpliiter(String assemblyCode){
		
		lines  = assemblyCode.split("\n");
		return lines;
	}
	
	
	public void runInstructions(String [] parsedInstructions) {
		
		for(int i=0 ; i < parsedInstructions.length ;i++) {
			if(parsedInstructions[i].equals("") || parsedInstructions[i].equals(" ") ) {
				continue;
			}
		}
	}
	
//	public static void main(String []args) {
//		
//		String input = null;
//		input = "add $a,$v,$s \nsub $hf ,$hfj ,$j \n \nadd he $hd jd\n\nhello $f $fj";
//		String []out = codeSpliiter(input);
//		
//		//codeSpliiter();
//		//codeSpliiter();
//	}
	
	
}
