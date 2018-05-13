import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class CheckMyObjects {

	JFrame frame;
	private JTextArea textArea = null;
	private int page = 0;
	private Student student = null;
	private ArrayList<String> objects = null;
	private JTextField tfGiveBackObject;
	private LibraySystem libray = new LibraySystem();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckMyObjects window = new CheckMyObjects(new  Student (4));
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
	public CheckMyObjects(Student student) {
		this.student = student;
		objects = student.getMyObjetsString();
		initialize();

		refresh();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Moje wypozyczone obiekty");
		frame.setBounds(100, 100, 929, 461);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBounds(304, 13, 595, 370);
		frame.getContentPane().add(textArea);
		
		JButton btnNextPage = new JButton("Next page");
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page = page +1;
				showObjects();
			}
		});
		btnNextPage.setBounds(178, 14, 114, 25);
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
		btnBeforePage.setBounds(178, 52, 114, 25);
		frame.getContentPane().add(btnBeforePage);
		
		tfGiveBackObject = new JTextField();
		tfGiveBackObject.setBounds(176, 174, 116, 22);
		frame.getContentPane().add(tfGiveBackObject);
		tfGiveBackObject.setColumns(10);
		
		JLabel lblOddajObiektO = new JLabel("Oddaj Obiekt o Id:");
		lblOddajObiektO.setBounds(178, 146, 114, 24);
		frame.getContentPane().add(lblOddajObiektO);
		
		JButton btnOddaj = new JButton("Oddaj");
		btnOddaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String answer = libray.giveBackObject(student.getPersonId(), Integer.parseInt(tfGiveBackObject.getText()));
				
				if(answer.equals("book is give back")) {

					JOptionPane.showMessageDialog(frame,  "oddano");
					refresh();
					
				}else if(answer.equals("book is inside")) {

					JOptionPane.showMessageDialog(frame,  "nie mozesz oddac tego obiektu");
				}
				
			}
		});
		btnOddaj.setBounds(178, 209, 118, 25);
		frame.getContentPane().add(btnOddaj);
		
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
	
	private void refresh() {
		student.readStudent(student.getPersonId());
		objects = student.getMyObjetsString();
		showObjects();
	}
}
