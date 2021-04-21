package com.board.controller;

import com.board.controller.action.Action;
import com.board.controller.action.AddReplyAction;
import com.board.controller.action.BoardCheckPassAction;
import com.board.controller.action.BoardDeleteAction;
import com.board.controller.action.BoardListAction;
import com.board.controller.action.BoardPassFormAction;
import com.board.controller.action.BoardUpdateAction;
import com.board.controller.action.BoardUpdateFormAction;
import com.board.controller.action.BoardViewAction;
import com.board.controller.action.BoardWriteAction;
import com.board.controller.action.BoardWriteForm;
import com.board.controller.action.DeleteReplyAction;
import com.board.controller.action.EditMemberAction;
import com.board.controller.action.EditMemberFormAction;
import com.board.controller.action.IdCheckAction;
import com.board.controller.action.JoinAction;
import com.board.controller.action.JoinFormAction;
import com.board.controller.action.LoginAction;
import com.board.controller.action.LoginFormAction;
import com.board.controller.action.LogoutAction;

public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() { return instance; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if(command.equals("loginform"))	ac = new LoginFormAction();
		else if(command.equals("login"))	ac = new LoginAction();
		else if(command.equals("joinform"))	ac = new JoinFormAction();
		else if(command.equals("idcheck"))	ac = new IdCheckAction();
		else if(command.equals("join"))	ac = new JoinAction();
		else if(command.equals("logout"))	ac = new LogoutAction();
		else if(command.equals("editmemberform"))	ac = new EditMemberFormAction();
		else if(command.equals("editmember"))	ac = new EditMemberAction();
		
		else if(command.equals("boardlist"))	ac = new BoardListAction();
		else if(command.equals("boardview")) ac = new BoardViewAction();
		else if(command.equals("boardwriteform")) ac = new BoardWriteForm();
		else if(command.equals("boardwrite")) ac = new BoardWriteAction();
		
		else if(command.equals("boardpassform"))	ac = new BoardPassFormAction();
		else if(command.equals("boardcheckpass"))	ac = new BoardCheckPassAction();
		else if(command.equals("boardupdateform"))	ac = new BoardUpdateFormAction();
		else if(command.equals("boardupdate"))	ac = new BoardUpdateAction();
		else if(command.equals("boarddeleteform"))	ac = new BoardDeleteAction();
		
		else if(command.equals("addreply"))	ac = new AddReplyAction();
		else if(command.equals("deletereply"))	ac = new DeleteReplyAction();
		
		return ac;
	}
}
