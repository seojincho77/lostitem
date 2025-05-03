package com.example.lostitem.services;

import com.example.lostitem.models.LostItem;
import com.example.lostitem.repositories.LostItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LostItemService {
    private final LostItemRepository lostItemRepository;

    public LostItemService(LostItemRepository lostItemRepository) {
        this.lostItemRepository = lostItemRepository;
    }

    public List<LostItem> getAllLostItems() {
        return lostItemRepository.findAll();
    }

    public Optional<LostItem> getLostItemById(Integer id) {
        return lostItemRepository.findById(id);
    }

    public LostItem saveLostItem(LostItem lostItem) {
        return lostItemRepository.save(lostItem);
    }

    public void deleteLostItem(Integer id) {
        lostItemRepository.deleteById(id);
    }
}