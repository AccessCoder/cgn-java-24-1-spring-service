package de.neuefische.cgnjava241springservice.service;

import java.util.UUID;

public class IdService {

    public String generateId(){
        return UUID.randomUUID().toString();
    }
}
