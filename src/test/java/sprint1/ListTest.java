package sprint1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest
{
	List l;
	List l2;
	Card c1;
	Card c2;
	Card c3;
	Card c4;
	@BeforeEach
	void setUp() throws Exception
	{
		l=new List("testList");
		l2= new List("testList2");
		c1= new Card("testCard");
		c2= new Card("testCard2");
		c3= new Card("testCard3");
		c4= new Card("testCard4");
	}

	@Test
	void testCreateCard()
	{
		l.createCard(c1);
		l.createCard(c2);
		l.createCard(c4);
		assert(l.cards.contains(c1));
		//fail("Not yet implemented");
	}

	@Test
	void testRemoveCard()
	{
		l.createCard(c1);
		l.createCard(c2);
		l.createCard(c4);
		l.removeCard(0);
		assert(!l.cards.contains(c1));
	}

	@Test
	void testMoveCard()
	{
		l.createCard(c1);
		l.createCard(c2);
		l.createCard(c4);
		l.moveCard(1,0);
		assertEquals(c2, l.cards.get(0));
		assertEquals(c1, l.cards.get(1));
	}

	@Test
	void testSwapCard()
	{
		l.createCard(c1);
		l.createCard(c2);
		l.createCard(c4);
		l2.createCard(c3);
		
		l.swapCard(l,0,l2,0);
		assertEquals(c1, l2.cards.get(0));
	}
	
	@Test
	void testXML() {
		l.createCard(c1);
		l.createCard(c2);
		l.createCard(c3);
		l.createCard(c4);
		l.storeToDisk();
		
		List diskL = List.loadFromDisk();
		assertTrue(l.equals(diskL)); 
	}

}

