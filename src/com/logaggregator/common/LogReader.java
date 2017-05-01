package com.logaggregator.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.logaggregator.domain.Session;
import com.logaggregator.domain.User;
import com.logaggregator.util.ColorCodes;
import com.logaggregator.util.UserUtils;
import com.logaggregator.util.collectors.UserDataCollector;
import com.logaggregator.util.compartor.PageViewsDescendingComparator;
import com.logaggregator.util.compartor.UniquePageViewsDescendingComparator;

public class LogReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String dirName;
		List<Path> filesInFolder = new ArrayList<Path>();
		Map<String, User> myList = new HashMap<String, User>();
		
		 if (args.length == 0) {
		      throw new IllegalArgumentException("Invalid Argument : Directory name is required.");
		 }

		dirName = args[0];

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
	    
	    
		 System.out.println(ColorCodes.CYAN + "Total unique users: " + myList.size() + ColorCodes.RESET);
		 UserUtils.printAggregationData(myList, new PageViewsDescendingComparator(), "Top users: ");
		 UserUtils.printAggregationData(myList, new UniquePageViewsDescendingComparator(), "Top users(Unique Pages): ");
	}



	
	

}








