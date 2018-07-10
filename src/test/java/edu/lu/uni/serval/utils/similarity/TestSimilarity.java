package edu.lu.uni.serval.utils.similarity;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestSimilarity {

	private Similarity sim = null;
	
	@Test
	public void testJaccardSimilarity() {
		sim = new Jaccard();
		List<String> l1 = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l1.add("test");
		l1.add("jaccard");
		l1.add("similarity");
		l2.add("test1");
		l2.add("jaccard");
		l2.add("similarity1");
		assertEquals(Double.valueOf(1d / 5), sim.similarity(l1, l2));
	}
	
	@Test
	public void testMostFreqKDistance() {
		String str1 = "aaaaabbbb";
		String str2 = "ababababa";
		assertEquals(Double.valueOf(8d), new MostFreqKDistance().similarity(str1, str2));
	}
	
	@Test
	public void testMostFreqKDistance2() {
		sim = new MostFreqKDistance();
		List<String> l1 = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l1.add("test");
		l1.add("jaccard");
		l1.add("similarity");
		l2.add("test1");
		l2.add("jaccard");
		l2.add("similarity1");
		assertEquals(Double.valueOf(8d), sim.similarity(l1, l2));
	}
	
	@Test
	public void testLevenshteinDistance() {
		String a = "gumbo";
		String b = "gaumbol";
		assertEquals(Double.valueOf(2d), new LevenshteinDistance().similarity(a, b));
	}
	
	@Test
	public void testLevenshteinDistance2() {
		List<String> l1 = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l1.add("test");
		l1.add("jaccard");
		l1.add("similarity");
		l2.add("test1");
		l2.add("jaccard");
		l2.add("similarity1");
		sim = new LevenshteinDistance();
		assertEquals(Double.valueOf(2d), sim.similarity(l1, l2));
	}
	
	@Test
	public void testDamerauLevenshteinDistance() {
		String a = "gumlbo";
		String b = "gaumbol";
		assertEquals(Double.valueOf(3d), new DamerauLevenshteinDistance().similarity(a, b));
	}
	
	@Test
	public void testDamerauLevenshteinDistance2() {
		List<String> l1 = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l1.add("test");
		l1.add("jaccard");
		l1.add("similarity");
		l2.add("test1");
		l2.add("jaccard");
		l2.add("similarity1");
		sim = new DamerauLevenshteinDistance();
		assertEquals(Double.valueOf(2d), sim.similarity(l1, l2));
	}
}
