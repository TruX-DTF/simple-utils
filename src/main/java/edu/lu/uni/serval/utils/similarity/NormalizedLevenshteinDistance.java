package edu.lu.uni.serval.utils.similarity;

import java.util.List;

/**
 * The similarity between two strings is inversely proportional to the simialrity value.
 * @author kui.liu
 *
 */
public class NormalizedLevenshteinDistance implements Similarity {
	
	private LevenshteinDistance ld = new LevenshteinDistance();

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

        int m_len = Math.max(str1.length(), str2.length());

        if (m_len == 0) {
            return 0d;
        }
		return ld.similarity(str1, str2) / m_len;
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

        int m_len = Math.max(l1.size(), l2.size());

        if (m_len == 0) {
            return 0d;
        }
		return ld.similarity(l1, l2) / m_len;
	}

}
