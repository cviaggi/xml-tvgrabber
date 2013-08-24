package org.viaggi.xmltv.grabber.objects;

import java.net.URI;
import java.text.ParseException;

import org.viaggi.xmltv.grabber.tvguide.objects.TVChannel;

public interface Program {

	public abstract boolean isNew();

	public abstract boolean isRepeat();

	public abstract boolean isCC();

	public abstract String[] getAllCategories();

	public abstract String getEndTime() throws ParseException;

	public abstract String getProgramTitle();

	public abstract void setProgramTitle(String programTitle);

	public abstract String getCategory();

	public abstract void setCategory(String category);

	public abstract String getBlockCount();

	public abstract void setBlockCount(String blockCount);

	public abstract String getSubCategory();

	public abstract void setSubCategory(String subCategory);

	public abstract String getSourceType();

	public abstract void setSourceType(String sourceType);

	public abstract String getAttributes();

	public abstract void setAttributes(String attributes);

	public abstract String getProgramId();

	public abstract void setProgramId(String programId);

	public abstract String getSourceId();

	public abstract void setSourceId(String sourceId);

	public abstract String getStartTime();

	public abstract void setStartTime(String startTime);

	public abstract String getDuration();

	public abstract void setDuration(String duration);

	public abstract String getObjectId();

	public abstract void setObjectId(String objectId);

	public abstract String getRating();

	public abstract void setRating(String rating);

	public abstract int hashCode();

	public abstract boolean equals(Object obj);

	public abstract Channel getChannel();

	public abstract void setChannel(TVChannel channel);

	public abstract String printString();

	public abstract URI getUrl();

	public abstract void setUrl(URI url);

	public abstract String getDescription();

	public abstract void setDescription(String description);

	public abstract String getSubTitle();

	public abstract void setSubTitle(String subTitle);

	public abstract String getIcon();

	public abstract void setIcon(String icon);

}