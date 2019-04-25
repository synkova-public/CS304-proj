package cs304;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField idField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 478, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Criminal Record Database");
		lblNewLabel.setBounds(158, 26, 136, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setBounds(99, 104, 21, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel passWord = new JLabel("Password:");
		passWord.setBounds(99, 157, 60, 13);
		frame.getContentPane().add(passWord);
		
		idField = new JTextField();
		idField.setBounds(158, 101, 156, 19);
		frame.getContentPane().add(idField);
		idField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(158, 154, 156, 19);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(idField.getText().equals("admin")) {
					String passText = new String(passwordField.getPassword());
					if(passText.equals("admin")) {
						FrontEnd window = new FrontEnd();
						window.frame.setVisible(true);
						frame.dispose();
						//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					}
				} else if(idField.getText().equals("public")) {
					FrontEnd window = new FrontEnd();
					window.tabbedPane.removeTabAt(0);
					window.frame.setVisible(true);
					frame.dispose();
				}
				else {
					JFrame frmLoginSystem = new JFrame("Exit");
					if(JOptionPane.showConfirmDialog(frmLoginSystem,  "Wrong password or username. Use public for public access", "Login Systems", 
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					}
				}
				
			}
		});
		btnLogin.setBounds(59, 260, 85, 21);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idField.setText(null);
				passwordField.setText(null);
			}
		});
		btnReset.setBounds(195, 260, 85, 21);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frmLoginSystem = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginSystem,  "Confirm if you want to exit", "Login Systems", 
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBounds(336, 260, 85, 21);
		frame.getContentPane().add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(59, 229, 363, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(59, 75, 363, 2);
		frame.getContentPane().add(separator_1);
	}
}
