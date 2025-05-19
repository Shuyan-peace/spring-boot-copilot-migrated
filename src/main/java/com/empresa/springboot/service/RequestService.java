package com.empresa.springboot.service;

import com.empresa.springboot.model.Request;
import com.empresa.springboot.repository.IRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class RequestService {

    private final IRequestRepository requestRepository;

    @Autowired
    public RequestService(IRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    /**
     * Save a new institution or branch, with duplicate checks.
     * @param request The request to save.
     * @return "success" if saved, "duplicate" if duplicate found, "error" otherwise.
     */
    public String saveRequest(Request request) {
        String key = getRequestKey(request);

        if ("Yes".equals(request.getHeadquarter())) {
            // Check for duplicate headquarter
            Request existing = requestRepository.findHeadquarter(request.getName());
            if (existing != null) {
                return "duplicate";
            }
            return requestRepository.save(request, key);
        } else {
            // Check for duplicate branch (same name, country, city)
            List<Request> sameName = requestRepository.findByName(request.getName());
            for (Request r : sameName) {
                if (r.getCountry().equals(request.getCountry()) && r.getCity().equals(request.getCity())) {
                    return "duplicate";
                }
            }
            return requestRepository.save(request, key);
        }
    }

    /**
     * Generate a unique key for the request (name-country-city).
     */
    public String getRequestKey(Request request) {
        return request.getName() + "-" + request.getCountry() + "-" + request.getCity();
    }

    // Additional methods for listing, deleting, etc. can be added here
    // ...existing code...

    /**
     * Get all requests as a list.
     */
    public List<Request> getAllRequests() {
        return requestRepository.findAllAsList();
    }

    /**
     * Get a map of country names for all requests.
     * Key and value are both country names (for dropdowns).
     */
    public java.util.Map<String, String> getCountryMap() {
        Locale locale= Locale.getDefault();
        String[] locales = Locale.getISOCountries();
		Map<String, String> countries = new HashMap<String, String>();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			// Store the code and name of country into the countries map
			countries.put(obj.getCountry(), obj.getDisplayCountry(locale));
		}
		return countries;
    }

    /**
     * Delete a request by its unique key (name-country-city).
     * @param request The request to delete.
     * @return true if deleted, false if not found.
     */
    public boolean deleteRequest(Request request) {
        String key = getRequestKey(request);
        String result= requestRepository.delete(key);
        return "success".equals(result);
    }

} 
