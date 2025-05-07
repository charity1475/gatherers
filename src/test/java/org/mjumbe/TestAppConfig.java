package org.mjumbe;

import org.mjumbe.config.AppConfig;

public class TestAppConfig extends AppConfig {
	public TestAppConfig() {
		this.httpRequestUrl = "https://web1.azamtvltd.co.tz/api/method/azam_tv.azam_tv.api.get_show_by_date_and_country";
		this.decoderName = "AZAM TV";
		this.country = "TANZANIA";
	}
}
