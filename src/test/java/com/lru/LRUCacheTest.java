package com.lru;

// Importaciones de JUnit 5
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    @Test
    void testCachePutAndGet() {
        LRUCache cache = new LRUCache(2);
        
        cache.put(1, 10);
        assertEquals(10, cache.get(1));
        
        cache.put(2, 20);
        assertEquals(10, cache.get(1));
        assertEquals(20, cache.get(2));
    }

    @Test
    void testCacheEviction() {
        LRUCache cache = new LRUCache(2);
        
        cache.put(1, 10);
        cache.put(2, 20);
       
        
        cache.put(3, 30);
        
        assertEquals(-1, cache.get(1));
        assertEquals(20, cache.get(2));
        assertEquals(30, cache.get(3));
    }

    @Test
    void testGetMovesToFront() {
        LRUCache cache = new LRUCache(2);
        
        cache.put(1, 10);
        cache.put(2, 20);
        
        
        cache.get(1);
       
        
        cache.put(3, 30); 
        
        assertEquals(10, cache.get(1));
        assertEquals(-1, cache.get(2)); 
        assertEquals(30, cache.get(3));
    }

    @Test
    void testCachePutUpdateValue() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        assertEquals(10, cache.get(1));
        
        cache.put(1, 100);
        assertEquals(100, cache.get(1));
    }
}