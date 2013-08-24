package org.viaggi.xmltv.grabber.tvguide.parsing;

import org.apache.log4j.Logger;
import org.viaggi.xmltv.grabber.tvguide.objects.TVChannel;
import org.viaggi.xmltv.grabber.tvguide.objects.TVProgram;

public class GuideLine {

	  static Logger log = Logger.getLogger(GuideLine.class.getName());

	public enum FIELD_NAME {
		
		SORT_ORDER(0),
		CHANNEL_NUMBER(1),
		CALL_LETTERS(2),
		NETWORK_ID(3),
		PROGRAM_TITLE(4),
		BLOCK_COUNT(5), //Value of 24 -- indicates its 24 hour programming (not sure how long blocks are)
		CATEGORY(6), //(bitwise operations: value & 256 = 256 -- news, value & 64 = 64 -- movies, value & 2 = 2 -- family, 1 -- regular programming, value & 1024 = 1024 -- sports
		SUB_CATEGORY(7), //
		SOURCE_TYPE(8), //
		START_BEFORE(9), //if the show starts before the time slot (0 -- false, 2 -- true). only for 24 hour programming
		ENDS_AFTER(10), //if the show ends after the time slot (0 -- false, 2 -- true). only for 24 hour programming
		ATTRIBUTES(11), //bitwise operations: value & 4 = 4 -- new, value & 2 = 2 -- repeat, value & 8 = 8 -- cc 
		PROGRAM_ID(12), 
		SOURCE_ID(13),
		START_TIME(14), //Parse time as "YYYYMMDDHHMM"
		DURATION(15), //Duration in minutes
		TV_OBJECT_ID(16),
		HAS_VIDEO(17), //
		RATING(18); //may not be present

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

	public GuideLine(String row) {
		line = row;
		fields = row.split("\t");
	}
	
	public String getLine() {
		return line;
	}
	
	public TVProgram getProgram() {
	
		TVProgram program = new TVProgram();
		
		program.setAttributes(get(FIELD_NAME.ATTRIBUTES));
		program.setBlockCount(get(FIELD_NAME.BLOCK_COUNT));
		program.setCategory(get(FIELD_NAME.CATEGORY));
		program.setDuration(get(FIELD_NAME.DURATION));
		program.setObjectId(get(FIELD_NAME.TV_OBJECT_ID));
		program.setProgramId(get(FIELD_NAME.PROGRAM_ID));
		program.setProgramTitle(get(FIELD_NAME.PROGRAM_TITLE));
		program.setRating(get(FIELD_NAME.RATING));
		program.setSourceId(get(FIELD_NAME.SOURCE_ID));
		program.setSourceType(get(FIELD_NAME.SOURCE_TYPE));
		program.setStartTime(get(FIELD_NAME.START_TIME) + "00"); //make sure we add the seconds
		program.setSubCategory(get(FIELD_NAME.SUB_CATEGORY));
		program.setChannel(new TVChannel(get(FIELD_NAME.CHANNEL_NUMBER), get(FIELD_NAME.CALL_LETTERS), get(FIELD_NAME.NETWORK_ID)));
		
		return program;
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
		
		sb.append(GuideLine.class).append("@").append(System.identityHashCode(this));
		sb.append("[");
		
		for(FIELD_NAME field : FIELD_NAME.values()) {
			sb.append(field.name()).append("=").append(this.get(field)).append(",");
		}
		
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		
		return sb.toString();
	}
}
