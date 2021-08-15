package sprint1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardTest
{	
	Card c;
	User a;
	User b;
	String label1;
	String label2;
	
	@BeforeEach
	void setUp() throws Exception
	{
		c= new Card("testCard");
		a= new User("iama", "1234");
		b= new User("iamb", "5678");
		label1="Hello";
		label2= "World";
		c.addMember(a);
		c.addMember(b);
		c.addLabel(label1);
		c.addLabel(label2);
	}

	@Test
	void testAddMember()
	{
		
		assert(c.membersCard.contains(a));
		assert(c.membersCard.contains(b));
	}

	@Test
	void testRemoveMember()
	{		
		c.removeMember(0);
		assert(!c.membersCard.contains(a));
	}

	@Test
	void testGetMember()
	{
		assertEquals(a, c.getMember(0));
	}

	@Test
	void testAddLabel()
	{
		
		assertEquals("Hello", c.labels.get(0));
		assertEquals("World", c.labels.get(1));
	}

	@Test
	void testRemoveLabel()
	{
		c.removeLabel(0);
		assert(!c.labels.contains("Hello"));
	}

	@Test
	void testGetLabel()
	{
		c.addLabel(label1);
		c.addLabel(label2);		
		assertEquals("Hello", c.getLabel(0));	
	}
	
	@Test
	void testXML()
	{
		c.storeToDisk();
		
		Card diskC = Card.loadFromDisk();
		assertTrue(c.cardName.equals(diskC.cardName)); 
		
	}

}
