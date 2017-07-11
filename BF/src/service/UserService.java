//鏈嶅姟鍣║serService鐨凷tub锛屽唴瀹圭浉鍚�
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote{
	public boolean login(String username, String password) throws RemoteException;

	public boolean logout(String username) throws RemoteException;
	
	public boolean create(String username, String password) throws RemoteException;
	
	public boolean modify(String username, String password, String newuser, String newpw) throws RemoteException;
	
	public boolean delete(String username, String password) throws RemoteException;
}
