package com.logaggregator.util.compartor;

import java.util.Comparator;

import com.logaggregator.domain.User;
/**
 * This class sorts the users in terms of page views, in descending order of page views
 * @author dipika
 *
 */

public class PageViewsDescendingComparator implements Comparator<User>{
		 
	    /* (non-Javadoc)
	     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	     */
	    @Override
	    public int compare(User e1, User e2) {
	        if(e1.getNumberOfPagesVisited() > e2.getNumberOfPagesVisited()){
	            return -1;
	        } else {
	            return 1;
	        }
	    }
	}

