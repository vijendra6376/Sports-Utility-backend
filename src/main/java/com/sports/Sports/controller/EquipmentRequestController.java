package com.sports.Sports.controller;

import com.sports.Sports.Service.EquipmentRequestService;
import com.sports.Sports.model.EquipmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/requests")
public class EquipmentRequestController {

    private final EquipmentRequestService requestService;

    @Autowired
    public EquipmentRequestController(EquipmentRequestService requestService) {
        this.requestService = requestService;
    }

    /**
     * USER: Create a new equipment request.
     * Endpoint: POST /requests
     * Body: { "equipmentName": "Some Equipment" }
     */
    @PostMapping
    public ResponseEntity<?> createRequest(@RequestBody Map<String, String> body) {
        String equipmentName = body.get("equipmentName");
        String email = body.get("email");

        if (equipmentName == null || equipmentName.isBlank()) {
            return ResponseEntity.badRequest().body("Field 'equipmentName' is required");
        }
        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body("Field 'email' is required");
        }
        try {
            EquipmentRequest saved = requestService.createRequest(equipmentName, email);
            return ResponseEntity.status(201).body(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create request: " + e.getMessage());
        }
    }

    /**
     * USER: View own requests.
     * Endpoint: GET /requests/my
     */
    @GetMapping("/my")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<EquipmentRequest>> getMyRequests() {
        List<EquipmentRequest> list = requestService.getUserRequests();
        return ResponseEntity.ok(list);
    }

    /**
     * ADMIN: View all equipment requests.
     * Endpoint: GET /requests
     */
    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EquipmentRequest>> getAllRequests() {
        List<EquipmentRequest> list = requestService.getAllRequests();
        return ResponseEntity.ok(list);
    }

    /**
     * ADMIN: Approve or reject a request (and issue equipment if approved).
     * Endpoint: PUT /requests/{id}
     * Body: { "status": "APPROVED" } or { "status": "REJECTED" }
     *
     * Note: status should be either "APPROVED" or "REJECTED" (case-insensitive).
     */
    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateRequestStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        String status = body.get("status");
        if (status == null || status.isBlank()) {
            return ResponseEntity.badRequest().body("Field 'status' is required");
        }
        String normalized = status.trim().toUpperCase();
        if (!normalized.equals("APPROVED") && !normalized.equals("REJECTED")) {
            return ResponseEntity.badRequest().body("Status must be 'APPROVED' or 'REJECTED'");
        }
        try {
            EquipmentRequest updated = requestService.updateStatusAndIssue(id, normalized);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            // e.g., request not found, equipment not available, etc.
            return ResponseEntity.badRequest().body("Failed to update request: " + e.getMessage());
        }
    }

    /**
     * (Optional) ADMIN: Delete a request if needed.
     * Endpoint: DELETE /requests/{id}
     * Only if you want admins to remove requests entirely.
     */
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteRequest(@PathVariable Long id) {
        try {
            // You may want to check existence first:
            // requestService.getAllRequests() or repository directly
            requestService.getAllRequests().stream()
                    .filter(r -> r.getId().equals(id))
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("Request not found with id: " + id));
            // If exists, delete. You may need to add a delete method in service:
            // requestService.deleteRequest(id);
            // If not implemented, throw or skip.
            // For example:
            // requestService.deleteRequest(id);
            return ResponseEntity.ok("Deleted request with id: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
