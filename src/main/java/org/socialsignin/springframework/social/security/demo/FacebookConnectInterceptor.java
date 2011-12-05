package org.socialsignin.springframework.social.security.demo;

import org.socialsignin.springframework.social.security.signin.SpringSocialSecurityConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;

@Component
public class FacebookConnectInterceptor extends
		SpringSocialSecurityConnectInterceptor<Facebook> {

}
