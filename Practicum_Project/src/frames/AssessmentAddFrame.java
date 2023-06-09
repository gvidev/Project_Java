package frames;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import repository.DbConnection;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class AssessmentAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField notesTF;
	@SuppressWarnings("rawtypes")
	private JComboBox studentFacultyNumberCB;
	@SuppressWarnings("rawtypes")
	private JComboBox courseNameCB;
	
	Connection conn;
	
	PreparedStatement state;
	Statement student_state;
	Statement course_state;
	ResultSet resultSet;

	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AssessmentAddFrame() {
		setTitle("Add New Assessment");
		setBounds(150,150,800,600);
        setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel studentFacultyNumberLabel = new JLabel("Student Faculty Number:");
		studentFacultyNumberLabel.setBounds(88, 70, 138, 14);
		contentPane.add(studentFacultyNumberLabel);
		
		JLabel courseNameLabel = new JLabel("Course Name:");
		courseNameLabel.setBounds(88, 114, 120, 14);
		contentPane.add(courseNameLabel);
		
		JLabel notesLabel = new JLabel("Notes:");
		notesLabel.setBounds(88, 202, 120, 14);
		contentPane.add(notesLabel);
		
		JLabel finalGradeLabel = new JLabel("Final GRADE:");
		finalGradeLabel.setBounds(88, 161, 120, 14);
		contentPane.add(finalGradeLabel);
		
		notesTF = new JTextField();
		notesTF.setBounds(243, 199, 331, 20);
		contentPane.add(notesTF);
		notesTF.setColumns(10);
		
		studentFacultyNumberCB = new JComboBox();
		studentFacultyNumberCB.setBounds(245, 68, 329, 18);
		contentPane.add(studentFacultyNumberCB);
		try {
			populateComboBox(studentFacultyNumberCB, "STUDENTS",7);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	    courseNameCB = new JComboBox();
		courseNameCB.setBounds(243, 111, 331, 20);
		contentPane.add(courseNameCB);
		try {
			populateComboBox(courseNameCB, "COURSES",2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		JComboBox gradeCB = new JComboBox();
		gradeCB.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5", "6"}));
		gradeCB.setBounds(244, 158, 61, 20);
		contentPane.add(gradeCB);
		
		
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
				String a = studentFacultyNumberCB.getSelectedItem().toString();
				String b = courseNameCB.getSelectedItem().toString();
				int c = Integer.parseInt(gradeCB.getSelectedItem().toString());
				String d = notesTF.getText();
				
				String student_sql = "SELECT  TOP(1) STUDENT_ID FROM STUDENTS WHERE FACULTY_NUMBER = '" + a + "'";
				String course_sql = "SELECT  TOP(1) COURSE_ID FROM COURSES WHERE COURSE_NAME =  '" + b + "'";
				
				
				if(d.isEmpty()) {
					ErrorLabel.setForeground(new Color(255, 51, 0));
					ErrorLabel.setText("Please insert all fields and boxes!");
				}else {
					
					conn=DbConnection.getConnection();
					String sql = "INSERT INTO ASSESSMENTS (STUDENT_ID,COURSE_ID,FINAL_GRADE,NOTES,ASSESSMENT_DATE) VALUES (?,?,?,?,?)";
					
					try {
						student_state = conn.createStatement();
						resultSet = student_state.executeQuery(student_sql);
						resultSet.next();
						int student_id = resultSet.getInt(1);
						course_state = conn.createStatement();
						resultSet = course_state.executeQuery(course_sql);
						resultSet.next();
						int course_id = resultSet.getInt(1);
						
						state=conn.prepareStatement(sql);
						state.setInt(1, student_id);
						state.setInt(2, course_id);
						state.setInt(3,c);
						state.setString(4, d);
						state.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
						state.execute();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					ErrorLabel.setForeground(new Color(4, 184, 25));
					ErrorLabel.setText("The New Assessment is added succsessfully");
					
					
				}
	}

			
	});
        
        
        setVisible(true);

}
	    @SuppressWarnings({ "rawtypes", "unchecked" })
	    private void populateComboBox(JComboBox comboBox, String table, int column) throws SQLException{
		conn = DbConnection.getConnection();
	    String query = "SELECT * FROM " + table;
	    DefaultComboBoxModel model = new DefaultComboBoxModel();

	    Statement statement = conn.createStatement();
	    ResultSet resultSet = statement.executeQuery(query); //run your query

	    while (resultSet.next()) //go through each row that your query returns
	    {
	        model.addElement(resultSet.getString(column));       
	    }
	    comboBox.setModel(model);

	    resultSet.close();
	    statement.close();
	}
}
