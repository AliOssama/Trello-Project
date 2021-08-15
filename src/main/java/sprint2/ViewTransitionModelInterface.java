package sprint2;

import sprint1.Board;
import sprint1.Card;
import sprint1.List;
import sprint1.User;

public interface ViewTransitionModelInterface
{
	public void showChooseServer();
	public void showBoards(User user);
	public void showBoard(Board board, User user);
	public void showLogin();
	public void showHelp(User user);
	public void showEditList(Board board, User user, List list);
	public void showEditCard(Board board, User user, List list, 
			Card card);
	public void showEditBoard(Board board, User user);
	public void showCreateList(Board board, User user);
	public void showCreateCard(Board board, List list, User user);
	public void showCreateBoard(User user);
	public void showVisitorSave(User user);
	public void showCreateBoardGuest(User user);

}
