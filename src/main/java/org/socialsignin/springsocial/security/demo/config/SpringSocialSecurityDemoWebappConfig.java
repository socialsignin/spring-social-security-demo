package org.socialsignin.springsocial.security.demo.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class SpringSocialSecurityDemoWebappConfig {
	

	// Handle to users connection repository - allows us to set connection sign up in post construct
	@Autowired
	private UsersConnectionRepository jdbcUsersConnectionRepository;
	
	@Autowired
	private ConnectionSignUp springSocialSecurityConnnectionSignUp;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserIdSource userIdSource;

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
