package com.pj.web;

import com.pj.model.Appuser;
import com.pj.service.AppuserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Provides API endpoints for Book operations
 *
 * @author Pavan Kumar Jadda
 * @since 2.1.0
 */
@RestController
@RequestMapping("/api/v1/appuser")
public class AppUserController {
    private final AppuserService appuserService;

    public AppUserController(AppuserService appuserService) {
        this.appuserService = appuserService;
    }

    /**
     * Find all books
     *
     * @return List of all books
     *
     * @author Pavan Kumar Jadda
     * @since 2.1.0
     */
    @GetMapping("/find/allappuser")
    public ResponseEntity<List<Appuser>> getAppuser() {
        return ResponseEntity.ok(appuserService.findAll());
    }

    /**
     * Find the book by ID
     *
     * @param id ID of the book that needs to be found
     *
     * @return Book matching the given ID
     *
     * @author Pavan Kumar Jadda
     * @since 2.1.0
     */
    @GetMapping("/find/id/{id}")
    public ResponseEntity<Optional<Appuser>> getAppuserById(@PathVariable String id) {
        return ResponseEntity.ok(appuserService.findById(id));
    }

    /**
     * Save the new book
     *
     * @param book an object that contains new book information
     *
     * @return Created book
     *
     * @author Pavan Kumar Jadda
     * @since 2.1.0
     */
    @PostMapping("/saveuser")
    public ResponseEntity<Appuser> saveAppuser(@RequestBody Appuser appuser) {
        return ResponseEntity.ok(appuserService.insertAppuser(appuser));
    }

      /**
     * Update the book
     *
     * @param book an object that contains updated book information
     *
     * @return Updated book
     *
     * @author Pavan Kumar Jadda
     * @since 2.1.0
     */
    @PutMapping("/updateappuser")
    public ResponseEntity<Appuser> updateappuser(@RequestBody Appuser appuser) {
        return ResponseEntity.ok(appuserService.updateAppuser(appuser));
    }
    
    /**
     * Delete book by ID
     *
     * @param id ID of the book that needs to be deleted
     *
     * @author Pavan Kumar Jadda
     * @since 2.1.0
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteappuser(@PathVariable String id) {
        appuserService.deleteById(id);
        return new ResponseEntity<>("{\"result\":\"success\"}", HttpStatus.OK);
    }
}
