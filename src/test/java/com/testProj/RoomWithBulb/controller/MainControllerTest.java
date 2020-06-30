package com.testProj.RoomWithBulb.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessageFromMainPage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Rooms with bulb application")));
    }

    @Test
    public void shouldReturnDenidedMessageFromRoomPage() throws Exception {
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/").requestAttr("RemoteAddr", "151.106.8.135");
        this.mockMvc.perform(get("/room?id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Access denided!")));
    }
}