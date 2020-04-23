package cn.baisee.oa.core;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OaSessionListener implements HttpSessionListener  {
	public static SessionContext sessionContext = SessionContext.getInstance();

	public void sessionCreated(HttpSessionEvent event) {
		sessionContext.addSession(event.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		sessionContext.delSession(event.getSession());
	}
}
