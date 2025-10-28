package com.lru;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cache")
public class CacheController {
    private final LRUCache cache;

    @Autowired
    public CacheController(LRUCache cache){
        this.cache = cache;
    }

    @PostMapping
    public ResponseEntity<String> put(@RequestBody CacheRequest body) {
        int key = body.getKey();
        int value = body.getValue();
        cache.put(key, value);
        return ResponseEntity.ok("Value added/updated");
    }

    @GetMapping("/{key}")
    public ResponseEntity<Integer> get(@PathVariable int key) {
        int value = cache.get(key);
        if (value == -1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(value); 
    }
}
