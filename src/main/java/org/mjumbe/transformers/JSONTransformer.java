package org.mjumbe.transformers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.IntStream;

public class JSONTransformer {
	private static final Logger logger = LoggerFactory.getLogger(JSONTransformer.class);

	public String transform(String jsonInput) {
		JSONObject data = new JSONObject(jsonInput);
		JSONArray programsDetails = data.getJSONObject("message").getJSONArray("program_details");
		JSONArray channelsDetails = data.getJSONObject("message").getJSONArray("channels");

		JSONArray programs = new JSONArray();
		programsDetails.forEach(programDetail -> {
			JSONObject programObj = (JSONObject) programDetail;
			String channelUuid = programObj.getString("channelUuid");
			JSONObject channelObj = getChannel(channelUuid, channelsDetails);

			if (channelObj != null) {
				JSONObject program = new JSONObject();
				program.put("id", programObj.get("id"));
				program.put("date", programObj.get("date"));
				program.put("startTime", programObj.get("since"));
				program.put("endTime", programObj.get("till"));
				program.put("title", programObj.get("title"));
				program.put("channelUUId", channelUuid);
				program.put("channelName", programObj.get("channel_name"));
				program.put("category", channelObj.get("category"));
				program.put("country", channelObj.get("country"));
				program.put("provider", channelObj.get("provider"));
				program.put("type", channelObj.get("type"));
				programs.put(program);
			}
		});

		if (programs.isEmpty()) {
			logger.error("No programs found in the response.");
			throw new RuntimeException("No programs found in the response.");
		}
		return programs.toString(4);
	}

	public JSONObject getChannel(String channelUuid, JSONArray channelsDetails) {
		return IntStream.range(0, channelsDetails.length())
				.mapToObj(channelsDetails::getJSONObject)
				.filter(channel -> channel.getString("uuid").equals(channelUuid))
				.findFirst()
				.orElse(null);
	}


	public String parseData(String data) {
		logger.info("Starting data transformation...");
		try {
			logger.info("Ending JSON Transformation ...");
			return transform(data);
		} catch (Exception e) {
			logger.error("An unexpected error occurred during transformation: {}", e.getMessage());
			throw new RuntimeException("Failed to transform response with error: " + e.getMessage());
		}
	}

}
