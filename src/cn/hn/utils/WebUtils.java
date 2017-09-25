package cn.hn.utils;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import java.util.UUID;

public class WebUtils {
	public static String makeUUID(){
		return UUID.randomUUID().toString();
	}

	public static void addMessage(ActionErrors errors, String key, String message) {
		errors.add(key,new ActionMessage(message,false));
	}

	public  static boolean isEmpty(String value){
		return (value == null || value.trim().equals(""));
	}

}
