# Processor Events

- Project Folder Structure
```
admin@Mindstixs-MacBook-Air Flipkart % tree          
.
└── process_events
    ├── ProcessEvents.java
    ├── README.md
    ├── exceptions
    │   ├── PayloadMismatchException.java
    │   └── ProcessorCrashedException.java
    ├── model
    │   └── Event.java
    ├── repository
    │   └── EventRepository.java
    └── service
        └── EventProcessor.java

6 directories, 7 files
```

- Create a model for event ids and it's payload.
- Further there is an implementation of crashing, so to terminate/fail with graceful message, we will write exception class.
- Create a repository for storing everything in Memory
- Create a service class
- Call the main Java method.