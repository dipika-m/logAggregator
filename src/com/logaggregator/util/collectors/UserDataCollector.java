package com.logaggregator.util.collectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.logaggregator.domain.User;
import com.logaggregator.util.UserUtils;
/**
 * This is implementation of collector class to collect all the data 
 * from log into user and session.
 * @author dipika
 *
 */
public class UserDataCollector  implements Collector<String, List<String>, Map<String, User>> {
		@Override
		public BiConsumer<List<String>, String> accumulator() {
			return List::add;
		}

		@Override
		public Supplier<List<String>> supplier() {
			return ArrayList::new;
		}

		@Override
		public BinaryOperator<List<String>> combiner() {
			return (lines, line) -> {

				lines.addAll(line);
				return lines;
			};
		}

		/* (non-Javadoc)
		 * @see java.util.stream.Collector#finisher()
		 * This method extracts the session and request url string 
		 * to create user objects and return hashmap of userid and user objects
		 */
		@Override
		public Function<List<String>, Map<String, User>> finisher() {
			Map<String, User> userMap = new HashMap<String, User>();
			return lines -> {
				for (String line : lines) {
					String[] s = line.split(UserUtils.LOG_DELIMITER);
					User user = new User(s[UserUtils.SESSION_STR_INDEX], s[UserUtils.REQUEST_URL_INDEX]);
					userMap.computeIfPresent(user.getUserId(), (k,v) -> v.updateUserDetails(user));
					userMap.computeIfAbsent(user.getUserId(), v -> user);
					
				}
				for(User user :userMap.values()){
			    	 user.SHORTEST_SESSION = user.getSessions().stream().min(UserUtils.durationComparator).get().getDurationInMinutes();
			    	 user.LONGEST_SESSION = user.getSessions().stream().max(UserUtils.durationComparator).get().getDurationInMinutes();
			    }
				return userMap;
			};

		}

		/* (non-Javadoc)
		 * @see java.util.stream.Collector#characteristics()
		 */
		@Override
		public Set<Characteristics> characteristics() {
			return Collections.emptySet();
		}
	}


