import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CheckLoanObjects {

	JFrame frame;
	private JTextField tfToBorrow;
	private Student student = null;
	private Admin admin = null;
	private Person person = null;
	private boolean isAdmin;
	private JTextArea textArea = null;
	private LibraySystem libray = new LibraySystem();
	private ArrayList<String> objects = libray.getObjectsToString();
	private int page=0;
	private JLabel lblSnumberbooks;

	public CheckLoanObjects(Person person, boolean isAdmin) {
		this.person = person;
		
		this.isAdmin = isAdmin;
		initialize();
	}
	/*public CheckLoanObjects(Student student, boolean isAdmin) {
		this.student = student;
		
		this.isAdmin = isAdmin;
		initialize();
	}
	public CheckLoanObjects(Admin admin, boolean isAdmin) {
		this.admin = admin;
		
		this.isAdmin = isAdmin;
		initialize();
	}*/

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 948, 466);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBounds(345, 13, 573, 370);
		frame.getContentPane().add(textArea);
		
		tfToBorrow = new JTextField();
		tfToBorrow.setBounds(12, 50, 195, 22);
		frame.getContentPane().add(tfToBorrow);
		tfToBorrow.setColumns(10);
		
		JLabel lblWypozyczObjectO = new JLabel("Wypozycz object o nr id:");
		lblWypozyczObjectO.setBounds(12, 15, 195, 22);
		frame.getContentPane().add(lblWypozyczObjectO);
		
		JLabel lblTwojeDane = new JLabel("Twoje Dane:");
		lblTwojeDane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTwojeDane.setBounds(12, 205, 180, 22);
		frame.getContentPane().add(lblTwojeDane);
		
		JButton btnWypozycz = new JButton("Wypozycz");

		if(isAdmin == true) {
			btnWypozycz.setText("Zmien dostepnosc");
			lblWypozyczObjectO.setText("Zmien dostepnosc obiektu o id:");
		}
		
		
		btnWypozycz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(isAdmin == false) {
					String answer = libray.borrowObject(person.getPersonId(), Integer.parseInt(tfToBorrow.getText()));
					
					if(answer.equals("book is borrow")) {

						JOptionPane.showMessageDialog(frame,  "wypozyczono");
						refresh();
						
					}else if(answer.equals("book is loan")) {

						JOptionPane.showMessageDialog(frame,  "obiekt nie jest dostepny");
					}
				}
				else if(isAdmin == true) {
					String answer = libray.changeAbility(Integer.parseInt(tfToBorrow.getText()));


					if(answer.equals("Succes")) {

						JOptionPane.showMessageDialog(frame,  "zmieniono");
						refresh();
						
					}else if(answer.equals("Dont exist the object")) {

						JOptionPane.showMessageDialog(frame,  "nie ma takiego obiektu");
					}else if(answer.equals("Error in object")) {

						JOptionPane.showMessageDialog(frame,  "Error in object");
					}
					
					
					
				}
				
				
				
				
				
			}
		});
		btnWypozycz.setBounds(12, 87, 195, 25);
		frame.getContentPane().add(btnWypozycz);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLogin.setBounds(12, 240, 79, 22);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblUprawnieniaid = new JLabel("UprawnieniaId:");
		lblUprawnieniaid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUprawnieniaid.setBounds(12, 269, 116, 16);
		frame.getContentPane().add(lblUprawnieniaid);
		
		
		
		JLabel lblSlogin = new JLabel(person.getLogin());
		lblSlogin.setBounds(151, 240, 114, 16);
		frame.getContentPane().add(lblSlogin);
		
		JLabel lblSpermission = new JLabel(person.getPermissionId());
		lblSpermission.setBounds(151, 270, 69, 16);
		frame.getContentPane().add(lblSpermission);
		
		JButton btnNextPage = new JButton("Next page");
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page = page +1;
				showObjects();
			}
		});
		btnNextPage.setBounds(219, 37, 114, 25);
		frame.getContentPane().add(btnNextPage);
		
		JButton btnBeforePage = new JButton("Before page");
		btnBeforePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(page == 0) {
					JOptionPane.showMessageDialog(frame,  "nie ma wczesniej obiektow");
				}
				else {
					page = page - 1;
					showObjects();
				}
			}
		});
		btnBeforePage.setBounds(219, 73, 114, 25);
		frame.getContentPane().add(btnBeforePage);
		
		
		
		
		if(isAdmin == false) {

			JLabel lblSindex = new JLabel("sindex");
			lblSindex.setBounds(151, 299, 56, 16);
			lblSindex.setText(person.getIndex());
			frame.getContentPane().add(lblSindex);
			
			JLabel lblIndeks = new JLabel("Indeks:");
			lblIndeks.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblIndeks.setBounds(12, 298, 56, 16);
			frame.getContentPane().add(lblIndeks);
			
			JLabel lblLiczbaWyporzyczonychRzeczy = new JLabel("Liczba wyporzyczonych rzeczy:");
			lblLiczbaWyporzyczonychRzeczy.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblLiczbaWyporzyczonychRzeczy.setBounds(12, 327, 208, 25);
			frame.getContentPane().add(lblLiczbaWyporzyczonychRzeczy);
			
			lblSnumberbooks = new JLabel("sNumberBooks");
			lblSnumberbooks.setBounds(12, 361, 56, 16);
			lblSnumberbooks.setText(Integer.toString(person.getNumberBooks()));
			frame.getContentPane().add(lblSnumberbooks);
			
		}
		
		
		
		
		JButton btnOdswierz = new JButton("Odswierz");
		btnOdswierz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					refresh();
			}
		});
		btnOdswierz.setBounds(219, 138, 114, 25);
		frame.getContentPane().add(btnOdswierz);
		

		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("menu");
		menuBar.add(mnMenu);
		
		showObjects();
	}
	
	private void refresh() {
		//page=0;
		
		person.readStudent(person.getPersonId());
		if(isAdmin == false)
			lblSnumberbooks.setText(Integer.toString(person.getNumberBooks()));
		libray.refresh();
		objects = libray.getObjectsToString();	
		showObjects();
	}
	
	private void showObjects() {

		int a = page*5;
		textArea.setText("");
		

		if(a + 5 > objects.size() ) {
			int b = objects.size() - a;
			
			for(int i=0 ; i<b; i++) {
				textArea.append(objects.get(a+i));
			}
		}
		else {
			for(int i=0 ; i<5; i++) {
				textArea.append(objects.get(a+i));
			}
		}
		
	}
}
