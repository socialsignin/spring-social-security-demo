package org.socialsignin.springframework.social.security.demo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

@Configuration
public class SpringSocialSecurityDemoWebappConfig {

	@Autowired
	private DataSource dataSource;

	/**
	 * Used to configure the in-memory HSQLDB database Remove this method if
	 * different datasource is used
	 * 
	 * @throws IOException
	 */
	@PostConstruct
	public void createDatabaseTable() throws IOException {
		Resource resource = new ClassPathResource(
				"org/springframework/social/connect/jdbc/JdbcUsersConnectionRepository.sql");
		BufferedInputStream is = new BufferedInputStream(
				resource.getInputStream());
		final char[] buffer = new char[0x10000];
		StringBuilder out = new StringBuilder();
		Reader in = new InputStreamReader(resource.getInputStream(), "UTF-8");
		int read;
		do {
			read = in.read(buffer, 0, buffer.length);
			if (read > 0) {
				out.append(buffer, 0, read);
			}
		} while (read >= 0);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute(out.toString());

	}

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = connectionFactoryRegistry();
		registry.addConnectionFactory(new TwitterConnectionFactory(
				twitterConsumerKey, twitterConsumerSecret));
		registry.addConnectionFactory(new FacebookConnectionFactory(
				facebookClientId, facebookClientSecret));
		return registry;
	}

	@Bean
	public ConnectionFactoryRegistry connectionFactoryRegistry() {
		return new ConnectionFactoryRegistry();
	}

	@Value("${twitter.consumerKey}")
	private String twitterConsumerKey;

	@Value("${twitter.consumerSecret}")
	private String twitterConsumerSecret;

	@Value("${facebook.clientId}")
	private String facebookClientId;

	@Value("${facebook.clientSecret}")
	private String facebookClientSecret;

	@Bean
	public DefaultAnnotationHandlerMapping handlerMapping() throws Exception {

		DefaultAnnotationHandlerMapping mapping = new DefaultAnnotationHandlerMapping();

		return mapping;

	}

	@Bean
	public AnnotationMethodHandlerAdapter handlerAdapter() throws Exception {

		AnnotationMethodHandlerAdapter mapping = new AnnotationMethodHandlerAdapter();

		return mapping;

	}

}
