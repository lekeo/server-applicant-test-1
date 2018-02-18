package com.mytaxi;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mytaxi.controller.CarController;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.service.driver.CarService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CarController.class, secure = false)
public class CarControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;


    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters)
    {

        this.mappingJackson2HttpMessageConverter =
            Arrays
                .asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull(
            "the JSON message converter must not be null",
            this.mappingJackson2HttpMessageConverter);
    }

    private List<CarDO> carList;

    private CarDO mockCar;


    @Before
    public void setup()
    {
        this.carList =
            Arrays.asList(
                new CarDO("Corsa", "34AB1123", EngineType.DIESEL, "Opel", 4, false),
                new CarDO("Polo", "34CD2223", EngineType.GAS, "VW", 4, true));

        this.carList.get(0).setId(Long.valueOf(1));
        this.carList.get(1).setId(Long.valueOf(2));

        this.mockCar = new CarDO("Passat 2.0TDI", "06ABC001", EngineType.DIESEL, "Volkswagen", 4, false);
        this.mockCar.setId(Long.valueOf(999));
    }


    @Test
    public void getAllCarsShouldReturnProperCarList() throws Exception
    {
        given(this.carService.findAll()).willReturn(carList);

        this.mockMvc
            .perform(get("/v1/cars")).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].model", is("Corsa")))
            .andExpect(jsonPath("$[0].licensePlate", is("34AB1123")))
            .andExpect(jsonPath("$[1].model", is("Polo")))
            .andExpect(jsonPath("$[1].licensePlate", is("34CD2223")));
    }


    @Test
    public void getSingleCarSuccess() throws Exception
    {
        given(this.carService.find(this.carList.get(0).getId())).willReturn(this.carList.get(0));

        this.mockMvc
            .perform(get("/v1/cars/" + this.carList.get(0).getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id", is(this.carList.get(0).getId().intValue())))
            .andExpect(jsonPath("$.model", is("Corsa")))
            .andExpect(jsonPath("$.licensePlate", is("34AB1123")));
    }


    @Test
    public void createCarSuccessMessageReturn() throws Exception
    {
        given(this.carService.create(Mockito.anyObject())).willReturn(mockCar);

        String carJson = toJson(this.mockCar);

        this.mockMvc
            .perform(
                post("/v1/cars")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(carJson))
            .andExpect(status().isCreated());

    }
    

    @Test
    //Not working
    public void rateCarUpdatesCarRating() throws Exception
    {
        Integer rating = 5;

        this.mockMvc.perform(put("v1/cars/" + this.mockCar.getId()).param("rating", String.valueOf(rating))).andExpect(status().isOk());

        verify(this.carService, times(1)).rateCar(this.mockCar.getId(), rating);
    }


    protected String toJson(CarDO carDO) throws Exception, IOException
    {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();

        this.mappingJackson2HttpMessageConverter.write(
            carDO, MediaType.APPLICATION_JSON_UTF8, mockHttpOutputMessage);

        return mockHttpOutputMessage.getBodyAsString();
    }
    
}
