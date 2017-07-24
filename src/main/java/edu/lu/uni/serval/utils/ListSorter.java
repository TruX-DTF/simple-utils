package edu.lu.uni.serval.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSorter<T extends Comparable<? super T>> {

	private List<T> list;

	public ListSorter(List<T> list) {
		this.list = new ArrayList<>();
		this.list.addAll(list);
	}

	public List<T> getList() {
		return this.list;
	}

	public List<T> sortAscending() {
		if (list != null && list.size() > 0) {
			Collections.sort(this.list, Collections.reverseOrder());
			Collections.reverse(this.list);
		}
		return this.list;
	}

	public List<T> sortDescending() {
		if (list != null && list.size() > 0) {
			Collections.sort(this.list, Collections.reverseOrder());
		}
		return this.list;
	}
}
