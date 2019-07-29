package com.example.tvordering;

import com.example.tvordering.model.Channel;
import com.example.tvordering.model.Customer;
import com.example.tvordering.repository.CustomerChannelRepository;
import com.example.tvordering.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TvorderingApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerChannelRepository customerChannelRepository;

    @Before
    public void setup() {
        Customer customer1 = new Customer(
                1L,
                "Ayrton Senna",
                "Avenida Brasil, 1",
                "ayrton.senna@xyz.com",
                "+55 5555-1212");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer1));

        Customer customer2 = new Customer(
                2L,
                "Alain Prost",
                "Avenida Paris, 2",
                "alain.prost@xyz.com",
                "+33 5555-9999");
        when(customerRepository.findById(2L)).thenReturn(Optional.of(customer2));

        List<Channel> subscribedChannels = new ArrayList<>();
        subscribedChannels.add(new Channel(1L, 100, "Sports"));
        subscribedChannels.add(new Channel(2L, 101, "Movies"));

        List<Channel> notSubscribedChannels = new ArrayList<>();
        notSubscribedChannels.add(new Channel(3L, 500, "Sports HD"));
        notSubscribedChannels.add(new Channel(4L, 501, "Movies HD"));

        when(customerChannelRepository.findUserChannelsBySubscribedStatus(1L, true))
                .thenReturn(subscribedChannels);
        when(customerChannelRepository.findUserChannelsBySubscribedStatus(2L, false))
                .thenReturn(notSubscribedChannels);

    }

    @Test
    public void shouldFindCustomer() throws Exception {
        mockMvc.perform(get("/customer/1"))
                //.andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Ayrton Senna")));

        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldNotFindCustomerThatDoesNotExist() throws Exception {
        mockMvc.perform(get("/customer/5")).andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetSubscribedChannels() throws Exception {
        mockMvc.perform(get("/customer/1/channel?subscribed=true"))
                //.andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].number", is(100)))
                .andExpect(jsonPath("$[0].name", is("Sports")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].number", is(101)))
                .andExpect(jsonPath("$[1].name", is("Movies")))
                .andExpect(status().isOk());

        verify(customerChannelRepository, times(1)).findUserChannelsBySubscribedStatus(1L, true);
    }

    @Test
    public void shouldGetUnsubscribedChannels() throws Exception {
        mockMvc.perform(get("/customer/2/channel?subscribed=false"))
                //.andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].number", is(500)))
                .andExpect(jsonPath("$[0].name", is("Sports HD")))
                .andExpect(jsonPath("$[1].id", is(4)))
                .andExpect(jsonPath("$[1].number", is(501)))
                .andExpect(jsonPath("$[1].name", is("Movies HD")))
                .andExpect(status().isOk());

        verify(customerChannelRepository, times(1)).findUserChannelsBySubscribedStatus(2L, false);
    }

    @Test
    public void shouldNotFindChannelsForUnexistingCustomer() throws Exception {
        mockMvc.perform(get("/customer/5/channel?subscribed=true")).andExpect(status().isNotFound());
    }


}
