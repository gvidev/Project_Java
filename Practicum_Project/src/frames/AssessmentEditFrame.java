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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import repository.DbConnection;

import javax.swing.JComboBox;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class AssessmentEditFrame extends JFrame {

	private JPanel contentPane;
	private JTextField notesTF;
	
    Connection conn;
	PreparedStatement state;
	Statement student_state;
	Statement course_state;
	ResultSet result;

	

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AssessmentEditFrame(int selectedItemIndex, String selectedItem) {
		
		String[] data = selectedItem.split("\\|");
		String studentFacultyNumber  = data[1].trim();
		String courseName = data[2].trim();
		String finalGrade = data[3].trim();
		String notes = data[4].trim();

		setTitle("Edit Assessment");
		setBounds(150,150,800,600);
        setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
        contentPane.setLayout(null);
		
		JLabel studentFacultyNumberLabel = new JLabel("Student Faculty Number:");
		studentFacultyNumberLabel.setBounds(78, 72, 155, 14);
		contentPane.add(studentFacultyNumberLabel);
		
		JLabel courseNameLabel = new JLabel("Course Name:");
		courseNameLabel.setBounds(78, 115, 155, 14);
		contentPane.add(courseNameLabel);
		
		JLabel finalGradeLabel = new JLabel("Final GRADE:");
		finalGradeLabel.setBounds(78, 163, 155, 14);
		contentPane.add(finalGradeLabel);
		
		JLabel notesLabel = new JLabel("Notes:");
		notesLabel.setBounds(78, 215, 139, 14);
		contentPane.add(notesLabel);
		
		notesTF = new JTextField();
		notesTF.setBounds(243, 212, 331, 20);
		notesTF.setText(notes);
		contentPane.add(notesTF);
		notesTF.setColumns(10);
		
		JButton editAssessmentBtn = new JButton("Edit");
		editAssessmentBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		editAssessmentBtn.setBounds(358, 277, 89, 23);
		contentPane.add(editAssessmentBtn);
		
		JComboBox studentFacultyNumberCB = new JComboBox();
		studentFacultyNumberCB.setBounds(243, 68, 331, 18);
		contentPane.add(studentFacultyNumberCB);
		try {
			populateComboBox(studentFacultyNumberCB, "STUDENTS",7);
		} catch (Exception e) {
			// TODO: handle exception
		}
		studentFacultyNumberCB.setSelectedItem(studentFacultyNumber);
		
		JComboBox courseNameCB = new JComboBox();
		courseNameCB.setBounds(243, 107, 331, 22);
		contentPane.add(courseNameCB);
		try {
			populateComboBox(courseNameCB, "COURSES",2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		courseNameCB.setSelectedItem(courseName);
		
		JComboBox finalGradeCB = new JComboBox();
		finalGradeCB.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5", "6"}));
		finalGradeCB.setBounds(243, 160, 83, 20);
		contentPane.add(finalGradeCB);
		finalGradeCB.setSelectedItem(finalGrade);
		
		
		
		JLabel ErrorLabel = new JLabel("");
		ErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ErrorLabel.setBounds(137, 403, 508, 23);
		contentPane.add(ErrorLabel);
        
        
		editAssessmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = studentFacultyNumberCB.getSelectedItem().toString();
				String b = courseNameCB.getSelectedItem().toString();
				int c = Integer.parseInt(finalGradeCB.getSelectedItem().toString());
				String d = notesTF.getText();
				
				String student_sql = "SELECT  TOP(1) STUDENT_ID FROM STUDENTS WHERE FACULTY_NUMBER = '" + a + "'";
				String course_sql = "SELECT  TOP(1) COURSE_ID FROM COURSES WHERE COURSE_NAME =  '" + b + "'";
				
				if(d.isEmpty()) {
					ErrorLabel.setForeground(new Color(255, 51, 0));
					ErrorLabel.setText("You have inserted wrong values or empty one!");
				}else {
					
					conn=DbConnection.getConnection();
					int ASSESSMENT_ID = MainFrame.assessments_ids.get(selectedItemIndex);
					String sql = "UPDATE ASSESSMENTS "
							+ " SET STUDENT_ID = ?, COURSE_ID = ?,FINAL_GRADE = ?,NOTES = ? "
							+ " WHERE ASSESSMENT_ID = " + ASSESSMENT_ID;
					
					try {
						student_state = conn.createStatement();
						result = student_state.executeQuery(student_sql);
						result.next();
						int student_id = result.getInt(1);
						course_state = conn.createStatement();
						result = course_state.executeQuery(course_sql);
						result.next();
						int course_id = result.getInt(1);
						
						state=conn.prepareStatement(sql);
						state.setInt(1, student_id);
						state.setInt(2, course_id);
						state.setInt(3,c);
						state.setString(4, d);
						state.execute();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					ErrorLabel.setForeground(new Color(4, 184, 25));
					ErrorLabel.setText("The Assessment is updated succsessfully");
					
					
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
