package org.viaggi.xmltv.grabber.tvguide.options;

/**
 * 
 *  (0 -- all channels, 2 -- movies only, 3 -- sports only, 4 -- family only, 6 -- news only)
 *  
 * @author cviaggi
 *
 */
public enum Genre {

	ALL_CHANNELS("0"),
	MOVIES_ONLY("2"),
	SPORTS_ONLY("3"),
	FAMILY_ONLY("4"),
	NEWS_ONLY("6");
	
	private String uri_value;
	
	private Genre(String value) {
		uri_value = value;
	}
	
	public String getValue() {
		return uri_value;
	}
}

