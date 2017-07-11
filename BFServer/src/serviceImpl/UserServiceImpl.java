package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public boolean login(String username, String password) throws RemoteException {
		File f = new File("user_account");
		try {
			FileReader fr = new FileReader(f); 
			BufferedReader br = new BufferedReader(fr);
			String str = null;
			boolean getid = false;
			boolean getpw = false;
			while((str = br.readLine())!=null) {
				if(getpw == true) {
					if(password.equals(str))
						return true;
					getpw = false;
				}
				if(getid == true) {
					if(username.equals(str))
						getpw = true;
					getid = false;
				}
				if(str.equals("---")) {
					getid = true;
				}
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

	@Override
	public boolean create(String username, String password) throws RemoteException{
		File f = new File("user_account");
		try {
			FileWriter fw = new FileWriter(f, true);
			FileReader fr = new FileReader(f); 
			BufferedReader br = new BufferedReader(fr);
			String str = null;
			boolean getid = false;
			byte i = 0;
			while((str = br.readLine())!=null) {
				if(getid == true) {
					if(str.equals(username))
						return false;
					getid = false;
				}
				if(str.equals("---")) {
					getid = true;
				}
			}
			fw.write("---\n"+username+"\n"+password+"\n");
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modify(String username, String password, String newuser, String newpw) throws RemoteException{
		File f = new File("user_account");
		try {
			String []USERS = new String[100];
			String []PWS = new String[100];
			FileReader fr = new FileReader(f); 
			BufferedReader br = new BufferedReader(fr);
			String str = null;
			boolean getid = false;
			boolean getpw = false;
			boolean canmodify = false;
			byte i = 0;
			byte mark = -1;
			while((str = br.readLine())!=null) {
				if(getpw == true) {
					PWS[i++] = str;
					getpw = false;
					if(password.equals(str) && username.equals(USERS[i-1])) {
						mark = (byte) (i-1);
						canmodify = true;
					}
				}
				if(getid == true) {
					USERS[i] = str;
					if(str.equals(newuser)) 
						return false;
					getpw = true;
					getid = false;
				}
				if(str.equals("---")) {
					getid = true;
				}
			}
			if(canmodify == false) return false;
			File dir = new File(username);
			File newdir = new File(newuser);
			dir.renameTo(newdir);
			FileWriter fw = new FileWriter(f, false);
			fw.flush();
			fw.close();
			for(byte j =0;j<i;++j) {
				if(j != mark) {
					fw = new FileWriter(f, true);
					fw.write("---\n"+USERS[j]+"\n"+PWS[j]+"\n");
					fw.flush();
					fw.close();
				}
			}
			fw = new FileWriter(f, true);
			fw.write("---\n"+newuser+"\n"+newpw+"\n");
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String username, String password) throws RemoteException{
		File f = new File("user_account");
		try {
			int delall = JOptionPane.showConfirmDialog(null, "Delete All File Of User " + username + "?");
			if(delall == 2) return false;
			String []users = new String[100];
			String []pws = new String[100];
			FileReader fr = new FileReader(f); 
			BufferedReader br = new BufferedReader(fr);
			String str = null;
			boolean getid = false;
			boolean getpw = false;
			boolean canmodify = false;
			byte i = 0;
			byte mark = -1;
			while((str = br.readLine())!=null) {
				if(getpw == true) {
					pws[i++] = str;
					if(password.equals(str) && username.equals(users[i-1])) {
						mark = (byte) (i - 1);
						canmodify = true;
					}
					getpw = false;
				}
				if(getid == true) {
					users[i] = str;
					getpw = true;
					getid = false;
				}
				if(str.equals("---")) {
					getid = true;
				}
			}
			if(canmodify == false) return false;
			FileWriter fw = new FileWriter(f, false);
			fw.flush();
			fw.close();
			for(byte j =0;j<i;++j) {
				if(j != mark) {
					fw = new FileWriter(f, true);
					fw.write("---\n"+users[j]+"\n"+pws[j]+"\n");
					fw.flush();
					fw.close();
				}
			}
			if(delall == 0) {
				File del = new File(username);
				deleteAll(del);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void deleteAll(File path) {
		File[] files = path.listFiles();
		if (!path.exists())  
			return;
		if (path.isFile()) {  
			path.delete();
			return;
		}
		for (int i = 0; i < files.length; i++) {
			deleteAll(files[i]);
		}
		path.delete(); 
	}
	
}
