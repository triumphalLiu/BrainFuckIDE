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


public class MainFrame extends JFrame {
	private JTextArea textArea;
	private JTextArea inputArea;
	private JTextArea outputArea;
	private JLabel resultLabel;
	private JMenuBar menuBar;
	private Box box;
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
		JMenuItem changeUserAccountItem = new JMenuItem("Change");
		userMenu.add(changeUserAccountItem);
		JMenuItem createUserAccountItem = new JMenuItem("Create");
		userMenu.add(createUserAccountItem);
		JMenuItem modifyUserAccountItem = new JMenuItem("Modify");
		userMenu.add(modifyUserAccountItem);
		JMenuItem deleteUserAccountItem = new JMenuItem("Delete");
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

	class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			resultLabel.setText("Saving");
			String code = textArea.getText();
			try {
				RemoteHelper.getInstance().getIOService().writeFile(code, "admin", "code");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}
}
