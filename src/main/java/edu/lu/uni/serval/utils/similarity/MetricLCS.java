package edu.lu.uni.serval.utils.similarity;

import java.util.List;

/**
 * Distance metric based on Longest Common Subsequence, 
 * from the notes "An LCS-based string metric" by Daniel Bakkelund.
 * 
 * @author kui.liu
 *
 */
public class MetricLCS implements Similarity {

    private final LongestCommonSubsequence lcs = new LongestCommonSubsequence();

    /**
     * Distance metric based on Longest Common Subsequence, computed as
     * 1 - |LCS(str1, str2)| / max(|str1|, |str2|).
     *
     * @param s1 The first string to compare.
     * @param s2 The second string to compare.
     * @return The computed distance metric value.
     * @throws NullPointerException if s1 or s2 is null.
     */
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

        int maxLength = Math.max(str1.length(), str2.length());
        if (maxLength == 0) {
            return 0d;
        }
        return 1.0 - (1.0 * lcs.similarity(str1, str2)) / maxLength;
	}

	@Override
	public <T> Double similarity(List<T> l1, List<T> l2) {
		if (l1 == null) {
            throw new NullPointerException("s1 must not be null");
        }

        if (l2 == null) {
            throw new NullPointerException("s2 must not be null");
        }

        if (l1.equals(l2)) {
            return 0d;
        }

        int maxSize = Math.max(l1.size(), l2.size());
        if (maxSize == 0) {
            return 0d;
        }
        return 1.0 - (1.0 * lcs.similarity(l1, l2)) / maxSize;
	}
}
