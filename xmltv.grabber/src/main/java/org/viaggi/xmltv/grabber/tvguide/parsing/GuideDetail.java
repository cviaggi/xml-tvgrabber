package org.viaggi.xmltv.grabber.tvguide.parsing;

import org.apache.log4j.Logger;

public class GuideDetail {

	  static Logger log = Logger.getLogger(GuideDetail.class.getName());

	  public enum FIELD_NAME {
		
		  PROGRAM_ID(0),
		  PROGRAM_TITLE(1),
		  EPISODE_NAME(2),
		  EPISODE_DESCRIPTION(3),
		  RATING(7),
		  YEAR(9),
		  IMAGE_URL(16),
		  PROGRAM_PATH(17);

		private int index;
		
		private FIELD_NAME(int inextval) {
			index = inextval;
		}
		
		public int getIndex() {
			return index;
		}
	  }
	
	String line;
	String fields[];

	public GuideDetail(String row) {
		line = row;
		fields = row.split("\t");
	}
	
	public String getLine() {
		return line;
	}
	
	public String getSubTitle() {

		if(fields[FIELD_NAME.EPISODE_NAME.getIndex()].length() > 0) {
			return fields[FIELD_NAME.EPISODE_NAME.getIndex()];
		} else {
			return "";
		}

	}
	
	public String getIcon() {
		
		if(!fields[FIELD_NAME.IMAGE_URL.getIndex()].equalsIgnoreCase("NO-IMAGE")) {
			return fields[FIELD_NAME.IMAGE_URL.getIndex()];
		} else {
			return "";
		}
	}
	
	public String get(FIELD_NAME field) {
		
		if(field.getIndex() < fields.length) {
			return fields[field.getIndex()];
		} else {
			return "";
		}
	}

	public String printLine(String string) {
		StringBuilder sb = new StringBuilder();

		for(FIELD_NAME field : FIELD_NAME.values()) {
			sb.append(this.get(field)).append(string);
		}
		
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	public String printString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(GuideDetail.class).append("@").append(System.identityHashCode(this));
		sb.append("[");
		
		for(FIELD_NAME field : FIELD_NAME.values()) {
			sb.append(field.name()).append("=").append(this.get(field)).append(",");
		}
		
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		
		return sb.toString();
	}
}
