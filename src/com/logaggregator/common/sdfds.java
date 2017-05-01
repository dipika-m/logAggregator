package com.logaggregator.common;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sdfds {
	 private static Pattern p = Pattern.compile("(/ec)");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String str = "10.10.6.90"
//				+ " - - 15/Aug/2016:23:59:20 -0500 \"POST /ecf8427e/b443dc7f/71f28176/174ef735/1dd4d421 HTTP/1.0\" 200 - \"-\" \"-\" 7 \"10.10.1.231, 10.10.6.90\" -";
//	
//		System.out.println(from3rd(str.split("-")[2]));
//		 java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
//				   .ofPattern("dd/MMM/yyyy:HH:mm:ss");
//        String dateInString = "15/Aug/2016:23:58:20";
//        String dateInString1 = "16/Aug/2016:00:08:20";
//        LocalDateTime date = LocalDateTime.parse(dateInString, formatter);
//        LocalDateTime date2 = LocalDateTime.parse(dateInString1, formatter);
//		    System.out.printf("%s%n", date);
//		System.out.println(date);
//		System.out.println(ChronoUnit.MINUTES.between(date, date2));
//        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
//        String dateInString11 = "7-Jun-2013";
//
//        try {
//
//            Date date1 = formatter1.parse(dateInString11);
//            System.out.println(date1);
//            System.out.println(formatter1.format(date1));
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//		  Set<String> lines = new TreeSet<String>();
//
//		     List<Integer> list = Arrays.asList(2,4);
//		        Double result = list.stream().collect(Collectors.averagingInt(v->v));
//		        System.out.println("Average: "+result);
//		Stream<String> words = Stream.of("Java", "Magazine", "is", 
//		     "the", "best");
//
//		Map<String, Long> letterToCount =
//		           words.map(w -> w.split(""))
//		                                   .flatMap(Arrays::stream)
//		                                   .collect(groupingBy(identity(), counting()));
//		for(Map.Entry<String, Long> entry : letterToCount.entrySet() ){
//			System.out.print(entry.getKey() + ":");
//			System.out.println(entry.getValue());
//		}
		
//        Map<String, Integer> nonSortedMap = new HashMap<String, Integer>();
//        nonSortedMap.put("ape", 1);
//        nonSortedMap.put("pig", 3);
//        nonSortedMap.put("cow", 1);
//        nonSortedMap.put("frog", 2);
//
//        for (Entry<String, Integer> entry  : entriesSortedByValues(nonSortedMap)) {
//            System.out.println(entry.getKey()+":"+entry.getValue());}
//        }
//        
//    
//  
//
//        static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
//            SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
//                new Comparator<Map.Entry<K,V>>() {
//                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
//                        int res = e1.getValue().compareTo(e2.getValue());
//                        return res != 0 ? res : 1; // Special fix to preserve items with equal values
//                    }
//                }
//            );
//            sortedEntries.addAll(map.entrySet());
//            return sortedEntries;
//        }
		
	
		     Person obama = new Person("Barack Obama", 53);
		     Person bush2 = new Person("George Bush", 68);
		     Person clinton = new Person("Bill Clinton", 68);
		     Person bush1 = new Person("George HW Bush", 90);
		 
		     Person[] personArray = new Person[] {obama, bush2, clinton, bush1};
		     List<Person> personList = Arrays.asList(personArray);

		    //Find Oldest Person
		    final Comparator<Person> comp = (p1, p2) -> Integer.compare( p1.getAge(), p2.getAge());
		    Person oldest = personList.stream()
		                              .max(comp)
		                              .get();

		    //Find Youngest Person
		    //  -This time instead create the Comparator as the argument to the min() method
		    Person youngest = personList.stream()
		                                .min((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
		                                .get();
		    
		    System.out.println(youngest.getName());
	}




public static String from3rd(String in) {
	System.out.println(in);
    Matcher m = p.matcher(in);

    if (m.matches())
        return m.group(2);
    else
        return null;
}
}

 class Person {
    String name;
    int age;
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

    //Constructors and Getters/Setters are omitted for brevity
}