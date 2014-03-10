package org.socialsignin.provider.facebook;

import org.socialsignin.provider.AbstractProviderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;

@Configuration
public class FacebookProviderConfig extends AbstractProviderConfig<Facebook> {
	
	
	@Autowired
	private ConnectInterceptor<Facebook> facebookConnectInterceptor;
	
	@Override
	protected ConnectInterceptor<Facebook> getConnectInterceptor() {
		return facebookConnectInterceptor;
	}
	

}
