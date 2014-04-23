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

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;

/**
 * Route with filter configured. 
 * 
 * It will accept messages only if message/subject contains 'Camel'
 * @author Developer
 *
 */

public class EmailRouteBuilderWithFilter extends RouteBuilder {

	public EmailRouteBuilderWithFilter() {
	}

	public EmailRouteBuilderWithFilter(CamelContext context) {
		super(context);

	}

	@Override
	public void configure() throws Exception {

		PropertiesComponent pc = getContext().getComponent("properties",
				PropertiesComponent.class);
		pc.setLocation("classpath:mail.properties");

		getContext().getShutdownStrategy().setTimeout(10);

		from("pop3://localhost?username={{smtp.username}}&password={{smtp.password}}&delete=true&unseen=true&consumer.delay=60000&searchTerm.subjectOrBody=Camel")
		.process(new MailAttachmentProcessor())
		.to("file://receivedCamelEmails");

	}

}
