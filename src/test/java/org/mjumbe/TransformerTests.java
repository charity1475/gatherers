package org.mjumbe;

import org.junit.jupiter.api.Test;
import org.mjumbe.http.DataFetcher;
import org.mjumbe.transformers.JSONTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformerTests {
	private static final Logger logger = LoggerFactory.getLogger(TransformerTests.class);

	@Test
	public void testTransform() {
		TestAppConfig config = new TestAppConfig();
		logger.info("TestAppConfig loaded : {}", config);

		DataFetcher dataFetcher = new DataFetcher();
		String input = dataFetcher.fetchData(config);
		JSONTransformer transformer = new JSONTransformer();
		String result = transformer.parseData(input);

		logger.info("Transformed Output: {}", result);
		assertTrue(result != null);
	}

}
