import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class CheckStudents {

	JFrame frame;
	private int page =0;
	private JTextArea textArea;
	private LibraySystem libray = new LibraySystem();
	private ArrayList<Student> students = libray.getStudents();
	private JTextArea textArea_1;
	private JLabel lblNewLabel;
	private JTextField tfToBorrow ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckStudents window = new CheckStudents();
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
	public CheckStudents() {
		initialize();
		showObjects();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 437);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		//textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBounds(197, 13, 573, 370);
		//frame.getContentPane().add(textArea);
		
		JScrollPane jsp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(jsp);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(222, 62, 548, 317);
		frame.getContentPane().add(textArea_1);
		

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
		btnBeforePage.setBounds(15, 56, 195, 25);
		frame.getContentPane().add(btnBeforePage);
		

		JButton btnNextPage = new JButton("Next page");
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page = page +1;
				showObjects();
			}
		});
		btnNextPage.setBounds(15, 20, 195, 25);
		frame.getContentPane().add(btnNextPage);
		
		lblNewLabel = new JLabel("Id, Login, Haslo, Uprawnienia, Indeks, Liczba wyporzyczonych rzeczy");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(222, 13, 548, 36);
		frame.getContentPane().add(lblNewLabel);
		
		
		

		JLabel lblWypozyczObjectO = new JLabel("Zmien dostepnosc obiektu o id:");
		lblWypozyczObjectO.setBounds(15, 121, 195, 22);
		frame.getContentPane().add(lblWypozyczObjectO);
		

		JButton btnWypozycz = new JButton("Zmien dostepnosc");
		btnWypozycz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String answer = libray.changeAbility(Integer.parseInt(tfToBorrow.getText()));


				if(answer.equals("Succes")) {

					JOptionPane.showMessageDialog(frame,  "zmieniono");
					showObjects();
					
				}else if(answer.equals("Dont exist the object")) {

					JOptionPane.showMessageDialog(frame,  "nie ma takiego obiektu");
				}else if(answer.equals("Error in object")) {

					JOptionPane.showMessageDialog(frame,  "Error in object");
				}
			}
		});

		btnWypozycz.setBounds(15, 184, 195, 25);
		frame.getContentPane().add(btnWypozycz);
		

		tfToBorrow = new JTextField();
		tfToBorrow.setBounds(15, 149, 195, 22);
		frame.getContentPane().add(tfToBorrow);
		tfToBorrow.setColumns(10);

		
	}
	
	private ArrayList<String> getStringData(){
		ArrayList<String> data = new ArrayList<String>();
		for(Student st : students) {
			data.add(st.toString() + "\n");
			
			ArrayList<Object> objects = st.getMyObjets();
			for(Object ob : objects) {
				data.add(ob.toString());
			}
			data.add("\n");
			
		}
		
		return data;
	}

	private void showObjects() {

		int a = page*11;
		textArea_1.setText("");
		ArrayList<String> data = getStringData();
		
		

		if(a + 11 > data.size() ) {
			int b = data.size() - a;
			
			for(int i=0 ; i<b; i++) {
				textArea_1.append(data.get(a+i));
				
			}
		}
		else {
			for(int i=0 ; i<11; i++) {
				textArea_1.append(data.get(a+i));
				
			}
		}
		
	}
}
