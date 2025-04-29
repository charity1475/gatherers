package org.mjumbe.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataFetcher {
	public static final Logger LOGGER = LoggerFactory.getLogger(DataFetcher.class);

	public String fetchData(String url) {
		LOGGER.info("Fetching data from URL: {}", url);
		try (HttpClient client = HttpClient.newHttpClient()) {
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
					.GET().build();
			try {
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				if (response.statusCode() != 200) {
					LOGGER.error("Failed to fetch data: {}", response.statusCode());
					throw new IOException("Azam TV decoder end point failed: " + response.statusCode());
				}
				return response.body();
			} catch (IOException | InterruptedException e) {
				LOGGER.error("Error while fetching data: {}", e.getMessage());
				throw new RuntimeException(e);
			}

		} catch (Exception e) {
			LOGGER.error("Exception: {}", e.getMessage());
			throw new RuntimeException("Failed to fetch data: " + e.getMessage());
		}
	}
}
