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

import java.io.FileOutputStream;
import java.util.Map;

import javax.activation.DataHandler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Processor takes care of attachments in mail.
 * 
 * Attachments are downloaded to local file system.
 * 
 * @author Developer
 *
 */
public class MailAttachmentProcessor implements Processor  {

	public MailAttachmentProcessor() {
	}


	@Override
	public void process(Exchange exchange) throws Exception {
		Map<String, DataHandler> attachments = exchange.getIn().getAttachments();
	     if (attachments.size() > 0) {
	         for (String name : attachments.keySet()) {
	             DataHandler dh = attachments.get(name);
	             // get the file name
	             String filename = dh.getName();
	 
	             // get the content and convert it to byte[]
	             byte[] data = exchange.getContext().getTypeConverter()
	                               .convertTo(byte[].class, dh.getInputStream());
	 
	             // write the data to a file
	             FileOutputStream out = new FileOutputStream(filename);
	             out.write(data);
	             out.flush();
	             out.close();
	         }
	     }
	}

}
