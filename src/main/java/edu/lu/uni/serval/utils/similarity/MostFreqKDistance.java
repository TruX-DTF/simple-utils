package edu.lu.uni.serval.utils.similarity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MostFreqKDistance implements Similarity {
	
	private int k = 3;
	private int limit = 10;
	
	public void setK(int k) {
		this.k = k;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	/**
	 * Wrapper function.
	 * 
	 * @param input string 1
	 * @param input string 2
	 * @param maximum possible limit value
	 * @return distance as integer
	 */
	public Double similarity(String str1, String str2) {
		return computeSimilarity(mostOcurrencesElement(str1.toCharArray()), mostOcurrencesElement(str2.toCharArray()));
	}

	@Override
	public <T> Double similarity(List<T> l1, List<T> l2) {
		String str1 = convertToString(l1);
		String str2 = convertToString(l2);
//		return computeSimilarity(mostOcurrencesElement(l1), mostOcurrencesElement(l2));
		return computeSimilarity(mostOcurrencesElement(str1.toCharArray()), mostOcurrencesElement(str2.toCharArray()));
	}
	
	
	private <T> String convertToString(List<T> l) {
		StringBuilder str = new StringBuilder();
		for (T e : l) {
			str.append(e.toString());
		}
		return str.toString();
	}

	/**
	 * Counts the number of occurrences of each character.
	 * 
	 * @param character array
	 * @return hashmap : Key = char, Value = num of occurrence
	 */
	private HashMap<Character, Integer> countElementOcurrences(char[] array) {
		HashMap<Character, Integer> countMap = new HashMap<Character, Integer>();
		for (char element : array) {
			Integer count = countMap.get(element);
			count = (count == null) ? 1 : count + 1;
			countMap.put(element, count);
		}

		return countMap;
	}

	/**
	 * Sorts the counted numbers of characters (keys, values) by java Collection List.
	 * 
	 * @param HashMap (with key as character, value as number of occurrences)
	 * @return sorted HashMap
	 */
	private <K, V extends Comparable<? super V>> HashMap<K, V> sortByValuesDescending(HashMap<K, V> map) {
		List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		// Here I am copying the sorted list in HashMap using LinkedHashMap to preserve the insertion order
		HashMap<K, V> sortedHashMap = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}

	/**
	 * Get most frequent k characters
	 * 
	 * @param array of characters
	 * @param limit of k
	 * @return hashed String
	 */
	private String mostOcurrencesElement(char[] array) {
		HashMap<Character, Integer> countMap = countElementOcurrences(array);
		Map<Character, Integer> map = sortByValuesDescending(countMap);
//		System.out.println(map);
		int i = 0;
		String output = "";
		for (Map.Entry<Character, Integer> pairs : map.entrySet()) {
			if (i++ >= k)
				break;
			output += "" + pairs.getKey() + pairs.getValue();
		}
		return output;
	}

	/**
	 * Calculates the similarity between two input strings
	 * 
	 * @param input string 1
	 * @param input string 2
	 * @param maximum possible limit value
	 * @return distance as integer
	 */
	public Double computeSimilarity(String str1, String str2) {
		int similarity = 0;
		int k = 0;
		for (int i = 0; i < str1.length(); i = k) {
			k++;
			if (Character.isLetter(str1.charAt(i))) {
				int pos = str2.indexOf(str1.charAt(i));

				if (pos >= 0) {
					String digitStr1 = "";
					while (k < str1.length() && !Character.isLetter(str1.charAt(k))) {
						digitStr1 += str1.charAt(k);
						k++;
					}

					int k2 = pos + 1;
					String digitStr2 = "";
					while (k2 < str2.length() && !Character.isLetter(str2.charAt(k2))) {
						digitStr2 += str2.charAt(k2);
						k2++;
					}

					similarity += Integer.parseInt(digitStr2) + Integer.parseInt(digitStr1);

				}
			}
		}
		return Double.valueOf(Math.abs(limit - similarity));
	}

	@SuppressWarnings("unused")
	private <T> String mostOcurrencesElement(List<T> l) {
		HashMap<T, Integer> countMap = countElementOcurrences(l);
		Map<T, Integer> map = sortByValuesDescending(countMap);
//		System.out.println(map);
		int i = 0;
		String output = "";
		for (Map.Entry<T, Integer> pairs : map.entrySet()) {
			if (i++ >= k)
				break;
			output += "" + pairs.getKey() + pairs.getValue();
		}
		return output;
	}

	private <T> HashMap<T, Integer> countElementOcurrences(List<T> l) {
		HashMap<T, Integer> countMap = new HashMap<T, Integer>();
		for (T element : l) {
			Integer count = countMap.get(element);
			count = (count == null) ? 1 : count + 1;
			countMap.put(element, count);
		}

		return countMap;
	}
}
