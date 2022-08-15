package com.beerair.core.fixture.fake;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import static org.mockito.Mockito.mock;

public class FakeClientRegistrationRepository implements ClientRegistrationRepository {
    @Override
    public ClientRegistration findByRegistrationId(String registrationId) {
        return mock(ClientRegistration.class);
    }
}
