/**
 * 
 */
package model;

import java.sql.Date;
import java.util.Calendar;

import org.apache.commons.lang3.time.DateUtils;

/**
 * @author SON
 *
 */
public class JSLTFunctionUtil {
	public int yearUntilToDay(Date date) {
		Calendar now = DateUtils.toCalendar(new Date(System.currentTimeMillis()));
		Calendar before = DateUtils.toCalendar(date);
		return now.get(Calendar.YEAR) - before.get(Calendar.YEAR);
	}
}
