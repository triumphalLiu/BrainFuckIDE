package serviceImpl;

import java.nio.charset.Charset;
import java.rmi.RemoteException;

import service.ExecuteService;
import service.UserService;

import javax.swing.JOptionPane;

public class ExecuteServiceImpl implements ExecuteService {

	@Override
	public String execute(String code, String param) throws RemoteException {
		char []codes = code.toCharArray();
		char []arr = new char[1024]; 
		char []output = new char[100];
		int []loop = new int[100];
		int parr = 0;
		int pout = 0;
		int pin = 0;
		int ploop = -1;
		for(int i = 0; i < codes.length; ++i) {
			switch (codes[i]) {
			case '>':	++parr;
						if(parr >= 1024)
							return "Error, Over Array Bound-Big";
						break;
			case '<':	--parr;
						if(parr < 0)
							return "Error, Over Array Bound-Small";
						break;
			case '+':	arr[parr]++; break;
			case '-':	arr[parr]--; break;
			case '.':	output[pout++] = arr[parr];
						break;
			case ',':	arr[parr] = ((pin >= param.length()) ? ' ' :param.charAt(pin++)) ;
						break;
			case '[':	loop[++ploop] = i+1; 
						break;
			case ']':	if(arr[parr] == 0) {
							loop[ploop--] = 0;
						}
						else {
							i = loop[ploop]; 
							--i;
						}
						break;
			case ' ' :
			case '\t':
			case '\r':
			case '\n':	break;
			default : return "Error, Unknown Code";
			}
		}
		String value = "";
		for(int i=0;i<pout;++i) {
			value += String.valueOf(output[i]);
		}
		return value;
	}

}
