package com.empresa.springboot.controller;

import com.empresa.springboot.model.Request;
import com.empresa.springboot.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    // Equivalent to save request (RequestAction)
    @PostMapping("/save")
    public ResponseEntity<String> saveRequest(@RequestBody Request request) {
        String result = requestService.saveRequest(request);
        if ("success".equals(result)) {
            return ResponseEntity.ok("Request saved successfully.");
        } else if ("duplicate".equals(result)) {
            return ResponseEntity.badRequest().body("Duplicate institution or branch.");
        } else {
            return ResponseEntity.status(500).body("Error saving request.");
        }
    }

    // Equivalent to ListRequestAction
    @GetMapping("/all")
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    // Equivalent to ListCountryAction
    @GetMapping("/countries")
    public ResponseEntity<Map<String, String>> getCountries() {
        Map<String, String> countries = requestService.getCountryMap();
        return ResponseEntity.ok(countries);
    }
}