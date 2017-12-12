package edu.lu.uni.serval.utils;

/**
 * Compute the levenshtein distance between two strings: the source string (s) and the target string (t).
 * 
 * The distance is the number of deletions, insertions, or substitutions required to transform s into t.
 * 
 * @author kui.liu
 *
 */
public class LevenshteinDistance {

	public static void main(String[] args) {
		// Test.
		String a = "gumbo";
		String b = "gaumbol";
		System.out.println(new LevenshteinDistance().computeLevenshteinDistance(a, b));
	}
	
	/**
	 * Compute the levenshtein distance of two strings.
	 * @param s, the source string.
	 * @param t, the target string.
	 * @return distance, The distance is the number of deletions, insertions, or substitutions required to transform s into t
	 */
	public int computeLevenshteinDistance(String s, String t) {
		int lengthS = s.length();
		int lengthT = t.length();

		// Step 1
		if (lengthS == 0) return lengthT;
		if (lengthT == 0) return lengthS;
		
		// Step 2
		int d[][] = new int[lengthS + 1][lengthT + 1];// matrix
		int indexS; // iterates through s
		int indexT; // iterates through t
		for (indexS = 0; indexS <= lengthS; indexS++) d[indexS][0] = indexS;
		for (indexT = 0; indexT <= lengthT; indexT++) d[0][indexT] = indexT;

		// Step 3
		char iCharOfS; // i_th character of s
		char jCharOfT; // j_th character of t
		int cost; // cost
		for (indexS = 1; indexS <= lengthS; indexS++) {
			iCharOfS = s.charAt(indexS - 1);

			// Step 4
			for (indexT = 1; indexT <= lengthT; indexT++) {
				jCharOfT = t.charAt(indexT - 1);
				
				// Step 5
				if (iCharOfS == jCharOfT) {
					cost = 0;
				} else {
					cost = 1;
				}

				// Step 6
				d[indexS][indexT] = getMinimumValue(d[indexS - 1][indexT] + 1, 
													d[indexS][indexT - 1] + 1, 
													d[indexS - 1][indexT - 1] + cost);
			}
		}

		// Step 7
		return d[lengthS][lengthT];
	}

	/**
	 * Get the minimum value of three values.
	 * @param a
	 * @param b
	 * @param c
	 * @return minimumValue
	 */
	private int getMinimumValue(int a, int b, int c) {
		int minimumValue = a;
		if (b < minimumValue)
			minimumValue = b;
		if (c < minimumValue)
			minimumValue = c;
		return minimumValue;
	}
	
}
