package org.multitower.game.server;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

@SpringBootTest
class GameServerApplicationTests {

	@TestConfiguration
	static class TestConfig {
		@Bean
		public ReactiveClientRegistrationRepository clientRegistrationRepository() {
			return Mockito.mock(ReactiveClientRegistrationRepository.class);
		}

		@Bean
		public ReactiveJwtDecoder reactiveJwtDecoder() {
			return Mockito.mock(ReactiveJwtDecoder.class);
		}
	}

	@Test
	void contextLoads() {
	}

}
