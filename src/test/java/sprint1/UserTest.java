package sprint1;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UserTest
{
	User x;
	User y;
	Board board;
	@Before
	public void setUp() throws Exception
	{
		x= new User("iamx", "1234");
		y= new User("iamy", "5678");
	}

	@Test
	public void testLogin() throws WrongInputException
	{	
		assertThrows(WrongInputException.class, 
			()-> {
				y.login("iamym", "5678");
			});
			assertTrue(x.login("iamx", "1234"));
	}
	
	@Test
	public void testXML()
	{
		x.storeToDisk();
		
		User disku = User.loadFromDisk();
		assertTrue(x.username.equals(disku.username));
		assertTrue(x.password.equals(disku.password));
	}
	

}
