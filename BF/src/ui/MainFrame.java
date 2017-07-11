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
import javax.swing.Box;
import javax.swing.BoxLayout;

import rmi.RemoteHelper;

import javax.swing.JOptionPane;

public class MainFrame extends JFrame {
	private JTextArea textArea;
	private JTextArea inputArea;
	private JTextArea outputArea;
	private JLabel resultLabel;
	private JMenuBar menuBar;
	private Box box;
	public String CurrentUser = "";
	public MainFrame() {
	//Build Window
		JFrame frame = new JFrame("BrainFuckIDE Client");
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
			if(CurrentUser.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Login First", "WARNING", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String cmd = e.getActionCommand();
			if (cmd.equals("Open")) {
				textArea.setText("Opening");
			} 
			else if (cmd.equals("Run")) {
				resultLabel.setText("Running");
				textArea.setEditable(false);
				inputArea.setEditable(true);
				inputArea.setText("Input Params Here");
			} 
			else if(cmd.equals("Exit")){
				System.exit(0);
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
					boolean LogResult = false;
					String UserID = JOptionPane.showInputDialog(null, "Input Your UserID");
					String UserPW = JOptionPane.showInputDialog(null, "Input Your Password");
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
					}
				}
				return;
			}
			else if(cmd.equals("Create")) {
				boolean CreateResult = false;
				String UserID = JOptionPane.showInputDialog(null, "Input Your UserID");
				String UserPW = JOptionPane.showInputDialog(null, "Input Your Password");
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
				boolean ModifyResult = false;
				String UserID = JOptionPane.showInputDialog(null, "Input Your UserID");
				String UserPW = JOptionPane.showInputDialog(null, "Input Your Password");
				String NewUserID = JOptionPane.showInputDialog(null, "Input Your UserID");
				String NewUserPW = JOptionPane.showInputDialog(null, "Input Your Password");
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
				boolean DelResult = false;
				String UserID = JOptionPane.showInputDialog(null, "Input Your UserID");
				String UserPW = JOptionPane.showInputDialog(null, "Input Your Password");
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
