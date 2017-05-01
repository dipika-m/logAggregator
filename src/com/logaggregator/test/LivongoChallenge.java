package com.logaggregator.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.logaggregator.domain.User;
import com.logaggregator.util.ColorCodes;
import com.logaggregator.util.UserUtils;
import com.logaggregator.util.collectors.UserDataCollector;
import com.logaggregator.util.compartor.PageViewsDescendingComparator;

public class LivongoChallenge {

	
	@Test
	public void test() {
		String dirName = "data/logs";
		List<Path> filesInFolder = new ArrayList<Path>();
		Map<String, User> myList = new HashMap<String, User>();
		
		try (Stream<Path> stream = Files.walk(Paths.get(dirName))) {
			filesInFolder = stream.filter(Files::isRegularFile).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Path fileName : filesInFolder) {
			try (Stream<String> stream = Files.lines(fileName)) {
				myList = stream.collect(new UserDataCollector());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//Map<String, User> Smap = UserUtils.getAggregationData(map, new PageViewsDescendingComparator(), "Top users: ");
		User mc = UserUtils.getTopUser(myList, new PageViewsDescendingComparator(), "Top users: ");
		long longestSession = 15;
		long shortestSession = 10;
		int session = 2;
		String userId = "489f3e87";
		System.out.printf(ColorCodes.GREEN + "%20s %20s %20s %20s %20s %20s", "userId", "#Pages", "#UniquePages", "Longest", "Shortest", "#Sessions \n" + ColorCodes.RESET);
		System.out.printf("%20s %20s %20s %20s %20s %20s", mc.getUserId(), mc.getNumberOfPagesVisited(), mc.getNumberOfUniquePagesVisited(), mc.LONGEST_SESSION, mc.SHORTEST_SESSION, mc.getNumberSessions() +"\n");
		assertEquals(userId ,mc.getUserId());
		assertEquals(longestSession ,mc.LONGEST_SESSION);
		assertEquals(shortestSession ,mc.SHORTEST_SESSION);
		assertEquals(session ,mc.getNumberSessions());
		assertEquals(7 ,mc.getNumberOfPagesVisited());
		assertEquals(1 ,mc.getNumberOfUniquePagesVisited());
	}
}
