package edu.lu.uni.serval.utils.similarity;

import java.util.List;

/**
 * 
 * @author kui.liu
 *
 */
public class LongestCommonSubsequence implements Similarity {

	@Override
	public Double similarity(String str1, String str2) {
		if (str1 == null) {
            throw new NullPointerException("s1 must not be null");
        }

        if (str2 == null) {
            throw new NullPointerException("s2 must not be null");
        }

        if (str1.equals(str2)) {
            return 0d;
        }

        return Double.valueOf(str1.length() + str2.length() - 2 * lcs(str1, str2));
	}

	private int lcs(String s1, String s2) {
        int lengthOfS1 = s1.length();
        int lengthOfS2 = s2.length();
        char[] x = s1.toCharArray();
        char[] y = s2.toCharArray();

        int[][] c = new int[lengthOfS1 + 1][lengthOfS2 + 1];

        for (int i = 1; i <= lengthOfS1; i++) {
            for (int j = 1; j <= lengthOfS2; j++) {
                if (x[i - 1] == y[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;

                } else {
                    c[i][j] = Math.max(c[i][j - 1], c[i - 1][j]);
                }
            }
        }

        return c[lengthOfS1][lengthOfS2];
	}

	@Override
	public <T> Double similarity(List<T> l1, List<T> l2) {
		if (l1 == null) {
            throw new NullPointerException("l1 must not be null");
        }

        if (l2 == null) {
            throw new NullPointerException("l2 must not be null");
        }

        if (l1.equals(l2)) {
            return 0d;
        }

        return Double.valueOf(l1.size() + l2.size() - 2 * lcs(l1, l2));
	}

	private <T> int lcs(List<T> l1, List<T> l2) {
        int sizeOfL1 = l1.size();
        int sizeOfL2 = l2.size();

        int[][] c = new int[sizeOfL1 + 1][sizeOfL2 + 1];

        for (int i = 1; i <= sizeOfL1; i++) {
            for (int j = 1; j <= sizeOfL2; j++) {
                if (l1.get(i - 1).equals(l2.get(j - 1))) {
                    c[i][j] = c[i - 1][j - 1] + 1;

                } else {
                    c[i][j] = Math.max(c[i][j - 1], c[i - 1][j]);
                }
            }
        }

        return c[sizeOfL1][sizeOfL2];
	}

}
