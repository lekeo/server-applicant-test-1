package com.mytaxi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mytaxi.config.ApplicationSecurityConfig;
import com.mytaxi.controller.CarController;
import com.mytaxi.controller.DriverController;
import com.mytaxi.controller.HomeController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MytaxiServerApplicantTestApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(ApplicationSecurityConfig.class)
public class MytaxiServerApplicantTestApplicationTests
{
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private HomeController homeController;

    @Autowired
    private CarController carController;
    
    @Autowired
    private DriverController driverController;
    
    @Test
    public void contextLoads()
    {
        assertThat(homeController).isNotNull();
        assertThat(carController).isNotNull();
        assertThat(driverController).isNotNull();
    }
    

    @Test
    public void homeShouldReturnDefaultPage() throws Exception
    {
        assertThat(restTemplate.withBasicAuth("mytaxiAdmin", "admin123").getForObject("http://localhost:" + port + "/", String.class))
            .contains("Swagger UI");
    }
    
    

}
