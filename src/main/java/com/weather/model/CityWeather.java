package com.weather.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class CityWeather {
	static SimpleDateFormat sdf = new SimpleDateFormat("EEEE hh:mm a");
	static String NOT_AVAILABLE = "Temporarily Unavailable";
	
	CityWeather(String cityName) {
		this.cityName = cityName;
	}
	
	CityWeather() {
		
	}
	
	/**
	 * Factory to construct a CityWeather bean.
	 */
	public static CityWeather newCityWeather(String cityName) {
		CityWeather city = new CityWeather(cityName);
        city.initialize();
        return city;
    }
	
	
	/**
	 * Read json String from online weather API for the current cityName and
	 * construct the Bean.
	 */
    protected void initialize() {
    	setUpdatedTime(sdf.format(new Date()));

    	String jsonStr = readWeatherForCity(getCityName());
		
		if (jsonStr != "") {
			try {			
				JSONObject jsonObject = new JSONObject(jsonStr);
				setWeather((String) ((JSONObject)jsonObject.getJSONArray("weather").get(0)).get("main"));
			
				setTemperature((int)Math.floor( jsonObject.getJSONObject("main").getDouble("temp")) + "\u00b0C");
				
				setWind((int)Math.round(jsonObject.getJSONObject("wind").getDouble("speed")*60*60/1000)+"km/h");		
			} catch (JSONException e) {
				System.err.println(e);
			}
		} else {
			setWeather(NOT_AVAILABLE);
			setTemperature(NOT_AVAILABLE);
			setWind(NOT_AVAILABLE);		
		}
    }

	/**
	 * Read json String from online weather API for the current cityName.
	 */
	public static String readWeatherForCity(String cityName) {
		String url = onlineWeatherAPI + cityName + ",au";
		return readUrl(url);
	}

	/**
	 * Read content from URL and return as String.
	 */
	public static String readUrl(String url) {
		String jsonStr = "";		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = reader.read()) != -1) {
		      sb.append((char) cp);
		    }
		    jsonStr = sb.toString();
		} catch (MalformedURLException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println(e);
				}
		}
		return jsonStr; 
	}

	
	
	
    public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String[] getCityList() {
		return cityList;
	}
	public void setCityList(String[] a_cityList) {
		cityList = a_cityList;
	}
	
	
	public String getOnlineWeatherAPI() {
		return onlineWeatherAPI;
	}
	public static void setOnlineWeatherAPI(String a_onlineWeatherAPI) {
		onlineWeatherAPI = a_onlineWeatherAPI;
	}


	private static String[] cityList;
	private static String onlineWeatherAPI;
	
	private String cityName;
    private String updatedTime;
    private String weather;
    private String temperature;
    private String wind;
}