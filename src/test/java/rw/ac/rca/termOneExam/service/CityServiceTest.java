package rw.ac.rca.termOneExam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @Mock
    private ICityRepository iCityRepository;

    @InjectMocks
    private CityService cityService;


    @Test
    public void getAllTest_Success() {
        when(iCityRepository.findAll()).thenReturn(Arrays.asList(new City("Nyamagabe", 31), new City("Kibungo", 52)));

        assertEquals(2, cityService.getAll().size());
        assertEquals("Nyamagabe", cityService.getAll().get(0).getName());
    }

    @Test
    public void getByIdTest_Success() {
        when(iCityRepository.findById(anyLong())).thenReturn(Optional.of(new City("Kayonza", 10)));

        assertTrue(cityService.getById(1L).isPresent());
        assertEquals("Kayonza", cityService.getById(1L).get().getName());
        assertEquals(10, cityService.getById(1L).get().getWeather());
    }

    @Test
    public void getByIdTest_NotFound() {
        when(iCityRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertFalse(cityService.getById(1L).isPresent());
    }


    @Test
    public void createTest_Success() {
        when(iCityRepository.save(any(City.class))).thenReturn(new City("Kagitumba", 3));

        CreateCityDTO dto = new CreateCityDTO();
        dto.setName("Nyamirambo");
        dto.setWeather(5);

        assertEquals("Kagitumba", cityService.save(dto).getName());
        assertEquals(3, cityService.save(dto).getWeather());
    }

    @Test
    public void existsByName_Test() {
        when(iCityRepository.existsByName(anyString())).thenReturn(true);

        assertTrue(cityService.existsByName("Nyamagabe"));
    }
    @Test
    public void converterTest_Any(){
        assertEquals(93.2, cityService.converter(34));
    }

}