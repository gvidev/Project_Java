package frames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import repository.DbConnection;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class StudentAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameTF;
	private JTextField emailTF;
	private JTextField genderTF;
	private JTextField majorTF;
	private JTextField phoneNumberTF;
	private JTextField facultyNumberTF;
	
	Connection conn;
	
	PreparedStatement state;
	ResultSet result;

	
	/**
	 * Create the frame.
	 */
	public StudentAddFrame() {
		setTitle("Add New Student");
		setBounds(150,150,800,600);
        setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(113, 72, 70, 14);
		contentPane.add(nameLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(113, 115, 70, 14);
		contentPane.add(emailLabel);
		
		JLabel phoneNumberLabel = new JLabel("Phone Number:");
		phoneNumberLabel.setBounds(113, 245, 120, 14);
		contentPane.add(phoneNumberLabel);
		
		JLabel genderLabel = new JLabel("Gender:");
		genderLabel.setBounds(113, 162, 70, 14);
		contentPane.add(genderLabel);
		
		JLabel majorLabel = new JLabel("Major:");
		majorLabel.setBounds(113, 202, 70, 14);
		contentPane.add(majorLabel);
		
		JLabel facultyNumberLabel = new JLabel("Faculty Number:");
		facultyNumberLabel.setBounds(113, 280, 120, 14);
		contentPane.add(facultyNumberLabel);
		
		nameTF = new JTextField();
		nameTF.setBounds(246, 69, 331, 20);
		contentPane.add(nameTF);
		nameTF.setColumns(10);
		
		emailTF = new JTextField();
		emailTF.setBounds(246, 112, 331, 20);
		contentPane.add(emailTF);
		emailTF.setColumns(10);
		
		genderTF = new JTextField();
		genderTF.setBounds(246, 159, 331, 20);
		contentPane.add(genderTF);
		genderTF.setColumns(10);
		
		majorTF = new JTextField();
		majorTF.setBounds(246, 199, 331, 20);
		contentPane.add(majorTF);
		majorTF.setColumns(10);
		
		phoneNumberTF = new JTextField();
		phoneNumberTF.setBounds(243, 242, 331, 20);
		contentPane.add(phoneNumberTF);
		phoneNumberTF.setColumns(10);
		
		facultyNumberTF = new JTextField();
		facultyNumberTF.setBounds(243, 277, 331, 20);
		contentPane.add(facultyNumberTF);
		facultyNumberTF.setColumns(10);
		
		JButton addStudentBtn = new JButton("Add");
		addStudentBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		addStudentBtn.setBounds(358, 347, 89, 23);
		contentPane.add(addStudentBtn);
		
		JLabel ErrorLabel = new JLabel("");
		ErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ErrorLabel.setBounds(137, 403, 508, 23);
		contentPane.add(ErrorLabel);
        
        
        addStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = nameTF.getText();
				String b = emailTF.getText();
				String c = phoneNumberTF.getText();
				String d = genderTF.getText();
				String f = majorTF.getText();
				String g = facultyNumberTF.getText();
				if(a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || f.isEmpty() || g.isEmpty() || c.length()>10 || d.length()>10 || g.length()>10) {
					ErrorLabel.setForeground(new Color(255, 51, 0));
					ErrorLabel.setText("Please insert all fields!");
				}else {
					
					conn=DbConnection.getConnection();
					String sql = "INSERT INTO STUDENTS (NAME,EMAIL,PHONE_NUMBER,GENDER,MAJOR,FACULTY_NUMBER) VALUES (?,?,?,?,?,?)";
					
					try {
						state=conn.prepareStatement(sql);
						state.setString(1, a);
						state.setString(2, b);
						state.setString(3,c);
						state.setString(4, d);
						state.setString(5, f);
						state.setString(6, g);
						state.execute();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					ErrorLabel.setForeground(new Color(4, 184, 25));
					ErrorLabel.setText("The New Students is added succsessfully");
					
					
				}
	}
	});
        
        
        setVisible(true);
	}
}
