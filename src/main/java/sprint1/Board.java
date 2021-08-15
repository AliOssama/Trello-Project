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

public class Board implements Serializable
{
	private static final long serialVersionUID = 7631753695746693623L;
	public String boardName;
	User owner;
	ArrayList <User> membersBoard = new ArrayList <User>();
	public ArrayList <List> lists= new ArrayList <List>();
	List temporary;

	public Board(String boardName, User owner)
	{
		this.boardName=boardName;
		this.owner=owner;
	}
	
	//default constructor
	public Board()
	{
		User u= new User("username", "password");
		this.boardName="testBoard";
		this.owner= u;
		this.membersBoard = null;
		this.lists=null;
	}
	/**
	 * @return the boardName
	 */
	public String getBoardName()
	{
		return boardName;
	}

	/**
	 * @param boardName the boardName to set
	 */
	public void setBoardName(String boardName)
	{
		this.boardName = boardName;
	}

	/**
	 * @return the owner
	 */
	public User getOwner()
	{
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner)
	{
		this.owner = owner;
	}

	/**
	 * @return the membersBoard
	 */
	public ArrayList<User> getMembersBoard()
	{
		return membersBoard;
	}

	/**
	 * @param membersBoard the membersBoard to set
	 */
	public void setMembersBoard(ArrayList<User> membersBoard)
	{
		this.membersBoard = membersBoard;
	}

	
	public ArrayList<List> getLists()
	{
		return lists;
	}

	public void setLists(ArrayList<List> lists)
	{
		this.lists = lists;
	}

	//list methods
	public void createList(List list)
	{
		lists.add(list);
	}
	public void removeList(int index)
	{
		lists.remove(index);
	}
	public void moveList(int spot, int index)
	{
		if (index>spot) 
		{
			index-=1;
		}
		temporary=lists.get(spot);
		lists.remove(spot);
		lists.add(index, temporary);		
	}
	
	public void addMember(User newMem, User requester)
	throws NotOwnerException
	{
		if(requester.equals(owner))
		{
			membersBoard.add(newMem);
		}
		else 
		{
			throw new NotOwnerException();
		}
	}
	public void storeToDisk()
	{
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream("board.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		}
		encoder.writeObject(this);
		encoder.close();
	}
	public static Board loadFromDisk()
	{
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new 
					FileInputStream("board.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}
		Board b= (Board) decoder.readObject();
		return b; 
	}
	
	public boolean equals(Board that)
	{
		if(!this.boardName.equals(that.boardName))
			{
				return false;
			}
		 if (!this.owner.username.equals(that.owner.username))
			{
				return false;
			}
		 if (!this.owner.password.equals(that.owner.password))
			 {
				 return false;
			 }
		for (User member:membersBoard)
			 {
				 if(!containMember(member))
				 {
					 return false;
				 }
			 }
		for(List list:lists) {
			if(!containList(list.listName))
			{
				return false;
			}
		}
		 return true; 

	}
	
	public boolean containMember(User mem)
	{
		for (User mtest: membersBoard)
		{
			if(mtest.username.equals(mem.username))
			{
				if(mtest.password.equals(mem.password))
				{
				return true;
				}
			}
		}
		return false;
	}
	
	public boolean containList(String name)
	{
		for (List ltest: lists)
		{
			if(ltest.listName.equals(name))
			{
				return true;
			/*for (Card c:ltest.cards)
			{
				if(!ltest.containCard(c))
				{
					return false;
				}
			}*/
			}
		}
		return false;
	}
	
	

}
