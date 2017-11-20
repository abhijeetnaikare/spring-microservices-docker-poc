package com.cailam.springmicroservicesdockerpoc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="inquiries")
public class ApiController {

    private static final List<String> INQUIRIES = new ArrayList<>();

    @RequestMapping(path = "", method = POST)
    public ResponseEntity<?> writeDownInquiries(final @RequestBody Map<String, Object> input) {
        if (input.containsKey("name") && input.get("name") != null) {
            INQUIRIES.add(input.get("name").toString());
            return ok(INQUIRIES);
        }
        else
            return badRequest().body("'name' is required.");
    }
}
