package com.example.webservices.restfulwebservices.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people-versioning")
public class PersonVersioningController {
    // URI versioning

    @GetMapping("/v1")
    public PersonV1 getPersonUriV1() {
        return new PersonV1("John Doe");
    }

    @GetMapping("/v2")
    public PersonV2 getPersonUriV2() {
        return new PersonV2("John", "Doe");
    }

    // Request params versioning

    @GetMapping(path ="/params", params = "version=1")
    public PersonV1 getPersonRequestParamsV1() {
        return new PersonV1("John Doe");
    }

    @GetMapping(path ="/params", params = "version=2")
    public PersonV2 getPersonRequestParamsV2() {
        return new PersonV2("John", "Doe");
    }

    // Header versioning

    @GetMapping(path ="headers", headers = "X-API-VERSION=1")
    public PersonV1 getPersonHeaderV1() {
        return new PersonV1("John Doe");
    }

    @GetMapping(path ="headers", headers = "X-API-VERSION=2")
    public PersonV2 getPersonHeaderV2() {
        return new PersonV2("John", "Doe");
    }

    // Media type versioning

    @GetMapping(path ="accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonAcceptV1() {
        return new PersonV1("John Doe");
    }

    @GetMapping(path ="accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonAcceptV2() {
        return new PersonV2("John", "Doe");
    }

    @AllArgsConstructor
    @Data
    private class PersonV1 {
        private String name;
    }

    @AllArgsConstructor
    @Data
    private class PersonV2 {
        private String firstname;
        private String lastname;
    }
}
