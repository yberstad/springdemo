package com.driw.web.order;

import com.driw.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

// SpringRunner is an alias for the SpringJUnit4ClassRunner.(requires JUnit 4.12 or higher)
// @SpringBootTest is a simplification of @SpringApplicationConfiguration, @ContextConfiguration, @IntegrationTest etc..

// Two configurations:
// 1. WebEnvironment.MOCK and @WebAppConfiguration class annotation + WebApplicationContext
// 2. WebEnvironment.RANDOM_PORT, no @WebAppConfiguration and EmbeddedWebApplicationContext


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
public class OrderRestControllerIntegrationTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters).filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setUp(){
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createOrder_WithExistingAccount_ShouldReturnStatusCreated() throws Exception{
        mockMvc.perform(post("/api/account_with_orders/orders"))
                .andExpect(status().isCreated());
    }

    @Test
    public void createOrder_WithExistingAccount_ShouldReturnStatusNotFound() throws Exception{
        mockMvc.perform(post("/api/account_not/orders"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addOrderItem_WithValidRequestBodyGiven_ShouldReturnStatusCreated() throws Exception{
        OrderItemRequestModel requestBody = new OrderItemRequestModel();
        requestBody.orderId = 1L;
        requestBody.productId = 4L;
        requestBody.count = 20;

        String content = this.json(requestBody);
        mockMvc.perform(post("/api/account_with_orders/orders/additem")
                .contentType(contentType)
                .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    public void addOrderItem_WithValidRequestBodyGiven_ShouldReturnStatusNotFound() throws Exception{
        OrderItemRequestModel requestBody = new OrderItemRequestModel();
        requestBody.orderId = 10L;
        requestBody.productId = 4L;
        requestBody.count = 20;

        String content = this.json(requestBody);
        mockMvc.perform(post("/api/account_with_orders/orders/additem")
                .contentType(contentType)
                .content(content))
                .andExpect(status().isNotFound());
    }


    private String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
