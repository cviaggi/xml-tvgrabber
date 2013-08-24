package org.viaggi.xmltv.grabber.objects;

import java.util.HashSet;

public interface Guide {

	public void addRawData(String rawData) throws Exception;
	
	public HashSet<Program> getPrograms();
	public HashSet<Channel> getChannels();
	
}
