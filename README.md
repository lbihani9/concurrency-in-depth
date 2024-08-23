This repository contains solutions to the problem statements (PS) involving concurrency and multi-threading concepts in Java. These assignments range from basic to advanced concepts.

# Problem statements
### 1. **Basic Thread Creation and Management**
- **Task**: Create a program that spawns multiple threads (e.g., 5) where each thread prints numbers from 1 to 20. Ensure each thread starts and completes independently.
- **Concepts**: Thread creation, thread lifecycle, basic thread management.

### 2. **Thread Synchronization Using Locks**
- **Task**: Implement a bank account system where multiple threads represent customers depositing and withdrawing money. Use synchronization to ensure account balances are correctly updated.
- **Concepts**: Mutual exclusion, critical sections, synchronized blocks or methods.

### 3. **Producer-Consumer Problem Using Blocking Queues**
- **Task**: Implement the classic producer-consumer problem where multiple producer threads add items to a shared buffer, and multiple consumer threads remove items from the buffer.
- **Concepts**: Blocking queues, thread communication, wait and notify methods.

### 4. **Deadlock Simulation and Prevention**
- **Task**: Simulate a deadlock scenario with multiple threads trying to acquire multiple locks. Then, implement a solution to avoid the deadlock using a proper locking order or other strategies.
- **Concepts**: Deadlock, lock ordering, deadlock prevention strategies.

### 5. **Thread Pool Implementation**
- **Task**: Implement a simple thread pool that can manage a fixed number of threads. Submit tasks to the thread pool and ensure they are executed efficiently.
- **Concepts**: Thread pooling, task scheduling, resource management.

### 6. **Dining Philosophers Problem**
- **Task**: Implement the dining philosophers problem using semaphores or monitors to ensure no deadlocks or starvation.
- **Concepts**: Semaphores, resource allocation, deadlock prevention, starvation.

### 7. **Implementing a Custom Semaphore**
- **Task**: Create a custom semaphore class and use it to solve a problem like the readers-writers problem, where you control access to a shared resource between multiple readers and writers.
- **Concepts**: Semaphores, mutual exclusion, fairness in thread scheduling.

### 8. **Atomic Operations and CAS (Compare-And-Swap)**
- **Task**: Implement a thread-safe counter using atomic operations. Then, demonstrate how a CAS operation can be used to implement a lock-free stack or queue.
- **Concepts**: Atomic variables, CAS, lock-free data structures.

### 9. **Futures and Callable Tasks**
- **Task**: Use the `Future` and `Callable` interfaces to execute tasks in the background and retrieve results once they are completed. 
- **Hint**: We can perform summation on independent sub-arrays and finally aggregate the results and print the final sum of the array.
- **Concepts**: Callable tasks, Futures, asynchronous task execution.

### 10. **Parallel Processing with Fork/Join Framework**
- **Task**: Implement a parallelized version of a recursive algorithm (e.g., merge sort or matrix multiplication) using the Fork/Join framework.
- **Concepts**: Fork/Join framework, parallelism, divide and conquer.

### 11. **ThreadLocal Variables**
- **Task**: Implement a scenario where each thread maintains its own state using `ThreadLocal` variables, such as generating unique IDs for each thread without sharing state.
- **Concepts**: ThreadLocal variables, thread-specific data, isolation.

### 12. **Barrier Synchronization Using CyclicBarrier**
- **Task**: Implement a simulation where multiple threads perform tasks in stages, and each stage waits for all threads to reach a synchronization point before proceeding.
- **Concepts**: CyclicBarrier, barrier synchronization, coordinating threads.

### 13. **Custom ReentrantLock Implementation**
- **Task**: Implement a simple version of `ReentrantLock` and use it to solve a problem requiring reentrant synchronization.
- **Concepts**: Reentrant locks, fairness in locking, custom synchronization.

### 14. **Thread Interruption and Handling**
- **Task**: Create a program where threads perform long-running tasks. Implement proper handling for thread interruption, ensuring the program can safely terminate ongoing tasks when interrupted.
- **Concepts**: Thread interruption, handling InterruptedException, cleanup after interruption.

