package org.socialsignin.springframework.social.security.demo;

import org.socialsignin.springframework.social.security.signin.SpringSocialSecurityConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;

@Component
public class TwitterConnectInterceptor extends
		SpringSocialSecurityConnectInterceptor<Twitter> {

}
