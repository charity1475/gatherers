package org.mjumbe;

import org.mjumbe.config.AppConfig;
import org.mjumbe.http.DataFetcher;
import org.mjumbe.redis.DataCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			AppConfig config = new AppConfig();
			logger.info("AppConfig loaded : {}", config);
			logger.info("Starting  {} scrape job at {} ...", config.getDecoderName(), java.time.LocalDateTime.now());
			DataFetcher dataFetcher = new DataFetcher();

			try (DataCache dataCache = new DataCache(config.getRedisHost(), config.getRedisPort(), config.getRedisPassword())) {
				String fetchedData = dataFetcher.fetchData(config);
				logger.debug("Fetched data: {}", fetchedData);
				dataCache.cacheData(config.getRedisKey(), fetchedData);
				logger.info("Data successfully fetched and saved to Redis with key: {}", config.getRedisKey());
			}
		} catch (Exception e) {
			logger.error("An error occurred during the scheduled job execution: {}", e.getMessage());
			System.exit(1);
		}
		logger.info("Decoder scrape job completed successfully at {} ...", java.time.LocalDateTime.now());
	}

}