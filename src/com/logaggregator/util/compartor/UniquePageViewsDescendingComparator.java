package com.logaggregator.util.compartor;

import java.util.Comparator;

import com.logaggregator.domain.User;
/**
 * This class sorts the users in terms of page views, in descending order of Unique page views
 * @author dipika
 *
 */
public class UniquePageViewsDescendingComparator implements Comparator<User>{
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
	    @Override
	    public int compare(User e1, User e2) {
	        if(e1.getNumberOfUniquePagesVisited() > e2.getNumberOfUniquePagesVisited()){
	            return -1;
	        } else {
	            return 1;
	        }
	    }
	}

