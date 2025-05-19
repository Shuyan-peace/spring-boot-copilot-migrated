package com.empresa.springboot.repository;

import com.empresa.springboot.model.Request;
import java.util.List;
import java.util.Map;

public interface IRequestRepository {
    Map<String, Request> findAll();
    List<Request> findAllAsList();
    List<Request> findByName(String name);
    Request findHeadquarter(String name);
    Request findByKey(String key);
    String save(Request request, String key);
    String delete(String key);
}