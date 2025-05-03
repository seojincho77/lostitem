package com.example.lostitem.services;

import com.example.lostitem.models.Locations;
import com.example.lostitem.repositories.LocationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationsService {
    private final LocationsRepository locationsRepository;

    public LocationsService(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    public List<Locations> getAllLocations() {
        return locationsRepository.findAll();
    }

    public List<Locations> searchByLocName(String keyword) {
        return locationsRepository.findByLocNameContaining(keyword);
    }

    public Locations saveLocation(Locations location) {
        return locationsRepository.save(location);
    }

    public void deleteLocation(Integer id) {
        locationsRepository.deleteById(id);
    }
}