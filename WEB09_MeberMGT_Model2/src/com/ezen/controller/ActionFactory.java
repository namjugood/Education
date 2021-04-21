package com.ezen.controller;

import com.ezen.controller.action.Action;
import com.ezen.controller.action.Delete_Action;
import com.ezen.controller.action.IdCheck_Action;
import com.ezen.controller.action.Join_Action;
import com.ezen.controller.action.Join_Form_Action;
import com.ezen.controller.action.Login_Form_Action;
import com.ezen.controller.action.Logout_Action;
import com.ezen.controller.action.Select_All_Action;
import com.ezen.controller.action.Update_Action;
import com.ezen.controller.action.Update_Form_Action;

public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory ist = new ActionFactory();
	public static ActionFactory getInstance() { return ist; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if(command.equals("login_form")) ac = new Login_Form_Action();
		else if(command.equals("login")) ac=new Login_Form_Action();
		else if(command.equals("join_form")) ac=new Join_Form_Action();
		else if(command.equals("idcheck")) ac=new IdCheck_Action();
		else if(command.equals("join")) ac=new Join_Action();
		else if(command.equals("delete")) ac=new Delete_Action();
		else if(command.equals("logout")) ac=new Logout_Action();
		else if(command.equals("update_form")) ac=new Update_Form_Action();
		else if(command.equals("update")) ac=new Update_Action();
		else if(command.equals("select_all")) ac=new Select_All_Action();
		
		return ac;
	}
}
