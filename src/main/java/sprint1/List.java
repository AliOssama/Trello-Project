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

public class List implements Serializable
{

	private static final long serialVersionUID = -5768864749077177858L;
	public String listName;
	public ArrayList<Card> cards = new ArrayList<Card>();
	Card temp;
	
	public List()
	{
		this.listName="listTest";
		
	}
	
	//should be default constructor
	public List(String listName)
	{
		this.listName=listName;
	}

	public String getListName()
	{
		return listName;
	}
	
	public void setListName(String listName)
	{
		this.listName = listName;
	}
	
	public ArrayList<Card> getCards()
	{
		return cards;
	}
	/**
	 * @param cards the cards to set
	 */
	public void setCards(ArrayList<Card> cards)
	{
		this.cards = cards;
	}
	/**
	 * @return the temp
	 */
	public Card getTemp()
	{
		return temp;
	}

	public void setTemp(Card temp)
	{
		this.temp = temp;
	}
	
	public void createCard(Card card)
	{
		cards.add(card);
	}
	
	public void removeCard(int index)
	{
		cards.remove(index);
	}
	
	//figure out move and swap
	public void moveCard(int spot, int index)
	{
		if (index>spot) 
		{
			index-=1;
		}
		temp=cards.get(spot);
		cards.remove(spot);
		cards.add(index, temp);
	}
	public void swapCard(List currentList,
			int currSpot, List newList, int newSpot)
	{
		temp=currentList.cards.get(currSpot);
		currentList.cards.remove(currSpot);
		newList.cards.add(newSpot, temp);
	}
	
	public void storeToDisk()
	{
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream("list.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		}
		encoder.writeObject(this);
		encoder.close();
	}
	public static List loadFromDisk()
	{
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new 
					FileInputStream("list.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}
		List l= (List) decoder.readObject();
		return l; 
	}
	
	public boolean equals(List that)
	{
		if(!this.listName.equals(that.listName))
			{
				return false;
			}
		 if (this.cards.size() != that.cards.size())
			{
				return false;
			}
		 for (Card c:cards)
		 {
			 if (!that.containCard(c))
			 {
				 return false;
			 }
		 }
		 return true; 
	}
	public boolean containCard(Card c)
	{
		for (Card ctest: cards)
		{
			if(ctest.cardName.equals(c.cardName))
			{
			if(ctest.membersCard.size() == 
					c.membersCard.size())
			{
			if(ctest.labels.size() == 
					c.labels.size())
			{return true;}}}
		}
		return false;
	}
	public boolean containsCard(String card)
	{
		for (Card ctest: cards)
		{
		if(ctest.cardName.equals(card))
		{ 
			return true;
		}		
		}
		return false;
	}
}

	

