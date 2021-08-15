package sprint2;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sprint1.BoardReport;
import sprint1.User;

class testServer
{
	static Server s;
	static BoardReport br;
	static Registry registry;
	static User u;
	static User q;
	static User n;
	static User temp;

	
	@BeforeAll
	static void setUp() throws Exception
	{
		s= new Server();
		registry = LocateRegistry.createRegistry(9999);
		registry.rebind("Sprint2",s);
		br = s.getReport();
		u = new User("anotherone", "nopass");
		q = new User("whatever", "password");
		n= new User("notit", "mypass");
		br.createOwnedBoard("School work", u);	
		temp= new User("tempUserName", "tempPassword");
		br.createOwnedBoard("Example board", temp);
	}
	@Test
	void testLogin()
	{	
		ServerInterface sp;
		boolean bol1 = false;
		boolean bol2= false;
		boolean bol3=false;
		try
		{
			sp = (ServerInterface) 
					registry.lookup("Sprint2");
			bol1= sp.verifyLogin(u);
			bol2= sp.verifyLogin(n);
			bol3= sp.verifyLogin(temp);
		} catch (RemoteException e1)
		{
			e1.printStackTrace();
		} catch (NotBoundException e1)
		{
			e1.printStackTrace();
		}
		assertTrue(bol1);
		assertFalse(bol2);
		assertTrue(bol3);
	}

	@Test
	void testSave()
	{
		ServerInterface sp=null;
		try
		{
			sp = (ServerInterface) 
					registry.lookup("Sprint2");
		} catch (AccessException e1)
		{
			e1.printStackTrace();
		} catch (RemoteException e1)
		{
			e1.printStackTrace();
		} catch (NotBoundException e1)
		{
			e1.printStackTrace();
		}

		assertFalse(br.users.contains(q));
		br.users.add(q);
		try
		{
			sp.storeToDisk();
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
		assertTrue(br.users.contains(q));
	}
	
	@After
	void tearDown() throws Exception
	{
		registry.unbind("Sprint2");
	}
}
