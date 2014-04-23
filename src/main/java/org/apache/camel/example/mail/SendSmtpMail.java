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
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Client for sending mails over smtp.
 * @author Developer
 *
 */
public class SendSmtpMail {

	public static void main(String args[]) throws Exception {

		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() {

				PropertiesComponent pc = getContext().getComponent(
						"properties", PropertiesComponent.class);
				pc.setLocation("classpath:mail.properties");

				from("direct:start")
				.to("smtp://{{smtp.host}}?username={{smtp.username}}&password={{smtp.password}}&from={{smtp.from.email}}&contentType={{contentType}}")
				.log("Email sent with content ${in.body}");

			}
		});

		// start the route and let it do its work
		
		context.start();
		


		ProducerTemplate template = context.createProducerTemplate();
		
		template.sendBodyAndHeader("direct:start", "Test mail from smtp", "subject", "First Test Mail with content type");

		// stop the CamelContext
		
		context.stop();
	}
}
