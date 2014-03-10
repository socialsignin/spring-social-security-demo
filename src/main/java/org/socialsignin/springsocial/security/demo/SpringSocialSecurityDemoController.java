package org.socialsignin.springsocial.security.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.UserIdSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SuppressWarnings({"unchecked","rawtypes"})
public class SpringSocialSecurityDemoController {

	@Autowired
	private UserIdSource userIdSource;
	

	@RequestMapping("/")
	public String helloPublicWorld(Map model) {
		model.put("userName", getUserName());

		// Display on the jsp which security level the page is intended for
		model.put("securityLevel", "Public");

		return "helloWorld";
	}

	@RequestMapping("/protected")
	public String helloProtectedWorld(Map model) {
		model.put("userName", getUserName());

		// Display on the jsp which security level the page is intended for
		model.put("securityLevel", "Protected");

		return "helloWorld";
	}
	
	@RequestMapping("/protected/twitter")
	public String helloTwitterProtectedWorld(Map model) {
		model.put("userName", getUserName());

		// Display on the jsp which security level the page is intended for
		model.put("securityLevel", "Twitter Protected");

		return "helloWorld";
	}
	
	@RequestMapping("/protected/facebook")
	public String helloFacebookProtectedWorld(Map model) {
		model.put("userName", getUserName());

		// Display on the jsp which security level the page is intended for
		model.put("securityLevel", "Facebook Protected");

		return "helloWorld";
	}
	
	@RequestMapping("/protected/facebookTwitter")
	public String helloFacebookAndTwitterProtectedWorld(Map model) {
		model.put("userName", getUserName());

		// Display on the jsp which security level the page is intended for
		model.put("securityLevel", "Facebook and Twitter Protected");

		return "helloWorld";
	}
	
	private String getUserName()
	{
		try
		{
			return  userIdSource.getUserId();
		}
		catch (IllegalStateException e)
		{
			// No user signed in
			return null;
		}
	}
	
	

}
