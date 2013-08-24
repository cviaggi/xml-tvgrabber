xml-tvgrabber
=============

Java XML TV Grabber, scrapes guide data in the US.

The scrapper will scrape TV Guide data from the TVGuide.com website. It will then format it into a xmltv format.

Prerequisits
============

The application was written and compiled against the open JDK 1.7, maven version 3.0.4. There is nothing inheirent 
to 1.7 so this should run and compile against 1.5.
 
Compiling
==========

After retrieving the repository perform the command: mvn clean install

Packaging
=========

If your compile was successful you can packge the java code with the command: mvn clean compile assembly:single

Property File
=============

There are various settable properties which can be added to a standard property file and then passed into the 
application as a runtime parameters. These parameters are documented in the help and more information can be 
found there. 

The property file is a simple key/value pair with the following keys:

-- guide.output.dir
-- guide.output.file
-- guide.scraper.retrieval.hours
-- guide.id.region
-- guide.id.zipcode

The options allow you to set the output directory, the file name, and the number of hours to retrieve. The 
id region and zipcode must be set appropriately. To determine your region id, see the following section 
about finding your region. The zipcode should be set to your zipcode.

Running
======= 

To run the program perform the command: java -jar xmltv.grabber.<version-number>.jar <file>

To get more detailed help run the command: java -jar xmltv.grabber.<version-number>.jar -h

To find your region id run the command: java -jar xmltv.grabber.<version-number>.jar -l <zipcode>

Logging
=======

The program will generate a log4j log file in /tmp/logs directory. If you are running this on windows then 
you will have to change the log directory location in the log4j.properties file and recompile the code. 
Logging is set by default on the INFO setting. If you require more to troubleshoot change this to DEBUG.

Finding Your Region Id
======================

The region Id is what identifies your area and what guide data to retrieve. You will need to determine this 
but the application can help you do that. Issue the -l command with you zipcode.

java -jar xmltv.grabber.<version-number>.jar -l [zipcode]

You will see an output table which lists the region id, the service type, and the provider name. You 
will need to find your provider name and type, then take the region id and then set the property file 
attributes accordingly. 

A sample of the table would look like this:

guide.id.region     service type        provider name
-------------------------------------------------------------------------------------
82105.8             Cable               AT&T U-verse (San Francisco) (CableCard)
82105.16777216      Cable               AT&T U-verse (San Francisco) (Digital (non-rebuild))
361696.1            Cable               Comcast (Standard Cable)
361696.8            Cable               Comcast (CableCard)
361696.16777216     Cable               Comcast (Digital (non-rebuild))
85102.8             Satellite           Bell TV (CableCard)
85102.16777216      Satellite           Bell TV (Digital (non-rebuild))
61616.8             Satellite           C-Band Providers (CableCard)
61616.16777216      Satellite           C-Band Providers (Digital (non-rebuild))
889087.8            Satellite           DirecTV with Local channels (CableCard)
889087.16777216     Satellite           DirecTV with Local channels (Digital (non-rebuild))
76449.16777216      Satellite           Dish Network with Local channels (Digital (non-rebuild))
893181.8            Satellite           FAVE TV (CableCard)
893181.16777216     Satellite           FAVE TV (Digital (non-rebuild))
85097.8             Satellite           Shaw Direct (CableCard)
85097.16777216      Satellite           Shaw Direct (Digital (non-rebuild))
889977.8            Satellite           Shaw Direct Advanced (CableCard)
889977.16777216     Satellite           Shaw Direct Advanced (Digital (non-rebuild))
75996.8             Satellite           SkyAngel (CableCard)
75996.16777216      Satellite           SkyAngel (Digital (non-rebuild))
20552.268435456     OTA                 San Francisco Bay Area Broadcast (San Fran-Oak-Sj) (OTA Broadcast)

