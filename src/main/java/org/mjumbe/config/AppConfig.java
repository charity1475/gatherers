package org.mjumbe.config;

public class AppConfig {
	public String httpRequestUrl;
	public String redisHost;
	public int redisPort;
	public String redisPassword;
	public String redisKey;
	public String decoderName;
	public String country;
	public int pageSize;

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
		String pageSizeStr = System.getenv("PAGE_SIZE");
		this.pageSize = (pageSizeStr != null && !pageSizeStr.isEmpty())
				? Integer.parseInt(System.getenv("PAGE_SIZE")) : 5000;
	}

	@Override
	public String toString() {
		return "AppConfig{" +
				"httpRequestUrl='" + httpRequestUrl + '\'' +
				", redisHost='" + redisHost + '\'' +
				", redisPort=" + redisPort +
				", redisPassword='" + (redisPassword != null ? "******" : null) + '\'' +
				", redisKey='" + redisKey + '\'' +
				", decoderName='" + decoderName + '\'' +
				", country='" + country + '\'' +
				", pageSize=" + pageSize +
				'}';
	}
}
