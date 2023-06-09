package frames;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import repository.DbConnection;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class CourseAddFrame extends JFrame {


	private JPanel contentPane;
	private JTextField courseNameTF;
	private JTextField teacherNameTF;
	private JTextField creditHoursTF;
	private JTextField descriptionTF;
	
	Connection conn;
	
	PreparedStatement state;
	ResultSet result;

	
	/**
	 * Create the frame.
	 */
	public CourseAddFrame() {
		setTitle("Add New Course");
		setBounds(150,150,800,600);
        setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel courseNameLabel = new JLabel("Course Name:");
		courseNameLabel.setBounds(113, 72, 120, 14);
		contentPane.add(courseNameLabel);
		
		JLabel teacherNameLabel = new JLabel("Teacher Name:");
		teacherNameLabel.setBounds(113, 115, 120, 14);
		contentPane.add(teacherNameLabel);
		
		JLabel creditHoursLabel = new JLabel("Credit Hours:");
		creditHoursLabel.setBounds(113, 202, 120, 14);
		contentPane.add(creditHoursLabel);
		
		JLabel descriptionLabel = new JLabel("Description:");
		descriptionLabel.setBounds(113, 162, 120, 14);
		contentPane.add(descriptionLabel);
		
		
		courseNameTF = new JTextField();
		courseNameTF.setBounds(246, 69, 331, 20);
		contentPane.add(courseNameTF);
		courseNameTF.setColumns(10);
		
		teacherNameTF = new JTextField();
		teacherNameTF.setBounds(246, 112, 331, 20);
		contentPane.add(teacherNameTF);
		teacherNameTF.setColumns(10);
		
		creditHoursTF = new JTextField();
		creditHoursTF.setBounds(243, 199, 331, 20);
		contentPane.add(creditHoursTF);
		creditHoursTF.setColumns(10);
		
		descriptionTF = new JTextField();
		descriptionTF.setBounds(246, 159, 331, 20);
		contentPane.add(descriptionTF);
		descriptionTF.setColumns(10);
		
		
		JButton addCourseBtn = new JButton("Add");
		addCourseBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		addCourseBtn.setBounds(361, 291, 89, 23);
		contentPane.add(addCourseBtn);
		
		JLabel ErrorLabel = new JLabel("");
		ErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ErrorLabel.setBounds(137, 403, 508, 23);
		contentPane.add(ErrorLabel);
        
        
		addCourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = courseNameTF.getText();
				String b = teacherNameTF.getText();
				int c = Integer.parseInt(creditHoursTF.getText().toString());
				String d = descriptionTF.getText();
				if(a.isEmpty() || b.isEmpty() || creditHoursTF.getText().isEmpty() || d.isEmpty() ) {
					ErrorLabel.setForeground(new Color(255, 51, 0));
					ErrorLabel.setText("Please insert all fields!");
				}else {
					
					conn=DbConnection.getConnection();
					String sql = "INSERT INTO COURSES (COURSE_NAME,TEACHER_NAME,CREDIT_HOURS,DESCRIPTION) VALUES (?,?,?,?)";
					
					try {
						state=conn.prepareStatement(sql);
						state.setString(1, a);
						state.setString(2, b);
						state.setInt(3,c);
						state.setString(4, d);
						state.execute();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					ErrorLabel.setForeground(new Color(4, 184, 25));
					ErrorLabel.setText("The New Course is added succsessfully");
					
					
				}
	}
	});
        
        
        setVisible(true);
	}

}
