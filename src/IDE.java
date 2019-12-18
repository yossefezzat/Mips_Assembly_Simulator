
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class IDE extends JFrame {        /////////////////////////   check sw and lw errors  ....    check all errors 

	private JPanel contentPane;
	private JTextArea textArea = new JTextArea();
	JEditorPane editorPane = new JEditorPane();
	JEditorPane editorPane_1 = new JEditorPane();
	JEditorPane editorPane_2 = new JEditorPane();
	int instructionIndex = 0 ;
	AssemblyCoder machineCode;
	VirtualMachine VM = new VirtualMachine(); 
	String AssemblyCode="";
	
	
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
		setBounds(0, 0, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 118, 574, 339);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(editorPane);
		
		
		editorPane.setFont(new Font("Arial", Font.PLAIN, 14));
		editorPane.setForeground(Color.BLACK);
		editorPane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 468, 574, 182);
		contentPane.add(scrollPane_2);
		scrollPane_2.setViewportView(editorPane_1);
		
		
		
		editorPane_1.setForeground(Color.BLACK);
		editorPane_1.setFont(new Font("Arial", Font.PLAIN, 14));
		editorPane_1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		
		
		
		JButton btnNewButton = new JButton("Assemble");
		btnNewButton.setBounds(10, 12, 103, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String message = "No Code typed";
				if(editorPane.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), message, "Error",
					        JOptionPane.ERROR_MESSAGE);
				} else {
				AssemblyCode = editorPane.getText();
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
				if(editorPane.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), message, "Error",
					        JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		contentPane.add(btnRun);
		
		JButton button = new JButton("Run Line by Line");
		button.setBounds(239, 12, 154, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String message = " no lines available to run !!" ;
				if(machineCode.instructions.size() == instructionIndex || editorPane.getText().isEmpty()) {
					  JOptionPane.showMessageDialog(new JFrame(), message, "Error",
						        JOptionPane.ERROR_MESSAGE);
					  instructionIndex = 0;
					  editorPane_1.setText("");
				} else {
				AssemblyCode = machineCode.getLineLine(instructionIndex);
				editorPane_1.setText(editorPane_1.getText() + AssemblyCode);
				VM.virtualMachine(AssemblyCode);
				editorPane_2.setText(VM.setMemory());
				textArea.setText(VM.setRegisters());
				instructionIndex = instructionIndex + 1;
				}
			}
		});
		contentPane.add(button);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setBounds(403, 12, 67, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(650, 34, 290, 616);
		contentPane.add(scrollPane);
		
		
		editorPane_2.setEditable(false);
		editorPane_2.setFont(new Font("Arial", Font.PLAIN, 11));
		scrollPane.setViewportView(editorPane_2);
		editorPane_2.setText(VM.setMemory());
		
		JEditorPane editorPane_3 = new JEditorPane();
		editorPane_3.setBounds(133, 56, 49, 23);
		contentPane.add(editorPane_3);
		textArea.setBounds(950, 34, 224, 616);
		contentPane.add(textArea);
		
		
		textArea.setFont(new Font("Open Sans", Font.PLAIN, 13));
		textArea.setForeground(Color.RED);
		textArea.setEditable(false);
		textArea.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		textArea.setText(VM.setRegisters());
	}
}
