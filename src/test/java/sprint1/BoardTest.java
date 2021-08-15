package sprint1;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest
{
	Board b;
	User u;
	User y;
	List l;
	List i;
	List k;
	ArrayList<Card> cards;
	@BeforeEach
	void setUp() throws Exception
	{
		u= new User("iamu", "thisismypassword");
		y= new User("iamy", "5678");
		b= new Board("Btest", u);
		l= new List("hello");
		i= new List("world");
		k=new List("bye");
		cards= new ArrayList<Card>();
		
	}

	@Test
	void testCreateList()
	{
		b.createList(l);
		b.createList(i);
		b.createList(k);
		assert(b.lists.contains(l));
		assert(b.lists.contains(i));
	}

	@Test
	void testRemoveList()
	{
		b.createList(l);
		b.createList(i);
		b.createList(k);
		b.removeList(0);
		assert(!b.lists.contains(l));
	}

	@Test
	void testMoveList()
	{
		b.createList(l);
		b.createList(i);
		b.createList(k);
		b.moveList(0, 1);
		assertEquals(l, b.lists.get(0));
		assertEquals(i, b.lists.get(1));
	}

	@Test
	void testAddMember() throws NotOwnerException
	{
		assertThrows(NotOwnerException.class, 
				()-> {
					b.addMember(y,y);
				});
		b.addMember(y, u);
		assert(b.membersBoard.contains(y));
	}
	
	@Test
	void testXML() 
	{
		b.createList(l);
		b.createList(i);
		b.createList(k);
		try
		{
			b.addMember(y, u);
		} catch (NotOwnerException e)
		{
			e.printStackTrace();
		}
		
		b.storeToDisk();
		
		Board diskB = Board.loadFromDisk();
		assertTrue(b.equals(diskB)); 
	}


}
