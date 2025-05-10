package org.mjumbe;

import org.mjumbe.config.AppConfig;
import org.mjumbe.http.DataFetcher;
import org.mjumbe.redis.DataCache;
import org.mjumbe.transformers.JSONTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			AppConfig config = new AppConfig();
			logger.info("AppConfig loaded : {}", config);
			DataFetcher dataFetcher = new DataFetcher();
			JSONTransformer jsonTransformer = new JSONTransformer();

			try (DataCache dataCache = new DataCache(config.redisHost, config.redisPort, config.redisPassword)) {
				String fetchedData = dataFetcher.fetchData(config);
				logger.debug("Fetched data: {}", fetchedData);
				String transformedData = jsonTransformer.parseData(fetchedData);
				dataCache.cacheData(config.redisKey, transformedData);
				logger.info("Data successfully fetched and saved to Redis with key: {}", config.redisKey);
			}
		} catch (Exception e) {
			logger.error("An error occurred during the scheduled job execution: {}", e.getMessage());
			System.exit(1);
		}
		logger.info("Decoder scrape job completed successfully at {} ...", LocalDateTime.now());
	}

}