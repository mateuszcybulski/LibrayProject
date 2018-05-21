import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class WindowLibray {

	private JFrame frame;
	private LoginToLibray login = new LoginToLibray(this);
	private Admin admin = null;
	private Student student = null;
	private boolean isAdmin;
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowLibray window = new WindowLibray();
					window.frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WindowLibray() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSprawdzWszystkieObiekty = new JButton("Sprawdz wszystkie obiekty");
		btnSprawdzWszystkieObiekty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isAdmin == true) {
					
					CheckLoanObjects checkObjects = new CheckLoanObjects(admin, true);
					checkObjects.frame.setVisible(true);
					
				}
				else if(isAdmin == false) {
					CheckLoanObjects checkObjects = new CheckLoanObjects(student, false);
					checkObjects.frame.setVisible(true);
				}
				
			}
			
		});
		btnSprawdzWszystkieObiekty.setBounds(12, 13, 210, 87);
		frame.getContentPane().add(btnSprawdzWszystkieObiekty);
		
		JButton btnSprawdzMojeObiekty = new JButton("Sprawdz moje obiekty");
		btnSprawdzMojeObiekty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isAdmin == true) {
					JOptionPane.showMessageDialog(frame,
						    "Administrator nie moze wypozyczac obiektow",
						    "Niepoprawne dane",
						    JOptionPane.ERROR_MESSAGE);
				}
				else if(isAdmin == false) {

					CheckMyObjects windowMyObjects = new CheckMyObjects(student);
					windowMyObjects.frame.setVisible(true);
				}
				
				
			}
		});
		btnSprawdzMojeObiekty.setBounds(262, 13, 220, 87);
		frame.getContentPane().add(btnSprawdzMojeObiekty);

		JButton btnZobaczStudentowI = new JButton("Zobacz studentow i ich wypozyczenia");
		

		btnZobaczStudentowI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isAdmin == true) {
					CheckStudents checkStudents = new CheckStudents();
					checkStudents.frame.setVisible(true);
					
				}
				else {

					JOptionPane.showMessageDialog(frame,
						    "Nie jestes administratorem",
						    "Niepoprawne dane",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnZobaczStudentowI.setBounds(12, 113, 470, 117);
		frame.getContentPane().add(btnZobaczStudentowI);
		
		
		
		
		frame.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		

		login.frame.setVisible(true);
		
	}
	
	public void signed() {
		login.end();
		frame.setVisible(true);
	}
	
	public void signed(Admin admin) {
		this.admin = admin;
		isAdmin = login.admin;
		login.end();
		frame.setVisible(true);
	}
	
	public void signed(Student student) {
		this.student = student;
		isAdmin = login.admin;
		login.end();
		frame.setVisible(true);
	}
}
