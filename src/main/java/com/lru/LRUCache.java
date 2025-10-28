    package com.lru;

    import java.util.HashMap;
    import java.util.Map;

    public class LRUCache {
        /*
        * The Node class is used to store a single value
        */
        private class Node {
            int key;
            int val;
            Node previousNode;
            Node nextNode;
            
            public Node(int key,int val){
                this.key = key;
                this.val = val;
            }
        }

        private final int capacity;
        private Map<Integer, Node> cacheMap;
        private final Node head;
        private final Node tail;
        

        /**
         * Constructs a new LRU (Least Recently Used) cache with the specified maximum capacity.
         *
         * The implementation combines a HashMap for fast key-to-node lookups and a doubly
         * linked list to maintain usage order. Two sentinel (dummy) nodes for head and tail
         * are created and linked so the list is initially empty. Newly accessed or inserted
         * entries will be moved toward the head; the least recently used entry is located
         * just before the tail.
         *
         * Precondition:
         * The provided capacity should be a positive integer representing the maximum number
         * of entries this cache will store. Behavior is undefined for non-positive capacities.
         *
         *
         * Complexity:
         * Construction runs in O(1) time and uses O(1) additional space for the initial sentinel
         * nodes (excluding internal allocations performed by the underlying HashMap).
         *
         * @param capacity the maximum number of entries the cache may hold; expected to be > 0
         */
        public LRUCache(int capacity){
            this.capacity = capacity;
            this.cacheMap = new HashMap<>();
            this.head = new Node( 0, 0);
            this.tail = new Node( 0, 0);

            this.head.nextNode = tail;
            this.tail.previousNode = head;
        }

        /**
         * Removes the specified node from the doubly-linked list by relinking its
         * adjacent neighbors. Concretely, this sets node.prev.nextNode to node.nextNode
         * and node.nextNode.previousNode to node.prev, effectively excising the node
         * from the list in O(1) time.
         *
         * Preconditions:
         * - node must not be null
         * - node.prev must not be null
         * - node.nextNode must not be null
         *
         * Note: This method updates the neighboring nodes' pointers but does not
         * clear the removed node's own prev/next references.
         *
         * @param node the node to remove from the list; must have non-null prev and nextNode
         */
        private void removeNode(Node node){
            Node prevNode = node.previousNode;
            Node nextNode = node.nextNode;
            prevNode.nextNode = nextNode;
            nextNode.previousNode = prevNode;
        }

        /**
         * Inserts the given {@code node} at the front of the doubly linked list,
         * immediately following the dummy head node.
         * This operation updates the necessary pointers to maintain the
         * integrity of the doubly linked list:
         * <ul>
         *     <li>The new node's {@code previousNode} pointer is set to the node that should precede it (implicitly the head node for a correct list structure).</li>
         *     <li>The new node's {@code nextNode} pointer is set to the node that was previously at the front (i.e., {@code head.nextNode}).</li>
         *     <li>The head node's {@code nextNode} pointer is updated to point to the new node.</li>
         *     <li>The {@code previousNode} pointer of the node that was previously at the front (which is now referenced by {@code node.nextNode}) is updated to point to the new node.</li>
         * </ul>
         *
         * @param node The node to be added to the front of the list.
         */
        private void addToFront(Node node){        
            node.previousNode = head;
            node.nextNode = head.nextNode;
            head.nextNode = node;
            node.nextNode.previousNode = node;
        }

        /**
         * Retrieves the value associated with the specified key from the cache.
         * If the key exists, the corresponding node is moved to the front of the linked list
         * (marking it as most recently used) and its value is returned.
         * If the key does not exist in the cache, -1 is returned.
         *
         * @param key The key of the element to retrieve.
         * @return The value associated with the key, or -1 if the key is not found.
         */
        public int get(int key){
            if(!cacheMap.containsKey(key)){
                return -1;
            }

            Node toRet = cacheMap.get(key);
            removeNode(toRet);
            addToFront(toRet);

            return toRet.val;
        }

        public void put(int key, int value) {
            if (cacheMap.containsKey(key)) {
                Node node = cacheMap.get(key);
                node.val = value;
                removeNode(node);
                addToFront(node);
                return;
            }

            Node newNode = new Node(key, value);
            cacheMap.put(key, newNode);
            addToFront(newNode);

            if (cacheMap.size() > capacity) {
                Node lruNode = tail.previousNode;
                removeNode(lruNode);
                cacheMap.remove(lruNode.key);
            }
        }
    }
