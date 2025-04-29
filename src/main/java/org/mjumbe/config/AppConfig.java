package org.mjumbe.config;

import java.util.Map;
import java.util.Objects;

public class AppConfig {
	private static final String HTTP_URL_ENV = "HTTP_REQUEST_URL";
	private static final String REDIS_HOST_ENV = "REDIS_HOST";
	private static final String REDIS_PORT_ENV = "REDIS_PORT";
	private static final String REDIS_PASSWORD_ENV = "REDIS_PASSWORD";
	private static final String REDIS_KEY = "REDIS_KEY";
	private static final String DECODER_NAME = "AZAM TV";
	private final String httpRequestUrl;
	private final String redisHost;
	private final int redisPort;
	private final String redisPassword;
	private final String redisKey;
	private final String decoderName;

	public AppConfig() {
		this.httpRequestUrl = Objects.requireNonNull(System.getenv(HTTP_URL_ENV),
				HTTP_URL_ENV + " Environment variable HTTP_URL_ENV not set");
		this.redisHost = Objects.requireNonNull(System.getenv(REDIS_HOST_ENV),
				REDIS_HOST_ENV + " Environment variable REDIS_HOST_ENV not set");
		this.redisKey = Objects.requireNonNull(System.getenv(REDIS_KEY),
				REDIS_KEY + " Environment variable REDIS_KEY not set");
		this.decoderName = Objects.requireNonNull(System.getenv(DECODER_NAME),
				DECODER_NAME + " Environment variable DECODER_NAME not set");
		String redisPortStr = System.getenv(REDIS_PORT_ENV);
		this.redisPort = (redisPortStr != null && !redisPortStr.isEmpty())
				? Integer.parseInt(redisPortStr) : 6379;
		this.redisPassword = System.getenv(REDIS_PASSWORD_ENV);
	}

	public String getHttpRequestUrl() {
		return httpRequestUrl;
	}
	public String getRedisHost() {
		return redisHost;
	}
	public int getRedisPort() {
		return redisPort;
	}
	public String getRedisPassword() {
		return redisPassword;
	}
	public String getRedisKey() {
		return redisKey;
	}
	public String getDecoderName() {
		return decoderName;
	}

	@Override
	public String toString() {
		Map<String, String> config = Map.of(
				HTTP_URL_ENV, httpRequestUrl,
				REDIS_HOST_ENV, redisHost,
				REDIS_PORT_ENV, String.valueOf(redisPort),
				REDIS_PASSWORD_ENV, redisPassword
		);
		return String.format("AppConfig { %s }", config);
	}
}
