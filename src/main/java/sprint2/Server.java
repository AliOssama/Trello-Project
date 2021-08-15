package sprint2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import sprint1.BoardReport;
import sprint1.User;

public class Server 
	extends UnicastRemoteObject
	implements ServerInterface
{

	private static final long serialVersionUID = -3196380394138558629L;


	public Server() throws RemoteException
	{
		getReport();		
	}

	BoardReport br;


	
	public BoardReport getReport()
	{
		br= BoardReport.loadFromDisk();
		return br;
	}
	@Override
	public boolean verifyLogin(User user) 
			throws RemoteException
	{
		if (br.containsUser(user))
			{
				return true;
			}
		return false;
	}

	@Override
	public void storeToDisk() throws RemoteException
	{ 
		br.storeToDisk();
	}
	
	public static void main(String[] args)
	{
		try
		{
			Server s= new Server();
			Naming.rebind("Sprint2", s);
		}
		catch(RemoteException e)
		{
			e.printStackTrace();
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
	}
}
