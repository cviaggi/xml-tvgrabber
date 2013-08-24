package org.viaggi.xmltv.grabber.tvguide.parsing;

import org.apache.log4j.Logger;

public class GuideOptions {

	  static Logger log = Logger.getLogger(GuideOptions.class.getName());

	  public enum FIELD_NAME {
		
		  REGION_ID_PREFIX(0),
		  REGION_ID_SUFFIX(1),
		  SERVICE_TYPE(2),
		  PROVIDER_NAME(3),
		  PROVIDER_TYPE(4);

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

	public GuideOptions(String row) {
		line = row;
		fields = row.split("\t");
	}
	
	public String getLine() {
		return line;
	}
	
	public String get(FIELD_NAME field) {
		
		if(field.getIndex() < fields.length) {
			return fields[field.getIndex()];
		} else {
			return "";
		}
	}

	public String getTableLine() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(getServiceId());
		
		while(sb.length() < 20) {
			sb.append(" ");
		}
		sb.append(getServiceType());

		while(sb.length() < 40) {
			sb.append(" ");
		}

		sb.append(getProvider());
		
		return sb.toString();
		
	}
	public String getServiceId() {
		return fields[FIELD_NAME.REGION_ID_PREFIX.getIndex()] + "." + fields[FIELD_NAME.REGION_ID_SUFFIX.getIndex()];
	}
	
	public String getServiceType() {
		return fields[FIELD_NAME.SERVICE_TYPE.getIndex()];
	}

	public String getProvider() {
		return fields[FIELD_NAME.PROVIDER_NAME.getIndex()] + " (" + fields[FIELD_NAME.PROVIDER_TYPE.getIndex()] + ")";
	}

	
	public String printString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(GuideOptions.class).append("@").append(System.identityHashCode(this));
		sb.append("[");
		
		for(FIELD_NAME field : FIELD_NAME.values()) {
			sb.append(field.name()).append("=").append(this.get(field)).append(",");
		}
		
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		
		return sb.toString();
	}
}
