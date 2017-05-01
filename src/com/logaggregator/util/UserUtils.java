package com.logaggregator.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.logaggregator.domain.Session;
import com.logaggregator.domain.User;

/**
 * Util file to hold constants
 * @author dipika
 *
 */
public class UserUtils {
 public static final int SESSION_STR_INDEX = 2;
 public static final int REQUEST_URL_INDEX = 3;
 public static final String LOG_DELIMITER = "-";
 public static final java.time.format.DateTimeFormatter FORMATTER = java.time.format.DateTimeFormatter
		   .ofPattern("dd/MMM/yyyy:HH:mm:ss");
//find the longest and shortest session 
 public static final Comparator<Session> durationComparator = (s1, s2) -> Long.compare( s1.getDurationInMinutes(), s2.getDurationInMinutes());
 public static final int USER_ID_INDEX = 3;
 public static final int PAGES_INDEX = 4;
 public static final String LOG_PATTERN = ".*(GET|POST)\\s(/(\\w+)){3}(/(\\S+))\\s.*";
 
 
	public static void printAggregationData(Map<String, User> map,Comparator<User> comparator, String reportName ){
		 Map<String, User> sortedMap2 =
				 map.entrySet().stream()
	                        .sorted(Map.Entry.comparingByValue(comparator))
	                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	                                (e1, e2) -> e1, LinkedHashMap::new));
		

		 System.out.println(ColorCodes.CYAN + reportName + ColorCodes.RESET);
		 System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		 System.out.printf(ColorCodes.GREEN + "%20s %20s %20s %20s %20s %20s", "userId", "#Pages", "#UniquePages", "Longest", "Shortest", "#Sessions \n" + ColorCodes.RESET);
		 System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		 int i = 0;
		 for(User mc : sortedMap2.values()){
			 	if(i==4){
			 		break;
			 	}
			 	i++;
				System.out.printf("%20s %20s %20s %20s %20s %20s", mc.getUserId(), mc.getNumberOfPagesVisited(), mc.getNumberOfUniquePagesVisited(), mc.LONGEST_SESSION, mc.SHORTEST_SESSION, mc.getNumberSessions() +"\n");
			}
		 System.out.println("------------------------------------------------------------------------------------------------------------------------------");
	}
	
	 
		public static Map<String, User> getAggregationData(Map<String, User> map,Comparator<User> comparator, String reportName ){
			 Map<String, User> sortedMap =
					 map.entrySet().stream()
		                        .sorted(Map.Entry.comparingByValue(comparator))
		                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
		                                (e1, e2) -> e1, LinkedHashMap::new));
			
			 return sortedMap;
			 
		}
		public static User getTopUser(Map<String, User> map,Comparator<User> comparator, String reportName ){
			 Map<String, User> sortedMap2 =
					 map.entrySet().stream()
		                        .sorted(Map.Entry.comparingByValue(comparator))
		                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
		                                (e1, e2) -> e1, LinkedHashMap::new));
			

			 System.out.println(ColorCodes.CYAN + reportName + ColorCodes.RESET);
			 System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			 System.out.printf(ColorCodes.GREEN + "%20s %20s %20s %20s %20s %20s", "userId", "#Pages", "#UniquePages", "Longest", "Shortest", "#Sessions \n" + ColorCodes.RESET);
			 System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			 int i = 0;User mc1 = new User();
			 for(User mc : sortedMap2.values()){
				 	if(i==1){
				 		break;
				 	}
				 	i++;
				 	mc1 = mc;
					System.out.printf("%20s %20s %20s %20s %20s %20s", mc.getUserId(), mc.getNumberOfPagesVisited(), mc.getNumberOfUniquePagesVisited(), mc.LONGEST_SESSION, mc.SHORTEST_SESSION, mc.getNumberSessions() +"\n");
				}
			 System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			 return mc1;
		}
}
