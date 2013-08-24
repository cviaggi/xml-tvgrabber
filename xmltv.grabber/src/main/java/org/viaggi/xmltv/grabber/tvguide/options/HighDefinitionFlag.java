package org.viaggi.xmltv.grabber.tvguide.options;

/**
 * 
 * @author cviaggi
 *
 */
public enum HighDefinitionFlag {

	HD_ONLY("true"),
	ALL_CHANNELS("false");
	
	private String uri_value;
	
	private HighDefinitionFlag(String value) {
		uri_value = value;
	}
	
	public String getValue() {
		return uri_value;
	}
}
