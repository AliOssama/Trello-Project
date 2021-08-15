package sprint2;

import java.rmi.RemoteException;
import java.util.ArrayList;

import sprint1.Board;
import sprint1.BoardReport;
import sprint1.Card;
import sprint1.User;

public class Client
{
	public Server ser;
	public User user;
	ArrayList <Board> boardReport;
	public static ServerInterface s;
	public BoardReport br;

	public Client() {
		try
		{
			ser= new Server();
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
		br= ser.getReport();
	}

	//Verify Login
	public boolean verifyLogin(User user)
	{
		this.user=user;
		boolean bol=false;
		try
		{
			bol = ser.verifyLogin(user);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
		return bol;
	}
	
	//Board
	//create board
	public void createBoard(String boardName, User owner)
	{
		br.createOwnedBoard(boardName, owner);
	}
	
	//open boards and returning it
	public ArrayList<Board> openBoards(User user)
	{
		if (verifyLogin(user)==true) 
		{	ArrayList<Board> boardReport= br.isMember(user);
				
				for (Board br: boardReport)
					{
						System.out.print(br.getBoardName());
						boardReport.add(br);
					}
		}
		else
		{
			System.out.println("Login test failed");
		}
		return boardReport;
	}
	
	public void editBoardName(String oldBoardName, String newBoardName)
	{
		for (Board b:br.boards)
		{
			if (b.getBoardName().equals(oldBoardName))
			{
				b.setBoardName(newBoardName);
			}
		}
	}
	public void removeboard(Board board)
	{	
		br.removeBoard(board.getBoardName());
	}
	public void createList(String boardname, String listName)
	{
		sprint1.List templ=new sprint1.List(listName);
		for (Board b:br.boards)
		{
			if(b.getBoardName().equals(boardname))
			{
				b.createList(templ);
				System.out.println("Added");
			}
		}
	}
	public void removeList(String boardname, int index)
	{
		for (Board b:br.boards)
		{
			if(b.getBoardName().equals(boardname))
			{
				b.removeList(index);
			}
		}
		
	}
	public void moveList(String boardname, int spot, int index)
	{
		for (Board b:br.boards)
		{
			if(b.getBoardName().equals(boardname))
			{
				b.moveList(spot, index);
			}
		}
		
	}
	
	//Lists
	public ArrayList<sprint1.List> openLists(String boardname)
	{
		ArrayList<sprint1.List> listReport= null;
		for (Board board:br.getBoards())
		{
			if(board.getBoardName().equals(boardname))
			{	
				listReport = board.getLists();
				for (sprint1.List l:listReport)
				{
					System.out.println(l);
				}
			}	
		}
		return listReport;
	}
	public void editListName(String boardname, String oldListName,
			String newListName)
	{
		for (Board board:br.getBoards())
		{
			if(board.getBoardName().equals(boardname))
			{
				ArrayList<sprint1.List> listReport;
				listReport = board.getLists();
				for (sprint1.List l:listReport)
				{
					if(l.getListName().equals(oldListName))
					{
						l.setListName(newListName);
					}
				}
			}	
		}
		
	}
	public void editCardName(String boardname, String listName, 
			String oldCardName, String newCardName)
	{
		for (Board board:br.getBoards())
		{
			if(board.getBoardName().equals(boardname))
			{
				ArrayList<sprint1.List> listReport= board.getLists();
				for (sprint1.List l:listReport)
				{
					if(l.getListName().equals(listName))
					{
						for(Card c:l.getCards())
						{
							if(c.getCardName().equals(oldCardName))
							{
								c.setCardName(newCardName);
							}
						}
					}
				}
			}	
		}
		
	}
	public void createCard(String boardname, String listName,
			String cardname)
	{
		Card c;
		for (Board b:br.boards)
		{
			if(b.getBoardName().equals(boardname))
			{
				for (sprint1.List l:b.lists)
				{
					if(l.getListName().equals(listName))
					{
						c= new Card(cardname);
						l.createCard(c);
					}
				}
			}
		}
	}
	public void removeCard(String boardname, String listName,
			int index)
	{
		ArrayList<sprint1.List> listReport;
		for (Board b:br.boards)
		{
			if(b.getBoardName().equals(boardname))
			{
				listReport = b.getLists();
				for (sprint1.List l:listReport)
				{
					if(l.getListName().equals(listName))
					{
						l.removeCard(index);
					}
				}
			}
		}
	}
	public void moveCard(String boardname, String listName, int spot,
			int index)
	{
		ArrayList<sprint1.List> listReport;
		for (Board b:br.boards)
		{
			if(b.getBoardName().equals(boardname))
			{
				listReport = b.getLists();
				for (sprint1.List l:listReport)
				{
					if(l.getListName().equals(listName))
					{
						l.moveCard(spot, index);
					}
				}
			}
		}
	}
	public void swapCard(String boardname, String oldlistName, 
			int currentSpot,String newlistname, int newSpot)
	{
		sprint1.List oldList= new sprint1.List();
		sprint1.List newList= new sprint1.List();
		for (Board b:br.boards)
		{
			if(b.getBoardName().equals(boardname))
			{
				for (sprint1.List l:b.lists)
				{
					if(l.listName.equals(oldlistName)) {
						oldList=l;
					}
					if(l.listName.equals(newlistname)) {
							newList=l;
						}
							
					
				}
			}
		}
		oldList.swapCard(oldList, currentSpot, newList,
				newSpot);
	}
	
	
	//Cards
		
	public ArrayList<sprint1.Card> openCard(String boardName,
			String listName)
	{
		ArrayList<Card> cardReport= new ArrayList<Card>(); 
		for (Board b :br.getBoards())
		{
			if(b.getBoardName().equals(boardName))
			{
				for(sprint1.List l:b.getLists())
				{
					if(l.getListName().equals(listName))
					{
						for(Card c:l.getCards())
						{
							cardReport.add(c);
						}
						
					}
				}

			}	
		}
		return cardReport;
	}
	
	public void addLabel(String boardname, String listname,
			String cardname, String label)
	{
		for (Board b :br.getBoards())
		{
			if(b.getBoardName().equals(boardname))
			{
				for(sprint1.List l:b.getLists())
				{
					if(l.getListName().equals(listname))
					{
						for(Card c:l.getCards())
						{
							if(c.getCardName().equals(cardname))
							{
								c.addLabel(label);
							}
						}
						
					}
				}

			}	
		}
		
	}
	
	public void removeLabel(String boardname, String listname,
			String cardname, String label)
	{
		for (Board b :br.getBoards())
		{
			if(b.getBoardName().equals(boardname))
			{
				for(sprint1.List l:b.getLists())
				{
					if(l.getListName().equals(listname))
					{
						for(Card c:l.getCards())
						{
							if(c.getCardName().equals(cardname))
							{
								for (String s:c.labels)
								{
									if(s.equals(label))
									{
										int index = c.labels.indexOf(label);
										c.labels.remove(index);
									}
									
								}
							}
						}
						
					}
				}

			}	
		}
		
	}
	
	
	
	public void save()
	{
		try
		{
			ser.storeToDisk();
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{		
	}

}
