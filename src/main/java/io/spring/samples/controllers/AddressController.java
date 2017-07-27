package io.spring.samples.controllers;

import io.spring.samples.domain.Address;
import io.spring.samples.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressRepository repository;

    @RequestMapping
    public Iterable<Address> list() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{email}")
    public Address find(@PathVariable String email) {
        System.out.println("Running search by email [" + email + "]");
        return repository.findByEmail(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus
    public ResponseEntity insert(@RequestBody Address address) throws UnsupportedEncodingException {
        Address result = repository.save(address);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{email}")
                .buildAndExpand(result.getEmail()).encode().toUri();

        return ResponseEntity.created(location).build();
    }
}