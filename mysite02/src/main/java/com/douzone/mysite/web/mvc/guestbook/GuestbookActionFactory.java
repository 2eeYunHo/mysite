package com.douzone.mysite.web.mvc.guestbook;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if ("delete".equals(actionName)) {
			action = new Delete();
		} else if ("add".equals(actionName)) {
			action = new AddAction();
		} else if ("deleteform".equals(actionName)) {
			action = new Deleteform();
		} else {
			action = new IndexAction();
		}
		return action;
	}

}
