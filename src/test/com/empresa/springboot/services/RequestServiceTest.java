package com.empresa.springboot.service;

import com.empresa.springboot.model.Request;
import com.empresa.springboot.repository.IRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RequestServiceTest {

    private IRequestRepository repository;
    private RequestService service;

    @BeforeEach
    void setUp() {
        repository = mock(IRequestRepository.class);
        service = new RequestService(repository);
    }

    @Test
    void testSaveHeadquarter_Success() {
        Request req = new Request();
        req.setHeadquarter("Yes");
        req.setName("CIAT");
        req.setCountry("Colombia");
        req.setCity("Cali");

        when(repository.findHeadquarter("CIAT")).thenReturn(null);
        when(repository.save(any(), anyString())).thenReturn("success");

        String result = service.saveRequest(req);
        assertEquals("success", result);
    }

    @Test
    void testSaveHeadquarter_Duplicate() {
        Request req = new Request();
        req.setHeadquarter("Yes");
        req.setName("CIAT");

        when(repository.findHeadquarter("CIAT")).thenReturn(new Request());

        String result = service.saveRequest(req);
        assertEquals("duplicate", result);
    }

    @Test
    void testSaveBranch_Success() {
        Request req = new Request();
        req.setHeadquarter("No");
        req.setName("CIAT");
        req.setCountry("Peru");
        req.setCity("Lima");

        when(repository.findByName("CIAT")).thenReturn(Collections.emptyList());
        when(repository.save(any(), anyString())).thenReturn("success");

        String result = service.saveRequest(req);
        assertEquals("success", result);
    }

    @Test
    void testSaveBranch_Duplicate() {
        Request req = new Request();
        req.setHeadquarter("No");
        req.setName("CIAT");
        req.setCountry("Colombia");
        req.setCity("Cali");

        Request existing = new Request();
        existing.setCountry("Colombia");
        existing.setCity("Cali");

        when(repository.findByName("CIAT")).thenReturn(Collections.singletonList(existing));

        String result = service.saveRequest(req);
        assertEquals("duplicate", result);
    }
}