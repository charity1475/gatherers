package org.mjumbe.transformers;

import org.json.JSONObject;
import org.json.XML;
import org.mjumbe.redis.DataCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class Transformer {
	private static final Logger logger = LoggerFactory.getLogger(Transformer.class);

	public void transform() {
		StreamSource streamSource = new StreamSource(new File("src/main/resources/transformer.xsl"));
	}

	/**
	 * @param jsonInput JSON string to be transformed
	 * @return XML string
	 *
	 */
	public String transformJsonToXml(String jsonInput) {
		JSONObject jsonObject = new JSONObject(jsonInput);
		return XML.toString(jsonObject);
	}

	/**
	 * @param xmlInput XML string to be transformed
	 * @return JSON string
	 *
	 */
	public String transformXmlToJson(String xmlInput) {
		JSONObject jsonObject = XML.toJSONObject(xmlInput);
		return jsonObject.toString();
	}

	/**
	 * @param data JSON string to be transformed
	 * @return JSON string
	 *
	 */
	public String parseData(String data) {
		logger.info("Starting data transformation...");
		try {
			String xml = transformXmlToJson(data);
			//TODO: call xml to xsl transformation
			//TODO: xml to json back
			return "{}";
		} catch (Exception e) {
			logger.error("An unexpected error occurred during transformation: {}", data, e);
			throw new RuntimeException("Failed to transform response with error: " + e.getMessage());
		}
	}
}
