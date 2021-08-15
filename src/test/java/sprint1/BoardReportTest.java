package sprint1;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardReportTest
{
	BoardReport rboard;
	Board board;
	Board board2;
	String boardName;
	String boardName2;
	User owner;
	User u;
	User user2;
	User falseUser;
	
	 

	@BeforeEach
	void setUp() throws Exception
	{
		rboard= new BoardReport();
		boardName= "tboard";
		boardName2= "tboard2";
		owner= new User("iamuser", "5678");
		board= new Board(boardName, owner);
		board2= new Board(boardName2, u);
		u= new User("iamamember", "21019");
		user2= new User("iammembertoo", "23456");
		rboard.boards.add(board);
		board.membersBoard.add(u);
		rboard.users.add(u);
	}

	@Test
	void testCreateOwnedBoard()
	{
		rboard.createOwnedBoard(board.boardName, board.owner);
		assert(rboard.boards.contains(board));
	}

	@Test
	void testIsMember()
	{			
		ArrayList<Board> ismember= rboard.isMember(u);
		assertTrue(ismember.contains(board));
	}
	@Test
	void testUsers()
	{
		assertTrue(rboard.users.contains(u));
	}

	@Test
	void testIsowner()
	{
		ArrayList<Board> isowner= rboard.isowner(owner);
		assertTrue(isowner.contains(board));
	}
	
	@Test
	void testXML() 
	{
		rboard.storeToDisk();
		
		BoardReport diskR = BoardReport.loadFromDisk();
		assertTrue(rboard.equals(diskR)); 
	}

}

