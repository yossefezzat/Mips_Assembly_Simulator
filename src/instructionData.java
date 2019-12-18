
public class instructionData {
	int lineNumber;
	String type; // all
	String operand ;	//all
	String rs;			// R , I type
	String rt;			// R , I type
	String rd;			// R type  // note :  in J type we used it in $ra to be jumbed to it 
	String func;		// R type
	String shmt;		// R type
	String address;		// J type
	String immediate;	// I type
	boolean imm ; 		//immediate variable checker
	boolean labelCheck ;  // compare instructions e.g : beq , bne
	
	
	public instructionData() {
		// TODO Auto-generated constructor stub
		 this.lineNumber = 0;
		 this.type = "";
		 this.operand ="" ;
		 this.rd = "";
		 this.rs = "";
		 this.rt = "";
		 this.func = "";
		 this.shmt= "";
		 this.address = "";
		 this.immediate = "";
		 this.imm = false;
		 this.labelCheck = false;
	}
}
