package com.sports.Sports.controller;

import com.sports.Sports.Service.IssueRecordService;
import com.sports.Sports.model.IssueRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/issuerecords")
public class IssueRecordController {

    private final IssueRecordService issueRecordService;

    @Autowired
    public IssueRecordController(IssueRecordService issueRecordService) {
        this.issueRecordService = issueRecordService;
    }

    // Get all issue records (no role filter)
    @GetMapping
    public ResponseEntity<List<IssueRecord>> getIssueRecords() {
        List<IssueRecord> allRecords = issueRecordService.getAllIssueRecord();
        return ResponseEntity.ok(allRecords);
    }

    // Get issue record by ID
    @GetMapping("/{id}")
    public Optional<ResponseEntity<IssueRecord>> getIssueRecordById(@PathVariable Long id) {
        Optional<IssueRecord> opt = issueRecordService.getIssueRecordById(id);
        return opt.map(ResponseEntity::ok);
//                .orElseGet(() -> ResponseEntity.status(404).body("IssueRecord not found with id: " + id));
    }

    // Create a new issue record (anyone can call this)
    @PostMapping
    public ResponseEntity<IssueRecord> createIssueRecord(@RequestBody IssueRecord issueRecord) {
        IssueRecord saved = issueRecordService.saveIssueRecord(issueRecord);
        return ResponseEntity.status(201).body(saved);
    }

    // Update an existing issue record
    @PutMapping("/{id}")
    public ResponseEntity<?> updateIssueRecord(
            @PathVariable Long id,
            @RequestBody IssueRecord updatedRecord) {
        try {
            IssueRecord saved = issueRecordService.updatedIssueRecord(id, updatedRecord);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to update IssueRecord: " + e.getMessage());
        }
    }

    // Delete an issue record
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIssueRecord(@PathVariable Long id) {
        Optional<IssueRecord> opt = issueRecordService.getIssueRecordById(id);
        if (opt.isPresent()) {
            issueRecordService.deleteIssueRecord(id);
            return ResponseEntity.ok("Deleted IssueRecord with id: " + id);
        } else {
            return ResponseEntity.status(404).body("IssueRecord not found with id: " + id);
        }
    }
}
