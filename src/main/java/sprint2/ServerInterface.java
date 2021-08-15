package sprint2;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sprint1.BoardReport;
import sprint1.User;

public interface ServerInterface extends Remote
{
	public boolean verifyLogin(User user) throws RemoteException;
	public void storeToDisk() throws RemoteException;
	public BoardReport getReport() throws RemoteException;
}
