package com.empresa.springboot.controller;

import com.empresa.springboot.model.Request;
import com.empresa.springboot.repository.IRequestRepository;
import com.empresa.springboot.service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RequestController.class)
class RequestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestService requestService;

    @Test
    void testSaveRequest_Success() throws Exception {
        Request req = new Request();
        req.setHeadquarter("Yes");
        req.setName("CIAT");
        req.setCountry("Colombia");
        req.setCity("Cali");

        when(requestService.saveRequest(any(Request.class))).thenReturn("success");

        mockMvc.perform(post("/api/requests/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"headquarter\":\"Yes\",\"name\":\"CIAT\",\"country\":\"Colombia\",\"city\":\"Cali\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Request saved successfully."));
    }

    @Test
    void testSaveRequest_Duplicate() throws Exception {
        when(requestService.saveRequest(any(Request.class))).thenReturn("duplicate");

        mockMvc.perform(post("/api/requests/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"headquarter\":\"Yes\",\"name\":\"CIAT\",\"country\":\"Colombia\",\"city\":\"Cali\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Duplicate institution or branch."));
    }

    @Test
    void testGetAllRequests() throws Exception {
        List<Request> requests = new ArrayList<>();
        Request req = new Request();
        req.setName("CIAT");
        requests.add(req);

        when(requestService.getAllRequests()).thenReturn(requests);

        mockMvc.perform(get("/api/requests/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("CIAT"));
    }
}