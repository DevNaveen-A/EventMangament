# 📄 Event Management Backend (Spring Boot)

## 🧩 Overview
This is a fully backend project built using *Spring Boot, designed to manage events, attendees, organizers, venues, and registrations through well-structured REST APIs. The application follows a modular architecture and uses PostgreSQL for data persistence. All functionalities are tested using **Postman*.

---

## ⚙ Tech Stack
- Java 17+
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL (managed via PgAdmin)
- Maven (project build tool)
- Postman (for API testing)

---

## 📁 Controller Modules
The application is structured into multiple controller-service layers, each responsible for handling a specific entity:

- EventController
- AttendeController
- OrganizerController
- VenueController
- RegisterController

---

## 🔄 Common Features Across Controllers
Each controller provides the following standard RESTful operations:

- *Create* (POST)
- *Read all / by ID* (GET)
- *Update* (PUT)
- *Delete* (DELETE)
- *Pagination & Sorting* (where applicable)

---

## 🧠 Additional Functionalities
Some controllers include extra features:

- *Filtering by foreign key* (e.g., get events by organizer, registrations by attendee/event)
- *Custom search* (e.g., get attendee by contact number, venues by location)
- *Paginated and sorted results* using Spring Data
- *Consistent API responses* using ResponseEntity<ResponseStructure<T>>

---

## 🚀 How to Run the Project

1. *Clone the repository*
2. *Set up PostgreSQL*, create DB, and configure credentials in application.properties
3. *Build & run* the app using:
   ```bash
   mvn spring-boot:run
