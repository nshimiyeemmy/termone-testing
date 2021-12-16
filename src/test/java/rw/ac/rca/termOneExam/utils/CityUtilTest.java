package rw.ac.rca.termOneExam.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;
import rw.ac.rca.termOneExam.service.CityService;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CityUtilTest {

    @Autowired
    private ICityRepository cityRepository;

    //no city has weather more than 40
    //degree Celsius.
    @Test
    public void noCityWeatherGreaterThan40Testing() {

        assertEquals(0, cityRepository.weatherGreaterThan(40));
    }

    //no city has weather LESS than 10
    //degree Celsius
    @Test
    public void noCityWeatherLessThan10Testing() {

        assertEquals(0, cityRepository.weatherLessThan(10));
    }

    @Test
    public void ContainsMusanzeAndKigaliTesting() {
        assertTrue(cityRepository.existsByName("Musanze"));
        assertTrue(cityRepository.existsByName("Kigali"));
    }
}