
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JEditorPane;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class IDE extends JFrame {        /////////////////////////   check sw and lw errors  ....    check all errors 
	 Border Darkline = BorderFactory.createLineBorder(Color.DARK_GRAY);
	private JPanel contentPane;
	private JTextArea registers = new JTextArea();
	JEditorPane assemblycode = new JEditorPane();
	JEditorPane machinecode = new JEditorPane();
	JEditorPane memory = new JEditorPane();
	AssemblyCoder machineCode;
	VirtualMachine VM = new VirtualMachine(); 
	String AssemblyCode="";
	private JTextField ProgramCount;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDE frame = new IDE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IDE() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1350, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 118, 574, 293);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(assemblycode);
		
		
		assemblycode.setFont(new Font("Arial Unicode MS", Font.ITALIC, 14));
		assemblycode.setForeground(Color.BLACK);
		assemblycode.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 468, 574, 182);
		contentPane.add(scrollPane_2);
		machinecode.setEditable(false);
		scrollPane_2.setViewportView(machinecode);
		
		
		
		machinecode.setForeground(Color.BLACK);
		machinecode.setFont(new Font("Arial Unicode MS", Font.ITALIC, 16));
		machinecode.setBorder(Darkline);
		
		
		
		JButton btnNewButton = new JButton("Assemble");
		btnNewButton.setBounds(10, 12, 103, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String message = "No Code typed";
				if(assemblycode.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), message, "Error",
					        JOptionPane.ERROR_MESSAGE);
				} else {
				AssemblyCode = assemblycode.getText();
				machineCode = new AssemblyCoder(AssemblyCode);
				}
			}
			
		});
		contentPane.add(btnNewButton);
		
		JButton btnRun = new JButton("Run All");
		btnRun.setBounds(123, 12, 103, 23);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String coded = machineCode.getMachineCode();
				String message = "No lines Available";
				if(assemblycode.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), message, "Error",
					        JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		contentPane.add(btnRun);
		
		JButton btnFetchdecodeexcute = new JButton("F + D + E");
		btnFetchdecodeexcute.setBounds(239, 12, 154, 23);
		btnFetchdecodeexcute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String message = " End :D !!" ;

				if(VM.syscall) {
					JOptionPane.showMessageDialog(new JFrame(), message, "End Program",
					        JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
				else if( assemblycode.getText().isEmpty()  ) {  
					JOptionPane.showMessageDialog(new JFrame(), message, "Error",
					        JOptionPane.ERROR_MESSAGE);
				  VM.ProgramCounter = 0;
					  
				} else {
				AssemblyCode = machineCode.getLineLine(VM.ProgramCounter);
				machinecode.setText(machinecode.getText() + AssemblyCode);
				VM.virtualMachine(AssemblyCode , machineCode.codelines);
				memory.setText(VM.setMemory());
				registers.setText(VM.setRegisters());
				ProgramCount.setText(Integer.toString(VM.ProgramCounter));
				}
			}
		});
		contentPane.add(btnFetchdecodeexcute);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VM = new VirtualMachine();
				registers.setText(VM.setRegisters());
				memory.setText(VM.setMemory());
				assemblycode.setText("");
				machinecode.setText("");
				ProgramCount.setText("0");
				
				
			}
		});
		btnNewButton_1.setBounds(403, 12, 67, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(627, 56, 378, 594);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(memory);
		memory.setForeground(Color.BLUE);
		
		
		memory.setEditable(false);
		memory.setFont(new Font("Open Sans Semibold", Font.PLAIN, 13));
		memory.setBorder(Darkline);
		memory.setText(VM.setMemory());
		registers.setBounds(1015, 56, 239, 594);
		contentPane.add(registers);
		
		
		registers.setFont(new Font("Open Sans Semibold", Font.PLAIN, 13));
		registers.setForeground(Color.RED);
		registers.setEditable(false);
		registers.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		registers.setText(VM.setRegisters());
		
		JLabel lblMemory = new JLabel("Memory");
		lblMemory.setForeground(Color.DARK_GRAY);
		lblMemory.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblMemory.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemory.setBounds(687, 15, 277, 34);
		contentPane.add(lblMemory);
		
		JLabel lblRegisters = new JLabel("Registers");
		lblRegisters.setForeground(Color.DARK_GRAY);
		lblRegisters.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisters.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblRegisters.setBounds(1002, 15, 239, 34);
		contentPane.add(lblRegisters);
		
		JLabel lblProgramCounter = new JLabel("Program Counter");
		lblProgramCounter.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramCounter.setForeground(Color.DARK_GRAY);
		lblProgramCounter.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProgramCounter.setBounds(10, 50, 162, 34);
		contentPane.add(lblProgramCounter);
		
		JLabel lblMachineCode = new JLabel("Machine Code");
		lblMachineCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblMachineCode.setForeground(Color.DARK_GRAY);
		lblMachineCode.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblMachineCode.setBounds(219, 423, 162, 34);
		contentPane.add(lblMachineCode);
		
		JLabel lblAssemblyCode = new JLabel("Assembly Code");
		lblAssemblyCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssemblyCode.setForeground(Color.DARK_GRAY);
		lblAssemblyCode.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAssemblyCode.setBounds(219, 82, 154, 34);
		contentPane.add(lblAssemblyCode);
		
		ProgramCount = new JTextField();
		ProgramCount.setFont(new Font("Tahoma", Font.BOLD, 16));
		ProgramCount.setHorizontalAlignment(SwingConstants.CENTER);
		ProgramCount.setText("0");
		ProgramCount.setBackground(Color.WHITE);
		ProgramCount.setEditable(false);
		ProgramCount.setBounds(182, 53, 50, 29);
		contentPane.add(ProgramCount);
		ProgramCount.setColumns(10);
	}
}
