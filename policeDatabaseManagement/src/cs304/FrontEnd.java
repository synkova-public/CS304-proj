package cs304;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

public class FrontEnd {

	JFrame frame;
	private JTextField recordText;
	private JTextField nameS;
	private JTextField addressS;
	private JTextField phoneS;
	private JTextField sinS;
	private JTextField nameV;
	private JTextField addressV;
	private JTextField phoneV;
	private JTextField sinV;
	private JTextField officerID;
	private JTextField dateR;
	private JTextField trialID;
	private JTextField judge;
	private JTextField outcome;
	private JTextField dateC;
	private JTable table;
	private JTextField startDate;
	private JTextField endDate;
	private Driver driver = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontEnd window = new FrontEnd();
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
	public FrontEnd() {
		driver = Driver.getDriver();
		initialize();
	}

	private String recordIDGlobal = null;
	private boolean validGlobal = false;
	private void setGlobals(String recordID, boolean valid) {
		recordIDGlobal = recordID;
		validGlobal = valid;
	}

	public JTabbedPane tabbedPane;
	public JPanel panel_1;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 990, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 982, 518);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Update", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblRecordId = new JLabel("Record ID:");
		lblRecordId.setBounds(21, 26, 72, 13);
		panel.add(lblRecordId);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(21, 49, 72, 13);
		panel.add(lblDescription);
		
		JTextArea description = new JTextArea();
		description.setBounds(95, 49, 211, 152);
		panel.add(description);
		
		recordText = new JTextField();
		recordText.setBounds(95, 23, 211, 19);
		panel.add(recordText);
		recordText.setColumns(10);
		
		JLabel lblPeoplesInformation = new JLabel("Suspect Information");
		lblPeoplesInformation.setBounds(359, 26, 93, 13);
		panel.add(lblPeoplesInformation);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(369, 49, 70, 13);
		panel.add(lblName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(369, 72, 70, 13);
		panel.add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone #");
		lblPhone.setBounds(369, 95, 70, 13);
		panel.add(lblPhone);
		
		JLabel lblSin = new JLabel("SIN:");
		lblSin.setBounds(369, 118, 70, 13);
		panel.add(lblSin);
		
		nameS = new JTextField();
		nameS.setBounds(449, 49, 135, 19);
		panel.add(nameS);
		nameS.setColumns(10);
		
		addressS = new JTextField();
		addressS.setColumns(10);
		addressS.setBounds(449, 72, 135, 19);
		panel.add(addressS);
		
		phoneS = new JTextField();
		phoneS.setColumns(10);
		phoneS.setBounds(449, 95, 135, 19);
		panel.add(phoneS);
		
		sinS = new JTextField();
		sinS.setColumns(10);
		sinS.setBounds(449, 118, 135, 19);
		panel.add(sinS);
		
		JLabel lblVictimInformation = new JLabel("Victim Information");
		lblVictimInformation.setBounds(359, 176, 93, 13);
		panel.add(lblVictimInformation);
		
		JLabel label_1 = new JLabel("Name:");
		label_1.setBounds(369, 199, 70, 13);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Address:");
		label_2.setBounds(369, 222, 70, 13);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Phone #");
		label_3.setBounds(369, 245, 70, 13);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("SIN:");
		label_4.setBounds(369, 268, 70, 13);
		panel.add(label_4);
		
		nameV = new JTextField();
		nameV.setColumns(10);
		nameV.setBounds(449, 199, 135, 19);
		panel.add(nameV);
		
		addressV = new JTextField();
		addressV.setColumns(10);
		addressV.setBounds(449, 222, 135, 19);
		panel.add(addressV);
		
		phoneV = new JTextField();
		phoneV.setColumns(10);
		phoneV.setBounds(449, 245, 135, 19);
		panel.add(phoneV);
		
		sinV = new JTextField();
		sinV.setColumns(10);
		sinV.setBounds(449, 268, 135, 19);
		panel.add(sinV);
		
		JLabel lblOfficerInformation = new JLabel("Officer Information");
		lblOfficerInformation.setBounds(21, 320, 129, 13);
		panel.add(lblOfficerInformation);
		
		JLabel lblOfficerId = new JLabel("Officer ID:");
		lblOfficerId.setBounds(31, 343, 62, 13);
		panel.add(lblOfficerId);
		
		officerID = new JTextField();
		officerID.setColumns(10);
		officerID.setBounds(103, 340, 135, 19);
		panel.add(officerID);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(21, 294, 62, 13);
		panel.add(lblDate);
		
		dateR = new JTextField();
		dateR.setColumns(10);
		dateR.setBounds(93, 291, 135, 19);
		panel.add(dateR);
		
		
		JLabel lblCourtInformation = new JLabel("Court Information");
		lblCourtInformation.setBounds(620, 26, 93, 13);
		panel.add(lblCourtInformation);
		
		JLabel lblTrialId = new JLabel("Trial id:");
		lblTrialId.setBounds(630, 49, 70, 13);
		panel.add(lblTrialId);
		
		JLabel lblJudge = new JLabel("Judge:");
		lblJudge.setBounds(630, 72, 70, 13);
		panel.add(lblJudge);
		
		JLabel lblOutcome = new JLabel("Outcome");
		lblOutcome.setBounds(630, 95, 70, 13);
		panel.add(lblOutcome);
		
		JLabel lblDate_1 = new JLabel("Date:");
		lblDate_1.setBounds(630, 118, 70, 13);
		panel.add(lblDate_1);
		
		trialID = new JTextField();
		trialID.setColumns(10);
		trialID.setBounds(710, 49, 135, 19);
		panel.add(trialID);
		
		judge = new JTextField();
		judge.setColumns(10);
		judge.setBounds(710, 72, 135, 19);
		panel.add(judge);
		
		outcome = new JTextField();
		outcome.setColumns(10);
		outcome.setBounds(710, 95, 135, 19);
		panel.add(outcome);
		
		dateC = new JTextField();
		dateC.setColumns(10);
		dateC.setBounds(710, 118, 135, 19);
		panel.add(dateC);
		
		JLabel label = new JLabel("Description:");
		label.setBounds(630, 141, 72, 13);
		panel.add(label);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(129, 383, 45, 13);
		panel.add(lblResult);
		
		JTextArea result_area = new JTextArea();
		result_area.setBounds(167, 383, 332, 98);
		panel.add(result_area);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet allrs = driver.deleteRecord(recordText.getText());
			}
		});
		btnDelete.setBounds(21, 445, 85, 21);
		panel.add(btnDelete);
		
		JCheckBox homicideU = new JCheckBox("Homicide");
		homicideU.setBounds(21, 207, 84, 21);
		panel.add(homicideU);
		
		JCheckBox arsonU = new JCheckBox("Arson");
		arsonU.setBounds(107, 207, 65, 21);
		panel.add(arsonU);
		
		JCheckBox assaultU = new JCheckBox("Assault");
		assaultU.setBounds(183, 207, 72, 21);
		panel.add(assaultU);
		
		JCheckBox burglaryU = new JCheckBox("Burglary");
		burglaryU.setBounds(21, 235, 84, 21);
		panel.add(burglaryU);
		
		JCheckBox publicU = new JCheckBox("Public indecency");
		publicU.setBounds(107, 258, 179, 21);
		panel.add(publicU);
		
		JCheckBox briberyU = new JCheckBox("Bribery");
		briberyU.setBounds(183, 235, 72, 21);
		panel.add(briberyU);
		
		JCheckBox extortionU = new JCheckBox("Extortion");
		extortionU.setBounds(21, 258, 84, 21);
		panel.add(extortionU);
		
		JCheckBox theftU = new JCheckBox("Theft");
		theftU.setBounds(107, 237, 72, 21);
		panel.add(theftU);
		
		JCheckBox taxU = new JCheckBox("Tax Evasion");
		taxU.setBounds(252, 207, 112, 21);
		panel.add(taxU);
		
		JCheckBox falseU = new JCheckBox("False Pretenses");
		falseU.setBounds(251, 241, 118, 21);
		panel.add(falseU);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Search", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(470, 13, 460, 401);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JCheckBox homicideS = new JCheckBox("Homicide");
		homicideS.setBounds(6, 60, 84, 21);
		panel_1.add(homicideS);
		
		JCheckBox arsonS = new JCheckBox("Arson");
		arsonS.setBounds(92, 60, 65, 21);
		panel_1.add(arsonS);
		
		JCheckBox assaultS = new JCheckBox("Assault");
		assaultS.setBounds(168, 60, 78, 21);
		panel_1.add(assaultS);
		
		JCheckBox burglaryS = new JCheckBox("Burglary");
		burglaryS.setBounds(6, 88, 84, 21);
		panel_1.add(burglaryS);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setBounds(10, 13, 62, 13);
		panel_1.add(lblStartDate);
		
		startDate = new JTextField();
		startDate.setColumns(10);
		startDate.setBounds(82, 10, 135, 19);
		panel_1.add(startDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(10, 34, 62, 13);
		panel_1.add(lblEndDate);

		endDate = new JTextField();
		endDate.setColumns(10);
		endDate.setBounds(82, 31, 135, 19);
		panel_1.add(endDate);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(782, 222, 2, 2);
		panel.add(scrollPane_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(630, 163, 279, 118);
		panel.add(scrollPane_3);
		
		JTextArea descriptionC = new JTextArea();
		scrollPane_3.setViewportView(descriptionC);
		
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet allrs = driver.getAllButVictims(recordText.getText());
				ResultSet victimrs = driver.getVictim(recordText.getText());
				ResultSet suspectrs = driver.getSuspect(recordText.getText());
				try {
					nameV.setText(null);
					addressV.setText(null);
					phoneV.setText(null);
					sinV.setText(null);
					nameS.setText(null);
					result_area.setText(null);
					addressS.setText(null);
					phoneS.setText(null);
					sinS.setText(null);
					judge.setText(null);
					dateR.setText(null);
					trialID.setText(null);
					outcome.setText(null);
					dateC.setText(null);
					officerID.setText(null);
					description.setText(null);
					homicideU.setSelected(false);
					assaultU.setSelected(false);
					arsonU.setSelected(false);
					burglaryU.setSelected(false);
					publicU.setSelected(false);
					taxU.setSelected(false);
					briberyU.setSelected(false);
					extortionU.setSelected(false);
					theftU.setSelected(false);
					falseU.setSelected(false);
					if(allrs.next()) {
						description.setText(allrs.getString(1));
						System.out.println(allrs.getString(1));
						dateR.setText(allrs.getString(2));
						trialID.setText(allrs.getString(3));
						judge.setText(allrs.getString(4));
						descriptionC.setText(allrs.getString(5));
						outcome.setText(allrs.getString(6));
						dateC.setText(allrs.getString(7));
						String type = allrs.getString(8).trim();
						if(type.equals("Homicide")) {
							homicideU.setSelected(true);
						} if(type.equals("Arson")) {
							arsonU.setSelected(true);
						} if(type.equals("Assault")) {
							assaultU.setSelected(true);
						} if(type.equals("Burglary")) {
							burglaryU.setSelected(true);
						} if(type.equals("Public indecency")) {
							publicU.setSelected(true);
						} if(type.equals("Tax Evasion")) {
							taxU.setSelected(true);
						} if(type.equals("Bribery")) {
							briberyU.setSelected(true);
						} if(type.equals("Extortion")) {
							extortionU.setSelected(true);
						} if(type.equals("Theft")) {
							theftU.setSelected(true);
						} if(type.equals("False Pretenses")) {
							falseU.setSelected(true);
						}
						officerID.setText(allrs.getString(9));
						setGlobals(recordText.getText(), true);
					}
					if(victimrs.next()) {
						nameS.setText(victimrs.getString(2));
						addressS.setText(victimrs.getString(3));
						phoneS.setText(victimrs.getString(4));
						sinS.setText(victimrs.getString(5));
					}
					if(suspectrs.next()) {
						nameV.setText(suspectrs.getString(2));
						addressV.setText(suspectrs.getString(3));
						phoneV.setText(suspectrs.getString(4));
						sinV.setText(suspectrs.getString(5));
					}
					
					
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		btnFind.setBounds(21, 382, 85, 21);
		panel.add(btnFind);
		
		JButton btnDeleteCourt = new JButton("Delete court");
		btnDeleteCourt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				driver.deleteTrial(recordText.getText());
			}
		});
		btnDeleteCourt.setBounds(628, 290, 129, 21);
		panel.add(btnDeleteCourt);

		
		JLabel lblRecordId_1 = new JLabel("Staticstics");
		lblRecordId_1.setBounds(18, 195, 72, 13);
		panel_1.add(lblRecordId_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 183, 340, 2);
		panel_1.add(separator);
		
		JButton btnRecordDetails = new JButton("Maximum");
		btnRecordDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(DbUtils.resultSetToTableModel(driver.getMax()));
			}
		});
		btnRecordDetails.setBounds(270, 218, 120, 21);
		panel_1.add(btnRecordDetails);
		
		JCheckBox publicS = new JCheckBox("Public indecency");
		publicS.setBounds(92, 111, 179, 21);
		panel_1.add(publicS);
		
		JCheckBox taxS = new JCheckBox("Tax Evasion");
		taxS.setBounds(248, 60, 123, 21);
		panel_1.add(taxS);
		
		JCheckBox briberyS = new JCheckBox("Bribery");
		briberyS.setBounds(168, 88, 78, 21);
		panel_1.add(briberyS);
		
		JCheckBox extortionS = new JCheckBox("Extortion");
		extortionS.setBounds(6, 111, 84, 21);
		panel_1.add(extortionS);
		
		JCheckBox theftS = new JCheckBox("Theft");
		theftS.setBounds(92, 90, 72, 21);
		panel_1.add(theftS);
		
		JCheckBox falseS = new JCheckBox("False Pretenses");
		falseS.setBounds(246, 88, 125, 21);

		JButton btnFindRecord = new JButton("Find Records");
		btnFindRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(DbUtils.resultSetToTableModel(driver.getRecordFilter(startDate.getText(), endDate.getText(), 
									homicideS.isSelected(), arsonS.isSelected(), assaultS.isSelected(), burglaryS.isSelected(), 
									publicS.isSelected(), taxS.isSelected(), briberyS.isSelected(), extortionS.isSelected(), 
									falseS.isSelected(), theftS.isSelected())));
			}
		});	
		panel_1.add(falseS);
		btnFindRecord.setBounds(10, 152, 120, 21);
		panel_1.add(btnFindRecord);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean selected = false;
				boolean error = false;
				String type = "";
				if(homicideU.isSelected() == true) {
					if(selected)
						error = true;
					selected = true;
					type = "Homicide";
				}
				if(arsonU.isSelected() == true) {
					if(selected)
						error = true;
					selected = true;
					type = "Arson";
				}
				if(assaultU.isSelected() == true) {
					if(selected)
						error = true;
					selected = true;
					type = "Assault";
				}
				if(burglaryU.isSelected() == true) {
					if(selected)
						error = true;
					selected = true;
					type = "Burglary";
				}
				if(publicU.isSelected() == true) {
					if(selected)
						error = true;
					selected = true;
					type = "Public indecency";
				}
				if(taxU.isSelected() == true) {
					if(selected)
						error = true;
					selected = true;
					type = "Tax Evasion";
				}
				if(briberyU.isSelected() == true) {
					if(selected)
						error = true;
					selected = true;
					type = "Bribery";
				}
				if(extortionU.isSelected() == true) {
					if(selected)
						error = true;
					selected = true;
					type = "Extortion";
				}
				if(falseU.isSelected() == true) {
					if(selected)
						error = true;
					selected = true;
					type = "False Pretenses";
				}
				if(theftU.isSelected() == true) {
					if(selected)
						error = true;
					selected = true;
					type = "Theft";
				}
				if(error || !selected) {
					//error, violation~!!!
					result_area.setText("CONSTRAINT VIOLATION!!! UPDATE NOT SUCCESSFUL");
					return;
				} 
				
				try {
					if(!driver.getOfficer(officerID.getText()).next()) {
						result_area.setText("CONSTRAINT VIOLATION!!! UPDATE NOT SUCCESSFUL");
						return;
					}
				} catch (SQLException e) {
					;
				}

				driver.updateRecord(recordText.getText(), type, description.getText(), dateR.getText());

			}
		});
		btnUpdate.setBounds(21, 414, 85, 21);
		panel.add(btnUpdate);
		
		JButton btnTotalCounts = new JButton("Total counts");
		btnTotalCounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(DbUtils.resultSetToTableModel(driver.getTotalCount(homicideS.isSelected(), arsonS.isSelected(), 
						assaultS.isSelected(), burglaryS.isSelected(), publicS.isSelected(), taxS.isSelected(), 
						briberyS.isSelected(), extortionS.isSelected(), falseS.isSelected(), theftS.isSelected())));
			}

		});
		btnTotalCounts.setBounds(140, 152, 120, 21);
		panel_1.add(btnTotalCounts);
		
		JButton btnCourtDetails = new JButton("Court Details");
		btnCourtDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(DbUtils.resultSetToTableModel(driver.getCourtNumber()));
			}
		});
		btnCourtDetails.setBounds(270, 152, 120, 21);
		panel_1.add(btnCourtDetails);
		
		JButton btnMinimum = new JButton("Average");
		btnMinimum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(DbUtils.resultSetToTableModel(driver.getAverage()));
			}
		});

		btnMinimum.setBounds(136, 218, 120, 21);
		panel_1.add(btnMinimum);
		
		JButton button_1 = new JButton("Minimum");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(DbUtils.resultSetToTableModel(driver.getMin()));
			}
		});
		button_1.setBounds(6, 218, 120, 21);
		panel_1.add(button_1);
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
