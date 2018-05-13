import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginToLibray {

	public JFrame frame;
	private JTextField txtHaslo;
	private JTextField txtLogin;
	private JLabel lblHaso = new JLabel("Has\u0142o:");
	private JLabel lblLogin = new JLabel("Login:");
	private WindowLibray mainWindow;
	public boolean admin;

	
	
	public LoginToLibray(WindowLibray mainWindow) {
		this.mainWindow = mainWindow;
		initialize();
	}

	public void end() {
		frame.dispose();
	}
	
	private void toLog() {
		String login = txtLogin.getText();
		String password = txtHaslo.getText();
		//System.out.println(login + " " + password);
		
		if(admin == true) {
			Admin adm = new Admin(login,password);
			String answerFromLogin = adm.loginPerson();
			
			if("Bad pass".equals(answerFromLogin)) {
				JOptionPane.showMessageDialog(frame,
					    "Niepoprawne dane",
					    "Blad przy logowaniu",
					    JOptionPane.ERROR_MESSAGE);
			}
			else if("Access".equals(answerFromLogin)) {
				JOptionPane.showMessageDialog(frame,
					    "Zalogowano");
				
				mainWindow.signed(adm);
			}
			
			
		}
		else if(admin == false){

			Student std = new Student(login,password);
			String answerFromLogin = std.loginPerson();
			
			if("Bad pass".equals(answerFromLogin)) {
				JOptionPane.showMessageDialog(frame,
					    "Niepoprawne dane",
					    "Blad przy logowaniu",
					    JOptionPane.ERROR_MESSAGE);
			}
			else if("Access".equals(answerFromLogin)) {
				JOptionPane.showMessageDialog(frame,
					    "Zalogowano");
				
				mainWindow.signed(std);
			}
		}
		else {
			JOptionPane.showMessageDialog(frame,
				    "ERROR",
				    "Nie wybrano opcji logowania",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblLoggin = new JLabel("Logowanie");
		lblLoggin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLoggin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoggin.setBounds(157, 13, 150, 35);
		frame.getContentPane().add(lblLoggin);

		JButton btnZaloguj = new JButton("zaloguj");
		btnZaloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				toLog();
			}
		});
		JButton btnAdministrator = new JButton("Administrator");
		JButton btnCofnij = new JButton("Cofnij");
		JButton btnStudent = new JButton("Student");
		
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdministrator.setVisible(false);
				btnStudent.setVisible(false);
				btnCofnij.setVisible(true);
				txtLogin.setVisible(true);
				txtHaslo.setVisible(true);
				lblHaso.setVisible(true);
				lblLogin.setVisible(true);
				btnZaloguj.setVisible(true);
				admin = false;
			}
		});
		btnAdministrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdministrator.setVisible(false);
				btnStudent.setVisible(false);
				btnCofnij.setVisible(true);
				txtLogin.setVisible(true);
				txtHaslo.setVisible(true);
				lblHaso.setVisible(true);
				lblLogin.setVisible(true);
				btnZaloguj.setVisible(true);
				admin = true;
			}
		});
		btnAdministrator.setBounds(83, 115, 130, 44);
		frame.getContentPane().add(btnAdministrator);
		
		btnStudent.setBounds(273, 115, 130, 44);
		frame.getContentPane().add(btnStudent);
		
		btnCofnij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdministrator.setVisible(true);
				btnStudent.setVisible(true);
				btnCofnij.setVisible(false);
				txtLogin.setVisible(false);
				txtHaslo.setVisible(false);
				lblHaso.setVisible(false);
				lblLogin.setVisible(false);
				btnZaloguj.setVisible(false);
			}
		});
		btnCofnij.setBounds(373, 21, 97, 25);
		btnCofnij.setVisible(false);
		frame.getContentPane().add(btnCofnij);
		
		lblHaso.setBounds(137, 134, 56, 25);
		frame.getContentPane().add(lblHaso);
		
		lblLogin.setBounds(137, 103, 56, 16);
		frame.getContentPane().add(lblLogin);
		
		txtHaslo = new JTextField();
		txtHaslo.setText("haslo");
		txtHaslo.setBounds(205, 135, 116, 22);
		frame.getContentPane().add(txtHaslo);
		txtHaslo.setColumns(10);
		
		txtLogin = new JTextField();
		txtLogin.setToolTipText("asd");
		txtLogin.setText("login");
		txtLogin.setBounds(205, 100, 116, 22);
		frame.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		btnZaloguj.setBounds(205, 183, 116, 25);
		frame.getContentPane().add(btnZaloguj);
		
		txtLogin.setVisible(false);
		txtHaslo.setVisible(false);
		lblHaso.setVisible(false);
		lblLogin.setVisible(false);
		btnZaloguj.setVisible(false);
	}

}
