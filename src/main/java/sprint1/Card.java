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

public class Card implements Serializable
{
	private static final long serialVersionUID = -4145772782997320570L;
	public String cardName;
	ArrayList<User> membersCard= new ArrayList<User>();
	public ArrayList<String> labels = new ArrayList<String>();
	ArrayList<Component> components= new ArrayList<Component>();

	public Card(String cardName)
	{
		this.cardName=cardName;
	}
	//edit default constructor
	public Card()
	{
		this.cardName="testCard";		
	}
	
	/**
	 * @return the cardName
	 */
	public String getCardName()
	{
		return cardName;
	}
	/**
	 * @param cardName the cardName to set
	 */
	public void setCardName(String cardName)
	{
		this.cardName = cardName;
	}
	/**
	 * @return the membersCard
	 */
	public ArrayList<User> getMembersCard()
	{
		return membersCard;
	}
	/**
	 * @param membersCard the membersCard to set
	 */
	public void setMembersCard(ArrayList<User> membersCard)
	{
		this.membersCard = membersCard;
	}
	/**
	 * @return the components
	 */
	public ArrayList<Component> getComponents()
	{
		return components;
	}
	/**
	 * @param components the components to set
	 */
	public void setComponents(ArrayList<Component> components)
	{
		this.components = components;
	}
	/**
	 * @return the labels
	 */
	public ArrayList<String> getLabels()
	{
		return labels;
	}
	/**
	 * @param labels the labels to set
	 */
	public void setLabels(ArrayList<String> labels)
	{
		this.labels = labels;
	}
	public void addMember(User user)
	{
		membersCard.add(user);
	}
	public void removeMember(int index)
	{
		membersCard.remove(index);
	}
	public User getMember(int index)
	{
		return membersCard.get(index);
	}
	
	public void addLabel(String label)
	{
		labels.add(label);
	}
	public void removeLabel(int index)
	{
		labels.remove(index);
	}
	public String getLabel(int index)
	{
		return labels.get(index);
	}
	public void storeToDisk()
	{
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream("card.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		}
		encoder.writeObject(this);
		encoder.close();
	}
	public static Card loadFromDisk()
	{
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new 
					FileInputStream("card.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}
		Card c= (Card) decoder.readObject();
		return c; 
	}
	public boolean equals(Card that)
	{
		if(!this.cardName.equals(that.cardName))
			{
				return false;
			}
		 if (this.membersCard.size() != that.membersCard.size())
			{
				return false;
			}
		 if(this.labels.size() != that.labels.size())
		 {
			 	return false;
		 }
		 for (String s:labels)
		 {
			 if (!that.containString(s))
			 {
				 return false;
			 }
		 }
		 for (User member:membersCard)
		 {
			 if(!containMember(member))
			 {
				 return false;
			 }
		 }
		 return true; 
	}
	
	public boolean containString(String s)
	{
		for (String stest: labels)
		{
			if(stest.equals(s))
			{
				return true;
			}
		}
		return false;
	}
	public boolean containMember(User mem)
	{
		for (User mtest: membersCard)
		{
			if(!mtest.username.equals(mem.username))
			{
				if(!mtest.password.equals(mem.password))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	
}
