package com.mytaxi;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mytaxi.controller.DriverController;
import com.mytaxi.service.driver.DriverService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DriverController.class, secure = false)
public class DriverControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverService driverService;

    
}
