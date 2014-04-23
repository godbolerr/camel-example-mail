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

This example can either run as a Camel client or server.

* The client will send test email to the server as per settings in the properties file.

* The server will receive email from the server and store the same local file system

To run the client you type:

  mvn compile exec:java -Pclient


To run the server you type:
  mvn compile exec:java -Pserver
  

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
