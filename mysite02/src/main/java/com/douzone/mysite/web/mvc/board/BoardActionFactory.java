
package com.douzone.mysite.web.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if ("writeform".equals(actionName)) {
			action = new WriteForm();
		} else if ("write".equals(actionName)) {
			action = new WriteAction();
		} else if ("view".equals(actionName)) {
			action = new ViewAction();
		} else if ("modifyform".equals(actionName)) {
			action = new ModifyForm();
		} else if ("modify".equals(actionName)) {
			action = new ModifyAtion();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else {
			action = new IndexAction();
		}
		return action;
	}

}
