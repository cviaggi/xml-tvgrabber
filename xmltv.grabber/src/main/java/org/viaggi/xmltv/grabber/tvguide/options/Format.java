package org.viaggi.xmltv.grabber.tvguide.options;

/**
 * 
 * (0 or 1) -- value of 1 seems to truncate name of show
 * 
 * @author cviaggi
 * 
 */
public enum Format {

	NORMAL("0"),
	TRUNCATED("1");
	
	private String uri_value;
	
	private Format(String value) {
		uri_value = value;
	}
	
	public String getValue() {
		return uri_value;
	}
}
