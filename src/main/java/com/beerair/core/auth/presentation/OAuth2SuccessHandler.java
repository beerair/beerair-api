package com.beerair.core.auth.presentation;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
}
