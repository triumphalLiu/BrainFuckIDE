//闇�瑕佸鎴风鐨凷tub
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
