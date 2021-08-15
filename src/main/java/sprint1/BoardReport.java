package sprint1;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BoardReport implements Serializable
{
	private static final long serialVersionUID = -8839871479386498718L;
	Board b;
	public ArrayList<Board> boards= new ArrayList<Board>();
	public ArrayList<User> users= new ArrayList<User>();

	public BoardReport(ArrayList<Board>boards)
	{
		this.boards=boards;
	}
	//default constructor
	public BoardReport()
	{	
		User userxml= new User ("userxml", "98756");
		Board bxml= new Board("boardxml", userxml);
		boards.add(bxml);
		users.add(userxml);
	}
	public ArrayList<User> getUsers()
	{
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users)
	{
		this.users = users;
	}



	

	/**
	 * @return the boards
	 */
	public ArrayList<Board> getBoards()
	{
		return boards;
	}

	public void setBoards(ArrayList<Board> boards)
	{
		this.boards = boards;
	}
	
	
	public void createOwnedBoard (String boardName, User owner)
	{
		Board b= new Board(boardName, owner);
		boards.add(b);
		if (!this.containsUser(owner))
		{
			users.add(owner);
		}
	}
	public void removeBoard(String boardName)
	{
		int index = 0;
		for (Board b:boards)
		{
			if(b.boardName.equals(boardName))
			{
				index= boards.indexOf(b);
			}
		}
		boards.remove(index);
	}
	
//IMPLEMENT USER ARRAYLIST ADDITION 
	
	public ArrayList<Board> isMember(User u)
	{
		ArrayList<Board> ismemberboard= new ArrayList<Board>();
		for (Board board: boards)
		{	
			for (User member:board.membersBoard)
			{
				if(member.username.equals(u.username))
				{
					if(member.password.equals(u.password))
					{
						ismemberboard.add(board);
					}
				}
			}
		}
		return ismemberboard;
			
	}
	
	public ArrayList<Board> isowner(User u)
	{
		ArrayList<Board> isownerboard= new ArrayList<Board>();
		for (Board board: boards)
		{	
			if(board.owner.username.equals(u.username))
				{
				if(board.owner.password.equals(u.password))
				{
					isownerboard.add(board);
				}
				}
		}
		return isownerboard;
			
	}
	
	public void storeToDisk()
	{
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream("boardreport.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		}
		encoder.writeObject(this);
		encoder.close();
	}
	
	public static BoardReport loadFromDisk()
	{
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new 
					FileInputStream("boardreport.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}
		BoardReport rboard= (BoardReport) decoder.readObject();
		return rboard; 
	}
	

	public boolean equals(BoardReport that)
	{
		if(this.boards.size() != that.boards.size())
			{
				return false;
			}
		 for (Board btest: boards)
		 {
			 if (!that.containsboard(btest))
			 {
				 return false;
			 }
		 }
		 return true; 
	}
	public boolean containsboard(Board board)
	{
		for (Board b: boards)
		{
			if(b.equals(board))
			{
				return true;
			}
		}
		return false;
	}
	public boolean containsUser(User user)
	{
		for (User u: users)
		{
			if(u.username.equals(user.username))
			{
				if(u.password.equals(user.password))
				{
					return true;
				}
			}
		}
		return false;
	}
	

}
