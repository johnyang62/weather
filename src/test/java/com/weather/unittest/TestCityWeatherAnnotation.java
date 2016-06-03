package com.weather.unittest;

import org.junit.Before;
import org.junit.Test;

import com.weather.model.CityWeather;

import junit.framework.Assert;

/**
 * Use the junit annotation framework to test the web application HTML pages.
 * weather web application must be deployed and running before run this test,
 * otherwise the test will fail.
 *
 */
public class TestCityWeatherAnnotation {	
	@Before
	public void setUp() {
		// Initialize stuff before every test
	}
	/**
	 * Test default generated HTML page.
	 */
	@Test
	public void testDefaultWeather() {		
		String html = CityWeather.readUrl("http://localhost:8180/weather/");
		int idx = html.indexOf("<select id=");
		Assert.assertFalse(idx == -1);
	}

	/**
	 * Test generated Sydney weather HTML page.
	 */
	@Test
	public void testSydneyWeather() {		
		String html = CityWeather.readUrl("http://localhost:8180/weather/Sydney");
		System.out.println(html);
		int idx = html.indexOf("<option value=\"Sydney\" selected=\"selected\">Sydney");
		Assert.assertFalse(idx == -1);
	}
}
