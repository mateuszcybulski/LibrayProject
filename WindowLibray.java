import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		frame.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmSprawdzObiekty = new JMenuItem("Sprawdz wszystkie obiekty");
		mnMenu.add(mntmSprawdzObiekty);
		mntmSprawdzObiekty.addActionListener(new ActionListener() {
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
		
		JMenuItem mntmSprawdzMojeObiekty = new JMenuItem("Sprawdz moje obiekty");
		mnMenu.add(mntmSprawdzMojeObiekty);
		mntmSprawdzMojeObiekty.addActionListener(new ActionListener() {
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
