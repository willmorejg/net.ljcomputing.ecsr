/**
           Copyright 2015, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.people.web.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author James G. Willmore
 *
 */
@Configuration
public class XmlWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(XmlWebMvcConfigurerAdapter.class);

	/**
	 * Instantiates a new gson web mvc config adapter.
	 */
	public XmlWebMvcConfigurerAdapter() {
		logger.info("Initializing {}", this.getClass());
	}

	/**
	 * Configure message converters.
	 *
	 * @param converters
	 *            the converters
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureMessageConverters(java.util.List)
	 */
	@Override
	public final void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {

		List<MediaType> mediaType = new ArrayList<>();
		mediaType.add(MediaType.APPLICATION_XML);
		mediaType.add(MediaType.TEXT_XML);

		XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();

		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();

		xmlConverter.setSupportedMediaTypes(mediaType);
		xmlConverter.setMarshaller(xstreamMarshaller);
		xmlConverter.setUnmarshaller(xstreamMarshaller);

		converters.add(xmlConverter);
	}
}
