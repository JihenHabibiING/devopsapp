package com.jihen.devopsapp.controller;

import com.jihen.devopsapp.model.Item;
import com.jihen.devopsapp.repo.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemRepository repo;

    public ItemController(ItemRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Item create(@RequestBody Item item) {
        item.setId(null);
        return repo.save(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
