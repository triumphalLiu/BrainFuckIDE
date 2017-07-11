package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import rmi.RemoteHelper;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class MainFrame extends JFrame {
	public String CurrentUser = "";
	public boolean ConfirmLogin = false;
	private JFrame frame;
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
		jlab1 = new JLabel("ÇëÏÈµÇÂ¼");
		jlab2 = new JLabel("ÕËºÅ");
		jlab3 = new JLabel("ÃÜÂë");
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
		jframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jbu1.addActionListener(new LoginItemActionListener());
		jbu2.addActionListener(new LoginItemActionListener());
		jframe.setVisible(true);
	}

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
		frame.add(textArea, BorderLayout.NORTH);
	//Input Area & Output Area
		box = new Box(BoxLayout.X_AXIS);
		inputArea = new JTextArea();
		inputArea.setRows(2);
		inputArea.setColumns(6);
		inputArea.setLineWrap(true);        
		inputArea.setWrapStyleWord(true);
		inputArea.setEditable(false);
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
		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		exitMenuItem.addActionListener(new MenuItemActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
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
			if(CurrentUser.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Login First", "WARNING", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (cmd.equals("Open")) {
				textArea.setText("Opening");
			} 
			else if (cmd.equals("Run")) {
				resultLabel.setText("Running");
				textArea.setEditable(false);
				inputArea.setEditable(true);
				inputArea.setText("Input Params Here");
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
						CurrentUser = UserID;
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
						CurrentUser = "";
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
			resultLabel.setText("Saving");
			String code = textArea.getText();
			try {
				RemoteHelper.getInstance().getIOService().writeFile(code, CurrentUser, "code");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}
}
