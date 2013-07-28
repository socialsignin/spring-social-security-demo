package org.socialsignin.springsocial.security.demo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.socialsignin.springsocial.security.config.annotation.EnableSpringSocialSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableJdbcConnectionRepository;
import org.springframework.social.facebook.config.annotation.EnableFacebook;
import org.springframework.social.twitter.config.annotation.EnableTwitter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
// Swap in the below annotation instead of no-arg version if implicit sign up is required
//@EnableJdbcConnectionRepository(connectionSignUpRef="springSocialSecurityConnectionSignUp")
@EnableJdbcConnectionRepository
@EnableSpringSocialSecurity
@EnableTwitter(appId = "${twitter.consumerKey}", appSecret = "${twitter.consumerSecret}")
@EnableFacebook(appId = "${facebook.clientId}", appSecret = "${facebook.clientSecret}")
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

	/**
	 * This is only needed because the official spring-social-security from SpringSocial is on the classpath
	 * @return
	 */
	@Bean
	public UserIdSource userIdSource() {
		return new UserIdSource() {			
			@Override
			public String getUserId() {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication == null) {
					throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
				}
				return authentication.getName();
			}
		};
	}

	@Bean
	public RequestMappingHandlerMapping handlerMapping() throws Exception {

		 RequestMappingHandlerMapping mapping = new  RequestMappingHandlerMapping();
		return mapping;
	}
	

	@Bean
	public RequestMappingHandlerAdapter handlerAdapter() throws Exception {

		RequestMappingHandlerAdapter mapping = new RequestMappingHandlerAdapter();

		return mapping;

	}

}
