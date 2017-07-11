package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import service.IOService;

public class IOServiceImpl implements IOService{
	
	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		File f = new File(userId + "\\" + fileName);
		if(f.exists() == true) {
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
		else return false;
	}

	@Override
	public String readFile(String userId, String fileName) {
		File f = new File(userId);
		if(f.exists() == false)
			f.mkdir();
		f = new File(userId + "\\" + fileName);
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
}
