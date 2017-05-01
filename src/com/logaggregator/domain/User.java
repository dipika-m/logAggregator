package com.logaggregator.domain;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.logaggregator.util.UserUtils;
/**
 * This class holds aggregates all the data 
 * @author dipika
 *
 */
public class User {


		private static int sessionCounter = 0;
		private static int SESSION_MAX_DURATION = 10;
		public long LONGEST_SESSION;
		public long SHORTEST_SESSION;
		
		String userId;
		List<String> pages = new ArrayList<String>();
		//TODO: add session
		List<Session> sessions = new ArrayList<Session>();

		public User(String sessionStr, String httpStr) {
			LocalDateTime now = LocalDateTime.parse(sessionStr.trim(), UserUtils.FORMATTER);
			sessions.add(new Session(++sessionCounter, now));
			Pattern p = Pattern.compile(UserUtils.LOG_PATTERN);
	    	Matcher m = p.matcher(httpStr);
	    	if(m.matches()) {
	    	    this.userId = m.group(UserUtils.USER_ID_INDEX);
	    
	    	    pages.add(m.group(UserUtils.PAGES_INDEX));

	    	}else{
	    		this.userId = null;
	    	}
	    	this.LONGEST_SESSION = 0;
	    	this.SHORTEST_SESSION = 0;
		}

		public User() {
			// TODO Auto-generated constructor stub
		}

		public User updateUserDetails(User mc) {
			// TODO Auto-generated method stub
			int size = sessions.size();
			LocalDateTime now = mc.getSessions().get(0).getSessionStartTime();
			if (size > 0) {
				Session prevSes = this.sessions.get(size - 1);
				LocalDateTime prevLastAccess = prevSes.getSessionLastAccessTime();
				
				long duration = ChronoUnit.MINUTES.between(prevLastAccess, now);
				
				if (duration <= SESSION_MAX_DURATION) {
					prevSes.setSessionLastAccessTime(now);
					//if this is the last request and
					prevSes.setSessionEndTime(now);
				} else {
					// Assuming that session was expired with in 10 after last
					prevSes.setSessionEndTime(prevLastAccess);
					sessions.add(new Session(++sessionCounter, now));

				}
				long prevDuration = ChronoUnit.MINUTES.between( prevSes.getSessionStartTime(), prevSes.getSessionEndTime()); 
				prevSes.setDurationInMinutes(prevDuration);
				this.addPages(mc.pages);
				return this;
			}else{
			return mc;
			}
		}

		@Override
		public String toString() {
			return "MyClass{" + "userId='" + userId + '\'' + ", pages='" + pages.toString() + '\'' + '}';
		}
		
		public String getUserId(){
			return this.userId;
		}
		public List<String> getPages(){
			return this.pages;
		}
		public User addPages(List<String> pages){
			for(String page: pages){
				this.pages.add(page);
			}
			return this;
		}
		
		public int getNumberOfPagesVisited(){
			return this.pages.size();
		}
		
		public int getNumberOfUniquePagesVisited(){
			return this.pages.stream().
			collect(groupingBy(identity(), counting())).size();
		}


		public List<Session> getSessions() {
			return sessions;
		}
		public int getNumberSessions() {
			return sessions.size();
		}
	  
	}


