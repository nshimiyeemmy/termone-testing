package rw.ac.rca.termOneExam.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.service.CityService;
import rw.ac.rca.termOneExam.utils.APICustomResponse;

import rw.ac.rca.termOneExam.dto.CreateCityDTO;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    // testing get all the cities
    @Test
    public void getAll_success() throws JSONException {
    String response = this.restTemplate.getForObject("/api/cities/all", String.class);
        System.out.println(response);
        JSONAssert.assertEquals("[{\"id\":101,\"name\":\"Kigali\",\"weather\":24.0,\"fahrenheit\":0.0},{\"id\":102,\"name\":\"Musanze\",\"weather\":18.0,\"fahrenheit\":0.0},{\"id\":103,\"name\":\"Rubavu\",\"weather\":20.0,\"fahrenheit\":0.0},{\"id\":104,\"name\":\"Nyagatare\",\"weather\":28.0,\"fahrenheit\":0.0}]", response, true);
    }

    // testing get all the cities failed
    @Test
    public void getAll_failed() throws JSONException {

    }

    // test for getting city by id success
    @Test
    public void getById_success() throws JSONException {
        ResponseEntity<City> response = this.restTemplate.getForEntity("/api/cities/101", City.class);

        System.out.println(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(24, response.getBody().getWeather());
        assertEquals("Kigali", response.getBody().getName());
    }

    //testing get city by Id failed
    @Test
    public void getById_404() {
        ResponseEntity<APICustomResponse> response = this.restTemplate.getForEntity("/api/cities/1000", APICustomResponse.class);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Item not found", response.getBody().getMessage());
    }

   //testing create city success
   @Test
   public void createTest_Success() {
       CreateCityDTO dto = new CreateCityDTO();
       dto.setName("Nyamirambo");
       dto.setWeather(40);

       ResponseEntity<City> response = restTemplate.postForEntity("/api/cities/add", dto, City.class);

       assertEquals(HttpStatus.CREATED, response.getStatusCode());
       assertEquals("Nyamirambo", response.getBody().getName());
   }









}
