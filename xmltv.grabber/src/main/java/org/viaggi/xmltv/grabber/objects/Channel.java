package org.viaggi.xmltv.grabber.objects;

public interface Channel {

	public static final String ID_PREFIX = "tvguide";

	public abstract String getId();

	public abstract String getIcon();

	public abstract String[] getDisplayNames();

	public abstract String printString();

	public abstract int hashCode();

	public abstract boolean equals(Object obj);

}