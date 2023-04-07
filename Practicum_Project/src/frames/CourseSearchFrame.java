package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import repository.DbConnection;
import java.awt.Cursor;

@SuppressWarnings("serial")
public class CourseSearchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField inputTextField;
	public static ArrayList<Integer> findeditemsId;
	
    Connection conn;
	PreparedStatement state;
	ResultSet resultSet;

	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CourseSearchFrame() {
		setTitle("Search Courses");
		setBounds(150,150,439,599);
        setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
        contentPane.setLayout(null);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Course Name", "Teacher Name"}));
        comboBox.setFocusable(false);
        comboBox.setBounds(150, 115, 139, 31);
        contentPane.add(comboBox);
        
        JLabel searchLabel = new JLabel("Search By:");
        searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        searchLabel.setBounds(150, 80, 139, 22);
        contentPane.add(searchLabel);
        
        inputTextField = new JTextField();
        inputTextField.setBounds(97, 177, 240, 44);
        contentPane.add(inputTextField);
        inputTextField.setColumns(10);
        
        JLabel errorLabel = new JLabel("");
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        errorLabel.setBounds(55, 368, 309, 22);
        contentPane.add(errorLabel);
        
        JButton searchButton = new JButton("Search");
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setFocusable(false);
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(comboBox.getSelectedIndex() != -1 && !inputTextField.getText().isEmpty()) {
        			errorLabel.setText("");
        			
        			int selected = comboBox.getSelectedIndex();
        			String searchBy = null;
        			String input = inputTextField.getText();
        			
        			switch(selected) {
        			case 0:
        				searchBy = "COURSE_NAME";
        				break;
        				
        			case 1:
        				searchBy = "TEACHER_NAME";
        				break;
        			}
        			
        			conn = DbConnection.getConnection();
        			Statement statement;
        			String sql = "SELECT * FROM COURSES WHERE " + searchBy + " LIKE '%" + input + "%'" ;
        			findeditemsId = new ArrayList<Integer>();
        			
        			try {
						 statement = conn.createStatement();
						 resultSet = statement.executeQuery(sql); //run your query
						 int count = 0;
						 while (resultSet.next()) //go through each row that your query returns
						    {
							   count++;
						        findeditemsId.add(Integer.parseInt(resultSet.getString(1)));
						    }
						 resultSet.close();
	        			 statement.close();
	        			 errorLabel.setForeground(new Color(4, 184, 25));
	         			errorLabel.setText(count + " have finded. Please close this window to see them!");
	        			 
					} catch (Exception e2) {
						// TODO: handle exception
					}
        		}
        		else {
        			errorLabel.setForeground(new Color(255, 0, 0));
        			errorLabel.setText("Please select from the box and fill the field!");
        		}
        		
        		
        	}
        });
        searchButton.setBounds(158, 253, 113, 44);
        contentPane.add(searchButton);
        
       
        
        
        setVisible(true);
	}

}
