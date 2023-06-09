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
public class CourseEditFrame extends JFrame {

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
	public CourseEditFrame(int selectedItemIndex, String selectedItem) {
		
		String[] data = selectedItem.split("\\|");
		String courseName  = data[1].trim();
		String teacherName = data[2].trim();
		String creditHours = data[3].trim();
		String description = data[4].trim();

		setTitle("Edit Course");
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
		creditHoursLabel.setBounds(113, 211, 120, 14);
		contentPane.add(creditHoursLabel);
		
		JLabel descriptionLabel = new JLabel("Description:");
		descriptionLabel.setBounds(113, 162, 104, 14);
		contentPane.add(descriptionLabel);
		
		courseNameTF = new JTextField();
		courseNameTF.setBounds(246, 69, 331, 20);
		courseNameTF.setText(courseName);
		contentPane.add(courseNameTF);
		courseNameTF.setColumns(10);
		
		teacherNameTF = new JTextField();
		teacherNameTF.setBounds(246, 112, 331, 20);
		teacherNameTF.setText(teacherName);
		contentPane.add(teacherNameTF);
		teacherNameTF.setColumns(10);
		
		creditHoursTF = new JTextField();
		creditHoursTF.setBounds(246, 208, 331, 20);
		creditHoursTF.setText(creditHours);
		contentPane.add(creditHoursTF);
		creditHoursTF.setColumns(10);
		
		descriptionTF = new JTextField();
		descriptionTF.setBounds(246, 159, 331, 20);
		descriptionTF.setText(description);
		contentPane.add(descriptionTF);
		descriptionTF.setColumns(10);
		
		JButton editCourseBtn = new JButton("Edit");
		editCourseBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		editCourseBtn.setBounds(359, 296, 89, 23);
		contentPane.add(editCourseBtn);
		
		JLabel ErrorLabel = new JLabel("");
		ErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ErrorLabel.setBounds(137, 403, 508, 23);
		contentPane.add(ErrorLabel);
        
        
		editCourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = courseNameTF.getText();
				String b = teacherNameTF.getText();
				int c = Integer.parseInt(creditHoursTF.getText().toString());
				String d = descriptionTF.getText();
				if(a.isEmpty() || b.isEmpty() || creditHoursTF.getText().isEmpty() || d.isEmpty()) {
					ErrorLabel.setForeground(new Color(255, 51, 0));
					ErrorLabel.setText("You have inserted wrong values or empty one!");
				}else {
					
					conn=DbConnection.getConnection();
					int COURSE_ID = MainFrame.courses_ids.get(selectedItemIndex);
					String sql = "UPDATE COURSES "
							+ " SET COURSE_NAME = ?, TEACHER_NAME = ?,CREDIT_HOURS = ?,DESCRIPTION = ? "
							+ " WHERE COURSE_ID = " + COURSE_ID;
					
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
					ErrorLabel.setText("The Course is updated succsessfully");
					
					
				}
	}
	});
        
        
        setVisible(true);
	}

}
