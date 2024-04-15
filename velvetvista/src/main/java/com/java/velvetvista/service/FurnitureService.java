package com.java.velvetvista.service;

import com.java.velvetvista.model.Furniture;
import com.java.velvetvista.repository.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public List<Furniture> getAllFurniture() {
        return furnitureRepository.findAll();
    }

    public Furniture getFurnitureById(int id) {
        Optional<Furniture> optionalFurniture = furnitureRepository.findById(id);
        return optionalFurniture.orElse(null);
    }

    public Furniture createFurniture(Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    public Furniture updateFurniture(int id, Furniture furniture) {
        furniture.setId(id);
        return furnitureRepository.save(furniture);
    }

    public void deleteFurniture(int id) {
        furnitureRepository.deleteById(id);
    }
}
