/**
 * 
 */
package com.matrimony.database;

import java.util.Comparator;
import java.util.Map;

import com.matrimony.entity.User;
import com.matrimony.model.MeasureHeart;

/**
 * @author SON
 *
 */
public class ValueMapComparator implements Comparator<User> {

	private Map<User, MeasureHeart> map;

	/**
	 * 
	 */
	public ValueMapComparator(Map<User, MeasureHeart> map) {
		this.map = map;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(User o1, User o2) {
		if (map.get(o1).getPercent() > map.get(o2).getPercent())
			return 1;
		else
			return -1;
	}

}
