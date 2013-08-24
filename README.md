xml-tvgrabber
=============

Java XML TV Grabber, scrapes guide data in the US.

The scrapper will scrape TV Guide data from the TVGuide.com website. It will then format it into a xmltv format.
 
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

The property file is a simple key/value pair with the followingu  keys:

-- guide.output.dir
-- guide.output.file
-- guide.scraper.retrieval.hours

The options allow you to set the output directory, the file name, and the number of hours to retrieve.

Running
=======

To run the program perform the command: java -jar xmltv.grabber.<version-number>.jar <file>

To get more detailed help run the command: java -jar xmltv.grabber.<version-number>.jar -h

Logging
=======

The program will generate a log4j log file in /tmp/logs directory. If you are running this on windows then 
you will have to change the log directory location in the log4j.properties file and recompile the code.
