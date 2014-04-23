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
 * Build route for listening to Gmail.
 * Gmail account must be configured for this program to work.
 * 
 * @author Developer
 *
 */

public class GmailRouteBuilder extends RouteBuilder {

	public GmailRouteBuilder() {
	}

	public GmailRouteBuilder(CamelContext context) {
		super(context);

	}

	@Override
	public void configure() throws Exception {

		PropertiesComponent pc = getContext().getComponent("properties",
				PropertiesComponent.class);
		pc.setLocation("classpath:mail.properties");

		getContext().getShutdownStrategy().setTimeout(10);

		from("imaps://imap.gmail.com?username={{gmail.username}}&password={{gmail.password}}&delete=false&unseen=true&consumer.delay=60000")
		.process(new MailAttachmentProcessor())
		.to("file://gmails");

	}

}
