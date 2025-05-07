package org.mjumbe.transformers;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import net.sf.saxon.TransformerFactoryImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.w3c.dom.Document;
//import org.xml.sax.SAXException;
//import net.sf.saxon.s9api.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class XSLTTransformer {
	private static final Logger logger = LoggerFactory.getLogger(XSLTTransformer.class);

	public String transform(String jsonInput) {

		// START
		logger.info("Starting JSON Transforming with");
		JSONObject jsonObject = new JSONObject(jsonInput);
		logger.info("JSON Object: {}", jsonObject);
		jsonObject.getJSONObject("message").getJSONArray("program_details").iterator().forEachRemaining(programDetails -> {
			
		});

		// END

		return "";
	}

	/**
	 * @param jsonInput JSON string to be transformed
	 * @return XML string
	 */
//	public String transformJsonToXml(String jsonInput) throws JsonProcessingException {
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
//		JsonNode jsonNode = objectMapper.readTree(jsonInput);
//		return new XmlMapper().writeValueAsString(jsonNode);
//	}

	/**
	 * @param xmlInput XML string to be transformed
	 * @return JSON string
	 */
//	public String transformXmlToJson(String xmlInput) throws IOException {
//		XmlMapper xmlMapper = new XmlMapper();
//		JsonNode jsonNode = xmlMapper.readTree(xmlInput.getBytes());
//		ObjectMapper jsonMapper = new ObjectMapper();
//		return jsonMapper.writeValueAsString(jsonNode);
//	}

	/**
	 * @param data JSON string to be transformed
	 * @return JSON string
	 */
	public String parseData(String data) {
		logger.info("Starting data transformation...");
		try {
//			String xml = transformXmlToJson(data);
//			String transformedXml = transform(xml);
//			return transformXmlToJson(transformedXml);
		} catch (Exception e) {
			logger.error("An unexpected error occurred during transformation: {}", e.getMessage());
			throw new RuntimeException("Failed to transform response with error: " + e.getMessage());
		}
	}
}
