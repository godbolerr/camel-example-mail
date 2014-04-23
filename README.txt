Camel Mail example
=================

An example which shows how to integrate Camel with Mail [Email].

This example requires that an existing Email server is available.

You can configure the location of this Email server in the file:
  src/main/resources/mail.properties

Following examples are covered.

1. Send mail over smtp
2. send mail with password encrypted in properties file
3. Listen to mail server for incoming messages
4. Listen to gmail for incoming mails.


Both java and dsl versions are available.


You will need to compile this example first:

  mvn compile

These examples expect connectivity to existing email server as well as internet for testing gmail 

 To run these examples you can execute the following command
 
 mvn compile exec:java -PsendMail
 mvn compile exec:java -PemailListener
 mvn compile exec:java -PgmailListener
 mvn compile exec:java -PmailWithAttachment
 
There is a spring DSL which illustrates above route as well.


This example is documented at
  http://camel.apache.org/mail-example.html [TODO]

You can enable verbose logging by adjusting the src/main/resources/log4j.properties
  file as documented in the file.

If you hit any problems please let us know on the Camel Forums
  http://camel.apache.org/discussion-forums.html

Please help us make Apache Camel better - we appreciate any feedback you may
have.  Enjoy!

------------------------
The Camel riders!
