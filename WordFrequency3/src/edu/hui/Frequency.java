package edu.hui;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;


public class Frequency {
	private String s;
	
	public Frequency (String content) {
		s = content;
	}
	
	public HashMap<String, Integer> getFreq( ) {
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		// Remove beginning and ending spaces.  replace multiple spaces with one space.  remove all punctuation.
		String[] words = s.toLowerCase().strip().replaceAll("\\s{2,}", " ").replaceAll("\\p{Punct}","").split(" ");
		for (String w : words) {
			m.put(w, m.getOrDefault(w, 0) + 1);
		}
		return m;
	}
	
	// Adapted from https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
	public LinkedHashMap<String, Integer> sortHashMapByValues(HashMap<String, Integer> passedMap) {
		ArrayList<String> mapKeys = new ArrayList<>(passedMap.keySet());
		ArrayList<Integer> mapValues = new ArrayList<>(passedMap.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);

	    LinkedHashMap<String, Integer> sortedMap =  new LinkedHashMap<>();

	    Iterator<Integer> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        Integer val = valueIt.next();
	        Iterator<String> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	            String key = keyIt.next();
	            Integer comp1 = passedMap.get(key);
	            Integer comp2 = val;

	            if (comp1.equals(comp2)) {
	                keyIt.remove();
	                sortedMap.put(key, val);
	                break;
	            }
	        }
	    }
	    return sortedMap;
	}

}
