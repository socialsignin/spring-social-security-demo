package org.socialsignin.provider.twitter;

import org.socialsignin.provider.AbstractProviderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.twitter.api.Twitter;

@Configuration
public class TwitterProviderConfig extends AbstractProviderConfig<Twitter> {
	
	@Autowired
	private ConnectInterceptor<Twitter> twitterConnectInterceptor;

	@Override
	protected ConnectInterceptor<Twitter> getConnectInterceptor() {
		return twitterConnectInterceptor;
	}
	
}
