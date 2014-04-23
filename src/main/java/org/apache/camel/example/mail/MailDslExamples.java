/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example.mail;

import org.apache.camel.ProducerTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Starts all the routes as per the dsl
 */
public final class MailDslExamples {

  
    private MailDslExamples() {
 
    }

    public static void main(final String[] args) throws Exception {
    	

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");

        // get the camel template for Spring template style sending of messages (= producer)
        final ProducerTemplate producer = context.getBean("camelTemplate", ProducerTemplate.class);
	
        producer.sendBodyAndHeader("direct:sendSmtpMail", "Test mail from smtp", "subject", "First Test Mail with content type");

        // send mail with password encrypted in properties file jasypt
        
        producer.sendBodyAndHeader("direct:sendSmtpMailEncryptedPassword", "Test mail from smtp", "subject", "First Test Mail with content type");

       
        
        // Keep running as listener need to be up for receiving messages.
        Thread.sleep(10000000);
  
       //  IOHelper.close(context);
    }

}
