package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;
import service.UserService;

public class ExecuteServiceImpl implements ExecuteService {

	@Override
	public String execute(String code, String param) throws RemoteException {
		
		return "haha";
	}

}