### 15. **Volatile Variables and Memory Consistency**
- **Task**: Implement a scenario where a shared variable is updated by multiple threads. Use the `volatile` keyword to ensure visibility of changes across threads, and explore what happens without it.
- **Concepts**: Volatile variables, memory consistency, visibility of shared data.

### 16. **Thread-Safe Stack Implementation**
- **Task**: Implement a thread-safe stack with push, pop, and peek operations. Ensure that the stack handles concurrent access correctly.
- **Concepts**: Synchronization, locks, and condition variables.

### 17. **Concurrent Linked List**
- **Task**: Create a thread-safe singly linked list that supports insertion, deletion, and traversal operations. Ensure that these operations are efficient under concurrent access.
- **Concepts**: Fine-grained locking, lock striping, and CAS (Compare-And-Swap).

### 18. **Thread-Safe Queue Using Two Stacks**
- **Task**: Implement a thread-safe queue using two stacks. This will require careful synchronization to ensure correct behavior under concurrent access.
- **Concepts**: Synchronization, locks, thread coordination.

### 19. **Concurrent LRU Cache**
- **Task**: Build a thread-safe LRU (Least Recently Used) cache using a combination of a doubly linked list and a hash map. The cache should handle multiple threads accessing and updating it concurrently.
- **Concepts**: Synchronization, reentrant locks, concurrent collections.

### 20. **Thread-Safe Bounded Blocking Queue**
- **Task**: Implement a bounded blocking queue, where producers add elements to the queue and consumers remove elements. If the queue is full, producers should wait; if the queue is empty, consumers should wait.
- **Concepts**: Blocking queues, producer-consumer pattern, wait/notify mechanism.

### 21. **Concurrent Fixed-Size Circular Buffer**
- **Task**: Implement a thread-safe circular buffer (ring buffer) where multiple producers can add data and multiple consumers can remove data. Ensure proper handling of buffer overflow and underflow.
- **Concepts**: Synchronization, condition variables, buffer management.

### 22. **Thread-Safe Priority Queue**
- **Task**: Create a thread-safe priority queue where elements are dequeued based on priority. Implement efficient concurrent access control to ensure thread safety.
- **Concepts**: Synchronization, locks, priority-based task scheduling.

### 23. **Concurrent HashMap with Expiry**
- **Task**: Implement a thread-safe hash map where entries expire after a certain time if not accessed. Use multithreading to periodically clean up expired entries.
- **Concepts**: Concurrent collections, thread pools, scheduling, and time-based operations.

### 24. **Concurrent Trie Data Structure**
- **Task**: Implement a thread-safe Trie (prefix tree) that supports insertions, deletions, and lookups. Ensure that concurrent access to the Trie does not cause inconsistencies.
- **Concepts**: Fine-grained locking, synchronization, recursive data structures.

### 25. **Multithreaded Word Count**
- **Task**: Build a multithreaded word count program that processes large text files. Divide the text into chunks, process each chunk in parallel, and then combine the results.
- **Concepts**: Thread pooling, synchronization, map-reduce pattern.

### 26. **Concurrent Task Scheduler**
- **Task**: Design a thread-safe task scheduler that can schedule tasks to run after a delay or at a fixed interval. The scheduler should manage multiple tasks concurrently.
- **Concepts**: Thread pooling, synchronization, timed tasks, and scheduling.

### 27. **Concurrent Memory Pool**
- **Task**: Implement a thread-safe memory pool where multiple threads can allocate and deallocate memory blocks. Ensure efficient management of the memory pool under concurrent access.
- **Concepts**: Memory management, synchronization, resource allocation.

### 28. **Concurrent Cache with Write-Behind Policy**
- **Task**: Implement a thread-safe cache where updates to the cache are asynchronously written to a backing store. Ensure consistency between the cache and the backing store.
- **Concepts**: Synchronization, asynchronous processing, data consistency.