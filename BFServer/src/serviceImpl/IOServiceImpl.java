package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import service.IOService;

public class IOServiceImpl implements IOService{
	
	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		File f = new File(userId + "\\" + fileName + "\\" + CurrentTime());
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readFile(String userId, String fileName) {
		File f =  new File(userId + "\\" + fileName);
		if(f.exists() == false)
			f.mkdirs();
		//JOptionPane.showMessageDialog(null, fileName);
		if(!fileName.contains("\\")) {
			File fa[] = f.listFiles();
			String RecentName = "0";
			for(int i = 0; i < fa.length; ++i)
				if(Double.parseDouble(RecentName) < Double.parseDouble(fa[i].getName()))
					RecentName = fa[i].getName();
			f = new File(userId + "\\" + fileName + "\\" + RecentName);
		}
		if(f.exists() == false) return "";
		FileReader fr;
		String buffer = "";
		try {
			fr = new FileReader(f);
		BufferedReader reader = new BufferedReader(fr);
        String line;
        try {
			while ((line = reader.readLine()) != null) {
			    buffer += line;
			    buffer += "\n"; 
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return buffer;
	}

	@Override
	public String readFileList(String userId) {
		String rtn = "";
		File f = new File(userId);
		if(f.exists() == false)
			f.mkdir();
		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++) {
			File fs = fa[i];
			rtn += fs.getName();
			rtn += '=';
		}
		return rtn;
	}
	
	public String newFile(String userId, String filename) {
		File f = new File(userId);
		if(f.exists() == false)
			f.mkdir();
		f = new File(userId + "\\" + filename);
		if(f.exists() == true) return "";
		f.mkdir();
		f = new File(userId + "\\" + filename + "\\" + CurrentTime());
		if(f.exists() == false) {
			try {
				f.createNewFile();
				return filename;
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
		else return "";
	}

	public boolean delFile(String UserId, String currentFile) {
		File path = new File(UserId + "\\" + currentFile);
		deleteAll(path);
		return true;
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
	
	public static String CurrentTime() {
		long time=new Date().getTime();
		Date date=new Date(time);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		String currentTime = sdf.format(date);
		return currentTime;
	}
}
