package org.viaggi.xmltv.grabber.tvguide.parsing;

import org.apache.commons.lang.builder.ToStringBuilder;

public class GuideHeader {

	public enum FIELD_NAME {
		
		START_DATE_TIME(0),
		START_DATE_TIME1(1),
		TIME_INCREMENT(2),
		END_DATE_TIME(3),
		END_DATE_TIME1(4),
		UNKNOWN_1(5),
		UNKNOWN_2(6),
		REGION_ID(7),
		REGION_NAME(8),
		BROADCAST_TYPE(9),
		TIME_SLOTS(10),
		RETRIEVAL_TIME(11),
		MAGIC(12);

		private int index;
		
		private FIELD_NAME(int inextval) {
			index = inextval;
		}
		
		public int getIndex() {
			return index;
		}
	}
	String header;
	String fields[];
	
	public GuideHeader(String row) {
		header = row;
		fields = header.split("\t");
	}
	
	public String getHeader() {
		return header;
	}
	
	public String get(FIELD_NAME field) {
		return fields[field.getIndex()];
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(GuideHeader.class).append("[");
		
		for(FIELD_NAME field : FIELD_NAME.values()) {
			sb.append(field.name()).append("=").append(get(field)).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		
		return sb.toString();
	}
}
