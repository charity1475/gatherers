package org.mjumbe.config;

import java.util.Map;
import java.util.Objects;

public class AppConfig {
	private final String httpRequestUrl;
	private final String redisHost;
	private final int redisPort;
	private final String redisPassword;
	private final String redisKey;
	private final String decoderName;
	private final String country;
	private final int pageSize;

	public AppConfig() {
		this.httpRequestUrl = System.getenv("DECODER_HTTP_URL");
		this.redisHost = System.getenv("REDIS_HOST");
		this.redisKey = System.getenv("REDIS_KEY");
		this.decoderName = System.getenv("DECODER_NAME");
		String redisPortStr = System.getenv("REDIS_PORT");
		this.redisPort = (redisPortStr != null && !redisPortStr.isEmpty())
				? Integer.parseInt(redisPortStr) : 6379;
		this.redisPassword = System.getenv("REDIS_PASSWORD");
		this.country = System.getenv("COUNTRY");
		this.pageSize = Integer.parseInt(System.getenv("PAGE_SIZE"));
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
		return String.format("%s:%s", redisKey, country);
	}
	public String getDecoderName() {
		return decoderName;
	}
	public String getCountry() {
		return country;
	}
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public String toString() {
		Map<String, String> config = Map.of(
				"decoderUrl", httpRequestUrl,
				"redisHost", redisHost,
				"redisPort", String.valueOf(redisPort),
				"decoderName", redisPassword,
				"country", country
		);
		return String.format("AppConfig: ", config);
	}
}
