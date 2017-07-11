//闇�瑕佸鎴风鐨凷tub
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IOService extends Remote{
	public boolean writeFile(String file, String userId, String fileName)throws RemoteException;

	public String newFile(String userId, String filename) throws RemoteException;
	
	public String readFile(String userId, String fileName)throws RemoteException;
	
	public String readFileList(String userId)throws RemoteException;

	public boolean delFile(String UserId, String currentFile)throws RemoteException;
}
