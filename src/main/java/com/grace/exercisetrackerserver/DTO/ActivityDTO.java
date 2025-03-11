package com.grace.exercisetrackerserver.DTO;

import lombok.Data;


import java.util.Date;

@Data   //generating boilerplate code like getters, setters, `toString`, `equals`, `hashCode`, and a `constructor`.

public class ActivityDTO {


    private Date date;
    private int steps;
    private double distance;
    private int caloriesBurned;

}

//DTO (ActivityDTO) ensures only required data is exposed in API responses.

/**
 * ### How a DTO fits in the Application
 * In a typical 3-layer architecture (Controller, Service, Data/Repository):
 * 1. **Controller Layer:** Accepts HTTP requests and often works with DTOs for request and response payloads. For example, the client might send activity data in a JSON format that maps to the `ActivityDTO`.
 * 2. **Service Layer:** Business logic processes the DTO and may convert it to or from other internal objects (e.g., database entities like `ActivityEntity`).
 * 3. **Data/Repository Layer:** Interacts with the database, often using an entity layer that maps directly to the database schema (e.g., `ActivityEntity`).
 *
 * DTOs act as intermediaries, providing structured data without directly coupling the Controller to database entities.
 *
 *
 * ### Example Workflow of `ActivityDTO`:
 * 1. A user submits activity data (steps, distance, etc.) through an API.
 * 2. The API receives the payload and maps it to an `ActivityDTO`.
 * 3. The `ActivityDTO` is passed to the Service layer for processing (e.g., saving to the database after validation).
 * 4. If needed, an `ActivityEntity` is populated from the DTO and stored in the database.
 * 5. When fetching activity data, the fetched `ActivityEntity` is mapped to an `ActivityDTO`, which is then sent back as the API response.
 *
 *
 * ### Benefits in Your Case
 * Using `ActivityDTO` in your project ensures:
 * - **Clarity:** Separates the data structure used at the API layer (`ActivityDTO`) from the internal database object (`ActivityEntity`), simplifying debugging and understanding.
 * - **Security:** Limits the data exposed to the web layer, preventing over-sharing of sensitive information (e.g., database-centric details like primary keys that shouldn't be exposed).
 * - **Flexibility:** Allows for future changes with minimal refactoring. For instance, if the database schema changes, only your entity mappings need to change—not the DTOs exposed via APIs.
 */
