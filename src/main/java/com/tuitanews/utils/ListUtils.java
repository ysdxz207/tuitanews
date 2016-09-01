package com.tuitanews.utils;

import java.util.HashSet;
import java.util.List;

public class ListUtils {

	/**
	 * 判断list中是否有重复元素
	 * 
	 * @param list
	 * @return
	 */
	public static boolean hasRepeat(List<? extends Object> list) {
		if (null == list)
			return false;
		return list.size() == new HashSet<Object>(list).size();
	}

	/**
	 * 判断list中元素是否完全相同
	 * 
	 * @param list
	 * @return
	 */
	public static boolean hasSame(List<? extends Object> list) {
		if (null == list)
			return false;
		return 1 == new HashSet<Object>(list).size();
	}
}
