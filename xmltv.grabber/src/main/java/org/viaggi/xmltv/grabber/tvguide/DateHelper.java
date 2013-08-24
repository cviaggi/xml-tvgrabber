package org.viaggi.xmltv.grabber.tvguide;

import java.util.Calendar;

public class DateHelper {

	public enum TIME_INCREMENT {
		TWO_HOURS(2);
		
		private int value;
		
		private TIME_INCREMENT(int setVal) {
			value = setVal;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	Calendar current;
	
	public DateHelper() {
		current = Calendar.getInstance();
	}
	
	public String getYear() {
		return Integer.toString(current.get(Calendar.YEAR));
	}
	
	public String getMonth() {
		return Integer.toString(current.get(Calendar.MONTH) + 1);
	}
	
	public String getDay() {
		return Integer.toString(current.get(Calendar.DATE));
	}

	public String getHour() {
		return Integer.toString(current.get(Calendar.HOUR_OF_DAY));
	}

	public void incrementDate(TIME_INCREMENT increment) {
		
		switch(increment) {
			case TWO_HOURS:
			default:
				current.add(Calendar.HOUR_OF_DAY, 2);
			
		}
	}
}
