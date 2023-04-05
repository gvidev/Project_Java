package frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import repository.DbConnection;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;




@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private StudentAddFrame stdAddFrame;
	private CourseAddFrame crsAddFrame;
	private AssessmentAddFrame asmAddFrame;
	Connection connection;
	
//	PreparedStatement state;
//	ResultSet result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		setResizable(false);
		setTitle("Student\ts Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setSize(835, 560);
		contentPane.add(tabbedPane);
		
		
		
		
		
		
		
		
		// Here start students panel
		
		JPanel studentsPanel = new JPanel();
		studentsPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		studentsPanel.setFocusable(false);
		tabbedPane.addTab("Students", null, studentsPanel, null);
		studentsPanel.setLayout(null);
		
		JButton stdAddButton = new JButton("Add new Student");
		stdAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    stdAddFrame = new StudentAddFrame();
	            setEnabled(false);
	            
	            
	            stdAddFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						setEnabled(true);
					}
					
				});
	            
			}
		});
		
		stdAddButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		stdAddButton.setFocusable(false);
		stdAddButton.setBounds(97, 28, 146, 23);
		studentsPanel.add(stdAddButton);
		
		JButton stdEditButton = new JButton("Edit Student");
		stdEditButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		stdEditButton.setFocusable(false);
		stdEditButton.setBounds(253, 28, 140, 23);
		studentsPanel.add(stdEditButton);
		
		JButton stdDeleteButton = new JButton("Delete Student");
		stdDeleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		stdDeleteButton.setFocusable(false);
		stdDeleteButton.setBounds(403, 28, 135, 23);
		studentsPanel.add(stdDeleteButton);
		
		JButton stdSearchButton = new JButton("Search Student");
		stdSearchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		stdSearchButton.setFocusable(false);
		stdSearchButton.setBounds(548, 28, 169, 23);
		studentsPanel.add(stdSearchButton);
		
		
		@SuppressWarnings("rawtypes")   
		JList allStudentsList = new JList();
		allStudentsList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		allStudentsList.setFocusable(false);
		allStudentsList.setBounds(97, 108, 622, 378);
		studentsPanel.add(allStudentsList);
		try {
			populateJList(allStudentsList, "STUDENTS");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		// Here start courses panel
		
		JPanel coursesPanel = new JPanel();
		coursesPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		coursesPanel.setFocusable(false);
		tabbedPane.addTab("Courses", null, coursesPanel, null);
		coursesPanel.setLayout(null);
		
		JButton crsAddButton = new JButton("Add new Course");
		crsAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    crsAddFrame = new CourseAddFrame();
	            setEnabled(false);
	            
	            
	            crsAddFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						setEnabled(true);
					}
					
				});
	            
			}
		});
		
		crsAddButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		crsAddButton.setFocusable(false);
		crsAddButton.setBounds(97, 28, 146, 23);
		coursesPanel.add(crsAddButton);
		
		JButton crsEditButton = new JButton("Edit Course");
		crsEditButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		crsEditButton.setFocusable(false);
		crsEditButton.setBounds(253, 28, 140, 23);
		coursesPanel.add(crsEditButton);
		
		JButton crsDeleteButton = new JButton("Delete Course");
		crsDeleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		crsDeleteButton.setFocusable(false);
		crsDeleteButton.setBounds(403, 28, 135, 23);
		coursesPanel.add(crsDeleteButton);
		
		JButton crsSearchButton = new JButton("Search Course");
		crsSearchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		crsSearchButton.setFocusable(false);
		crsSearchButton.setBounds(548, 28, 169, 23);
		coursesPanel.add(crsSearchButton);
		
		@SuppressWarnings("rawtypes")
		JList allCoursesList = new JList();
		allCoursesList.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		allCoursesList.setFocusable(false);
		allCoursesList.setBounds(97, 108, 622, 378);
		coursesPanel.add(allCoursesList);
		try {
			populateJList(allCoursesList,"COURSES");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		// Here start assessments panel
		
		
		JPanel assessmentsPanel = new JPanel();
		assessmentsPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		assessmentsPanel.setFocusable(false);
		tabbedPane.addTab("Assessments", null, assessmentsPanel, null);
		assessmentsPanel.setLayout(null);
		
		JButton asmAddButton = new JButton("Add new Assessment");
		asmAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    asmAddFrame = new AssessmentAddFrame();
	            setEnabled(false);
	            
	            
	            asmAddFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						setEnabled(true);
					}
					
				});
	            
			}
		});
		
		asmAddButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		asmAddButton.setFocusable(false);
		asmAddButton.setBounds(97, 28, 146, 23);
		assessmentsPanel.add(asmAddButton);
		
		JButton asmEditButton = new JButton("Edit Assessment");
		asmEditButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		asmEditButton.setFocusable(false);
		asmEditButton.setBounds(253, 28, 140, 23);
		assessmentsPanel.add(asmEditButton);
		
		JButton asmDeleteButton = new JButton("Delete Assessment");
		asmDeleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		asmDeleteButton.setFocusable(false);
		asmDeleteButton.setBounds(403, 28, 135, 23);
		assessmentsPanel.add(asmDeleteButton);
		
		JButton asmSearchButton = new JButton("Search Assessment");
		asmSearchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		asmSearchButton.setFocusable(false);
		asmSearchButton.setBounds(548, 28, 169, 23);
		assessmentsPanel.add(asmSearchButton);
		
		@SuppressWarnings("rawtypes")
		JList list3 = new JList();
		list3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list3.setFocusable(false);
		list3.setBounds(97, 108, 622, 378);
		assessmentsPanel.add(list3);
		
		contentPane.setVisible(true);

	}
	
	@SuppressWarnings("unchecked")
	private void populateJList(JList list, String table) throws SQLException
	{
		connection = DbConnection.getConnection();
	    DefaultListModel model = new DefaultListModel(); //create a new list model
	    String query = "SELECT * FROM " + table;

	    Statement statement = connection.createStatement();
	    ResultSet resultSet = statement.executeQuery(query); //run your query

	    while (resultSet.next()) //go through each row that your query returns
	    {
	        ResultSetMetaData rsmd =  resultSet.getMetaData();
	        StringBuilder x = new StringBuilder();
	        int numColumns = rsmd.getColumnCount();
	        
	        for(int i =2; i<=numColumns; i++) {
	        	
	        	x.append(resultSet.getString(i) + " | ");
	        }
	        model.addElement(x); //add each item to the model
	    }
	    list.setModel(model);

	    resultSet.close();
	    statement.close();

	}
}