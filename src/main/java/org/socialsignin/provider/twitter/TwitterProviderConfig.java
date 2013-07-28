package org.socialsignin.provider.twitter;

import org.socialsignin.provider.AbstractProviderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.config.annotation.EnableTwitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
public class TwitterProviderConfig extends AbstractProviderConfig<Twitter> {
	
	@Autowired
	private TwitterConnectInterceptor twitterConnectInterceptor;

	@Override
	protected ConnectInterceptor<Twitter> getConnectInterceptor() {
		return twitterConnectInterceptor;
	}
	
	

}
