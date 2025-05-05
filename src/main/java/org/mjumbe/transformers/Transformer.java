package org.mjumbe.transformers;

import org.json.JSONObject;
import org.json.XML;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class Transformer {
	public void transform() {
		StreamSource streamSource = new StreamSource(new File("src/main/resources/transformer.xsl"));
	}

	/**
	 * @param jsonInput JSON string to be transformed
	 * @return XML string
	 * @throws Exception if transformation fails
	 *
	 */
	public String transformJsonToXml(String jsonInput) throws Exception {
		JSONObject jsonObject = new JSONObject(jsonInput);
		return XML.toString(jsonObject);
	}
	/**
	 * @param xmlInput XML string to be transformed
	 * @return JSON string
	 * @throws Exception if transformation fails
	 *
	 */
	public String transformXmlToJson(String xmlInput) throws Exception {
		JSONObject jsonObject = XML.toJSONObject(xmlInput);
		return jsonObject.toString();
	}

	public String parseData(String data) {
		try {
			String xml = transformXmlToJson(data);
			//TODO: call xml to xsl transformation
			//TODO: xml to json back
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
