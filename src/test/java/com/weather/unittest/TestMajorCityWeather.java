package com.weather.unittest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.weather.model.CityWeather;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestMajorCityWeather extends TestCase {	
	private CityWeather Sydney;
	private CityWeather Melbourne;
	
	protected void setUp() {
		// Initialize stuff before every test
		CityWeather.setOnlineWeatherAPI(onlineWeatherAPI);
		if (Sydney == null ) Sydney = CityWeather.newCityWeather("Sydney");
		if (Melbourne == null ) Melbourne = CityWeather.newCityWeather("Melbourne");
	}	
	
	/**
	 * Test the return json from online weather API contains Sydney.
	 */
	public void testJsonContainsSydney() {
		String json = CityWeather.readWeatherForCity("Sydney");
		int idx = json.indexOf("Sydney");
		Assert.assertFalse(idx == -1);
	}
	
	/**
	 * Test construction of Sydney CityWeather Object.
	 */
	public void testSydney() {
		Assert.assertNotNull(Sydney);
		Assert.assertNotNull(Sydney.getTemperature());
		Assert.assertNotNull(Sydney.getWeather() );
		Assert.assertNotNull(Sydney.getWind());
	}
	
	/**
	 * Test construction of Melbourne CityWeather Object.
	 */
	public void testMelbourne() {
		Assert.assertNotNull(Melbourne);
		Assert.assertNotNull(Melbourne.getTemperature());
		Assert.assertNotNull(Melbourne.getWeather() );
		Assert.assertNotNull(Melbourne.getWind());
	}
	
	
	public static String onlineWeatherAPI = "http://api.openweathermap.org/data/2.5/weather?APPID=f1ed86b3c2e7f5be4307343674225f32&amp;units=metric&amp;q=";
	public static String getOnlineWeatherAPI() {
		return onlineWeatherAPI;
	}
	static {
		loadProperties();
	}
	public static void loadProperties()  {
		Properties prop = new Properties();
		InputStream input = null;

		try {			
			prop.load(TestMajorCityWeather.class.getClassLoader().getResourceAsStream("testConfig.properties"));
			onlineWeatherAPI = prop.getProperty("onlineWeatherAPI");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
