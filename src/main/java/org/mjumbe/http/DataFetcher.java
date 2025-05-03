package org.mjumbe.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.mjumbe.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataFetcher {
	public static final Logger LOGGER = LoggerFactory.getLogger(DataFetcher.class);

	public String fetchData(AppConfig config) {
		LOGGER.info("Fetching data from URL: {}", config.getHttpRequestUrl());
		String date = java.time.LocalDate.now().toString();
		LOGGER.info("Today's date: {}", date);
		try (HttpClient client = HttpClient.newHttpClient()) {
			JSONObject requestBody = new JSONObject();
			requestBody.put("date", date);
			requestBody.put("category", "All");
			requestBody.put("country", config.getCountry());
			requestBody.put("page_no", 1);
			requestBody.put("page_size", config.getPageSize());

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(config.getHttpRequestUrl()))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(requestBody.toString(), StandardCharsets.UTF_8))
					.build();

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
