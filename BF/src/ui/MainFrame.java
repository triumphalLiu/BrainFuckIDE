package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import rmi.RemoteHelper;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class MainFrame extends JFrame {
	public String CurrentUser = "";
	public String CurrentFile = "";
	//Login Dialog
	public boolean ConfirmLogin = false;
	private JDialog jframe;
	private JLabel jlab1;
	private JLabel jlab2;
	private JLabel jlab3;
	private JButton jbu1;
	private JButton jbu2;
	private JTextField jtext1;
	private JPasswordField jtext2;
	public void LoginFrame() {
		jframe = new JDialog(frame,"LoginDialog",true);
		jlab1 = new JLabel("Input ID/PW");
		jlab2 = new JLabel("ID");
		jlab3 = new JLabel("PW");
		jbu1 = new JButton("OK");
		jbu2 = new JButton("Cancel");
		jtext1 = new JTextField();
		jtext2 = new JPasswordField();
		jframe.setLayout(null);
		jframe.add(jlab1);
		jlab1.setBounds(110,20,60,30);
		jframe.add(jlab2);
		jlab2.setBounds(60,50,40,25);
		jframe.add(jlab3);
		jlab3.setBounds(60,90,40,25);
		jframe.add(jbu1);
		jbu1.setBounds(80,120,70,32);
		jframe.add(jbu2);
		jbu2.setBounds(160,120,70,32);
		jframe.add(jtext1);
		jtext1.setBounds(90,50,120,25);
		jframe.add(jtext2);
		jtext2.setBounds(90,90,120,25);
		jframe.setBounds(500,400,300,200);
		jframe.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		jbu1.addActionListener(new LoginItemActionListener());
		jbu2.addActionListener(new LoginItemActionListener());
		jframe.setVisible(true);
	}
	//Open Dialog	
	public boolean ConfirmChoose = false;
	private JDialog opendialog;
	private JButton openbu1;
	private JButton openbu2;
	private JComboBox<String> openselbox;
	private JLabel openlab;
	public void OpenDialog(String str) {
		opendialog = new JDialog(frame,"OpenDialog",true);
		openlab = new JLabel("Choose File:");
	    openselbox = new JComboBox<String>();
	    String []array = str.split("=");
	    int len = array.length;
	    for(int i =0;i<len;++i)
	    	openselbox.addItem(array[i]);
	    opendialog.setLayout(null);
	    opendialog.add(openlab);
	    openlab.setBounds(30, 20, 80, 40);
	    opendialog.add(openselbox);
	    openselbox.setBounds(60, 60, 160, 30);
		openbu1 = new JButton("OK");
		openbu2 = new JButton("Cancel");
		opendialog.add(openbu1);
		openbu1.setBounds(60,120,70,32);
		opendialog.add(openbu2);
		openbu2.setBounds(150,120,70,32);
		opendialog.setBounds(500,400,300,200);
		opendialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		openbu1.addActionListener(new OpenActionListener());
		openbu2.addActionListener(new OpenActionListener());
		opendialog.setVisible(true);
	}
	//Main Frame
	private JFrame frame;
	private JTextArea textArea;
	private JTextArea inputArea;
	private JTextArea outputArea;
	private JLabel resultLabel;
	private JMenuBar menuBar;
	private Box box;
	public MainFrame() {
	//Build Window
		frame = new JFrame("BrainFuckIDE Client");
		frame.setLayout(new BorderLayout());
	//Menu Area
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		JMenu runMenu = new JMenu("Run");
		menuBar.add(runMenu);
		JMenuItem runMenuItem = new JMenuItem("Run");
		runMenu.add(runMenuItem);
		JMenu userMenu = new JMenu("User");
		menuBar.add(userMenu);
		JMenuItem LOGINUserAccountItem = new JMenuItem("Login");
		JMenuItem LOGOUTUserAccountItem = new JMenuItem("Logout");
		JMenuItem createUserAccountItem = new JMenuItem("Create");
		JMenuItem modifyUserAccountItem = new JMenuItem("Modify");
		JMenuItem deleteUserAccountItem = new JMenuItem("Delete");
		userMenu.add(LOGINUserAccountItem);
		userMenu.add(LOGOUTUserAccountItem);
		userMenu.add(createUserAccountItem);
		userMenu.add(modifyUserAccountItem);
		userMenu.add(deleteUserAccountItem);
		frame.setJMenuBar(menuBar);
	//Text Area
		textArea = new JTextArea();
		textArea.setRows(13);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setBackground(Color.WHITE);
		textArea.setLineWrap(true);        
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		frame.add(textArea, BorderLayout.NORTH);
	//Input Area & Output Area
		box = new Box(BoxLayout.X_AXIS);
		inputArea = new JTextArea();
		inputArea.setRows(2);
		inputArea.setColumns(6);
		inputArea.setLineWrap(true);        
		inputArea.setWrapStyleWord(true);
		inputArea.setEditable(true);
		outputArea = new JTextArea();
		outputArea.setRows(2);
		outputArea.setColumns(6);
		outputArea.setEditable(false);
		JScrollPane INPUTAREA=new JScrollPane(inputArea);
		JScrollPane OUTPUTAREA=new JScrollPane(outputArea);
		box.add(INPUTAREA);
		box.add(OUTPUTAREA);
		frame.getContentPane().add(BorderLayout.CENTER,box);
	//click and response
		newMenuItem.addActionListener(new SaveActionListener());
		openMenuItem.addActionListener(new SaveActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		runMenuItem.addActionListener(new RunActionListener());
		exitMenuItem.addActionListener(new MenuItemActionListener());
		LOGINUserAccountItem.addActionListener(new UserItemActionListener());
		LOGOUTUserAccountItem.addActionListener(new UserItemActionListener());
		createUserAccountItem.addActionListener(new UserItemActionListener());
		modifyUserAccountItem.addActionListener(new UserItemActionListener());
		deleteUserAccountItem.addActionListener(new UserItemActionListener());
	//Label Area & Other Settings
		resultLabel = new JLabel();
		resultLabel.setText("Ready");
		frame.add(resultLabel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	class MenuItemActionListener implements ActionListener {
		//×Ó²Ëµ¥ÏìÓ¦
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("Exit")){
				System.exit(0);
			}
		}
	}
	
	class LoginItemActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("OK")) {
				String UserID = "";
				String UserPW = "";
				UserID = jtext1.getText();
				UserPW = String.valueOf(jtext2.getPassword());
				if((UserID.equals("") || UserPW.equals(""))){
					JOptionPane.showMessageDialog(null, "Input Correct ID/PW!", "Error", JOptionPane.ERROR_MESSAGE);
					ConfirmLogin = false;
				}	
				else {
					ConfirmLogin = true;
					jframe.setVisible(false);
				}
			}
			else if(cmd.equals("Cancel")) {
				ConfirmLogin = false;
				jframe.setVisible(false);
			}
		}
	}
	
	class OpenActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("OK")) {
				ConfirmChoose = true;
				opendialog.setVisible(false);
			}
			else if(cmd.equals("Cancel")) {
				ConfirmChoose = false;
				opendialog.setVisible(false);
			}
		}
	}
	
	class UserItemActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("Login")) {
				if(!CurrentUser.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Logout First", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
				else {
					LoginFrame();
					if(ConfirmLogin == false) return;
					ConfirmLogin = false;
					String UserID = jtext1.getText();
					String UserPW = String.valueOf(jtext2.getPassword());
					boolean LogResult = false;
					try {
						LogResult = RemoteHelper.getInstance().getUserService().login(UserID, UserPW);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					if(LogResult == false)
						JOptionPane.showMessageDialog(null, "Wrong ID/PW", "ERROR", JOptionPane.ERROR_MESSAGE);
					else {
						JOptionPane.showMessageDialog(null, "Login Successfully", "FINISH", JOptionPane.INFORMATION_MESSAGE);
						CurrentUser = UserID;CurrentFile = "";
						resultLabel.setText("CurrentUser:"+CurrentUser);
					}
				}
				return;
			}
			else if(cmd.equals("Logout")) {
				if(CurrentUser.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Login First", "WARNING", JOptionPane.WARNING_MESSAGE);
				}
				else {
					boolean LogResult = false;
					try {
						LogResult = RemoteHelper.getInstance().getUserService().logout(CurrentUser);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					if(LogResult == false)
						JOptionPane.showMessageDialog(null, "Error Happened When Logout", "ERROR", JOptionPane.ERROR_MESSAGE);
					else {
						JOptionPane.showMessageDialog(null, "Logout Successfully", "FINISH", JOptionPane.INFORMATION_MESSAGE);
						CurrentUser = "";CurrentFile = "";
						textArea.setText("");
						resultLabel.setText("CurrentUser:NULL");
					}
				}
				return;
			}
			else if(cmd.equals("Create")) {
				LoginFrame();
				if(ConfirmLogin == false) return;
				ConfirmLogin = false;
				String UserID = jtext1.getText();
				String UserPW = String.valueOf(jtext2.getPassword());
				boolean CreateResult = false;
				try {
					CreateResult = RemoteHelper.getInstance().getUserService().create(UserID, UserPW);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if(CreateResult == false)
					JOptionPane.showMessageDialog(null, "UserName Has Already Existed", "ERROR", JOptionPane.ERROR_MESSAGE);
				else 
					JOptionPane.showMessageDialog(null, "Create Successfully", "FINISH", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			else if(cmd.equals("Modify")) {
				LoginFrame();
				if(ConfirmLogin == false) return;
				ConfirmLogin = false;
				String UserID = jtext1.getText();
				String UserPW = String.valueOf(jtext2.getPassword());
				LoginFrame();
				if(ConfirmLogin == false) return;
				ConfirmLogin = false;
				String NewUserID = jtext1.getText();
				String NewUserPW = String.valueOf(jtext2.getPassword());
				boolean ModifyResult = false;
				try {
					ModifyResult = RemoteHelper.getInstance().getUserService().modify(UserID, UserPW, NewUserID, NewUserPW);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if(ModifyResult == false)
					JOptionPane.showMessageDialog(null, "Wrong ID/PW OR UserName Has Already Existed", "ERROR", JOptionPane.ERROR_MESSAGE);
				else 
					JOptionPane.showMessageDialog(null, "Modify Successfully", "FINISH", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			else if(cmd.equals("Delete")) {
				LoginFrame();
				if(ConfirmLogin == false) return;
				ConfirmLogin = false;
				String UserID = jtext1.getText();
				String UserPW = String.valueOf(jtext2.getPassword());
				boolean DelResult = false;
				try {
					DelResult = RemoteHelper.getInstance().getUserService().delete(UserID, UserPW);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if(DelResult == false)
					JOptionPane.showMessageDialog(null, "Wrong ID/PW", "ERROR", JOptionPane.ERROR_MESSAGE);
				else 
					JOptionPane.showMessageDialog(null, "Delete Successfully", "FINISH", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		
	}

	class SaveActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(CurrentUser.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Login First", "WARNING", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String cmd = e.getActionCommand();
			if(cmd.equals("Save")) {
				if(CurrentFile == "") return;
				String code = textArea.getText();
				try {
					boolean rtn = RemoteHelper.getInstance().getIOService().writeFile(code, CurrentUser, CurrentFile);
					if(rtn == true) {
						JOptionPane.showMessageDialog(null, "Save Successfully");
					}
					else
						JOptionPane.showMessageDialog(null, "Error When Save File");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (cmd.equals("Open")) {
				try {
					String rtn = RemoteHelper.getInstance().getIOService().readFileList(CurrentUser);
					OpenDialog(rtn);
					if(ConfirmChoose == false) return;
					ConfirmChoose = false;
					CurrentFile = (String) openselbox.getSelectedItem();
					String content = RemoteHelper.getInstance().getIOService().readFile(CurrentUser, CurrentFile);
					textArea.setText(content);
					textArea.setEditable(true);
					resultLabel.setText("CurrentUser:"+CurrentUser+"  "+"CurrentFile:"+CurrentFile);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			} 
			else if(cmd.equals("New")) {
				String FileName = JOptionPane.showInputDialog(null, "FileName:");
				if(FileName == null) return;
				while(FileName.contains(" "))
					FileName = JOptionPane.showInputDialog(null, "Invalid Name, FileName:");
				try {
					String rtn = RemoteHelper.getInstance().getIOService().newFile(CurrentUser, FileName);
					if(rtn.equals(FileName)) {
						JOptionPane.showMessageDialog(null, "Create Successfully");
						CurrentFile = FileName;
						textArea.setEditable(true);
						resultLabel.setText("CurrentUser:"+CurrentUser+"  "+"CurrentFile:"+CurrentFile);
					}
					else
						JOptionPane.showMessageDialog(null, "Error When Create New File");
				} catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error When Create New File");
				}
			}
		}
	}

	class RunActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Run")) {
				textArea.setEditable(true);
				String rtn = null;
				try {
					rtn = RemoteHelper.getInstance().getExecuteService().execute(textArea.getText(), inputArea.getText());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if(rtn == null)
					outputArea.setText("Run Error");
				else
					outputArea.setText("Result= "+rtn);
				resultLabel.setText("CurrentUser:"+CurrentUser+"  "+"CurrentFile:"+CurrentFile+"  "+"Run Finished");
			} 
		}
	}
}