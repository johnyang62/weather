package com.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.weather.model.CityWeather;

//
/**
 * Main Spring MVC Controller for the Weather Web application.
 * Use the following URL http://localhost:8080/weather/ to access this.
 */
@Controller
public class CityWeatherController {
	/**
	 * Autowired/Inject a CityWeather instance created by configuration.
	 */
	@Autowired
	private CityWeather cityWeather;

	/**
	 * Handle URL like http://localhost:8080/weather/Sydney
	 * Create newCityWeather bean.
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getCityWeather(@PathVariable String name, ModelMap model) {
		model.addAttribute("command", CityWeather.newCityWeather(name));
		return "cityWeatherList";
	}

	/**
	 * Handle URL http://localhost:8080/weather/, no cityName specify.
	 * Use the default injected cityWeather bean.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getDefaultCityWeather(ModelMap model) {
		model.addAttribute("command", cityWeather);

		return "cityWeatherList";
	}
}