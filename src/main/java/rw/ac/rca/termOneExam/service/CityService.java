package rw.ac.rca.termOneExam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

@Service
public class CityService {

	@Autowired
	private ICityRepository cityRepository;

	public Optional<City> getById(long id) {

		Optional<City> city = cityRepository.findById(id);

		// add a line to convert to set the degrees to farneheit
		city.ifPresent(value -> value.setFahrenheit(Temp_Converter(value.getWeather())));

		return city;
	}

	public List<City> getAll() {

		List<City> cities = cityRepository.findAll();

		// loop to convert each celius to Fahrenheit
		for (City city : cities)
			city.setFahrenheit(Temp_Converter(city.getWeather()));

		return cities;
	}

	public boolean existsByName(String name) {

		return cityRepository.existsByName(name);
	}

	public City save(CreateCityDTO dto) {
		City city = new City(dto.getName(), dto.getWeather());
		city.setFahrenheit(Temp_Converter(dto.getWeather()));
		return cityRepository.save(city);
	}


	public double Temp_Converter(double celius) {
		return (celius * 9 / 5) + 32;
	}
}