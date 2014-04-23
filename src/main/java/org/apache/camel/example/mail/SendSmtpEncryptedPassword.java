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

import java.util.Collection;
import java.util.Iterator;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.EndpointConfiguration;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jasypt.JasyptPropertiesParser;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Use Jasypt lib for decrypting password encoded in the properties file.
 * 
 * The key required for decrypting is hardcoded in the program but can be taken from env variables as well.
 * 
 * Refer to Jasypt website on generating keys using batch files.
 * 
 * @author Developer
 *
 */
public class SendSmtpEncryptedPassword {

	public static void main(String args[]) throws Exception {

		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() {

				PropertiesComponent pc = getContext().getComponent(
						"properties", PropertiesComponent.class);
				pc.setLocation("classpath:mail.properties");
				JasyptPropertiesParser jpp = new JasyptPropertiesParser();
				jpp.setPassword("secret");
				pc.setPropertiesParser(jpp);

				from("direct:start")
				.to("smtp://{{smtp.host}}?username={{smtp.username}}&password={{smtp.encrypted.password}}&from={{smtp.from.email}}&contentType={{contentType}}")
				.log("Email sent with content ${in.body}. Logged in using encrypted password");

			}
		});

		// start the route and let it do its work
		
		context.start();
		
		Collection<Endpoint> epcol = context.getEndpoints();
		
		EndpointConfiguration conf = null;
		
		for (Iterator iterator = epcol.iterator(); iterator.hasNext();) {
			Endpoint endpoint = (Endpoint) iterator.next();
			
		//	System.out.println(endpoint);
			
			if ( endpoint.getEndpointUri().startsWith("smtp")) {
				conf = endpoint.getEndpointConfiguration();
			}
			
		//	System.out.println(endpoint.getEndpointKey() + " is the key ");
			
		}
		
			System.out.println(conf.getParameter("password"));
	

		ProducerTemplate template = context.createProducerTemplate();
		
		template.sendBodyAndHeader("direct:start", "Test mail from smtp", "subject", "First Test Mail with content type");

		// stop the CamelContext
		
		context.stop();
	}
}
