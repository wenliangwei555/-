package cn.baisee.oa.core;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import cn.baisee.oa.constants.AppConstants;
 /**
  * 用户session管理
  * @author Administrator
  *
  */
public class SessionContext {
	
	private static SessionContext instance;  
  private HashMap<String,HttpSession> sessionMap;  

  private SessionContext() {  
      setSessionMap(new HashMap<String,HttpSession>());  
  }  

  public static SessionContext getInstance() {  
      if (instance == null) {  
          instance = new SessionContext();  
      }  
      return instance;  
  }  

  public synchronized void addSession(HttpSession session) {  
      if (session != null) {  
      	sessionMap.put(session.getId(), session);  
      }  
  }  

  public synchronized void delSession(HttpSession session) {  
      if (session != null) {  
      	sessionMap.remove(session.getId());  
          if(session.getAttribute(AppConstants.SESSION_USER_ID)!=null){  
              sessionMap.remove((String)session.getAttribute(AppConstants.SESSION_USER_ID));  
          }  
      }  
  }  

  public synchronized HttpSession getSession(String session_id) {  
      if (session_id == null) return null;  
      return (HttpSession) getSessionMap().get(session_id);  
  }

	public void setSessionMap(HashMap<String,HttpSession> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public HashMap<String,HttpSession> getSessionMap() {
		return sessionMap;
	}  

}
