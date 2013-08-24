package org.viaggi.xmltv.grabber.tvguide.options;

/**
 * (1 -- do not display, 0 -- display)
 * 
 * @author cviaggi
 *
 */
public enum TwentFourHourProgramming {

	DISPLAY("0"),
	DO_NOT_DISPLAY("1");
	
	private String uri_value;
	
	private TwentFourHourProgramming(String value) {
		uri_value = value;
	}
	
	public String getValue() {
		return uri_value;
	}
}
