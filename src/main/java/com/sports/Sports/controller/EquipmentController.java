package com.sports.Sports.controller;

import com.sports.Sports.Service.EquipmentService;
import com.sports.Sports.model.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    // GET all equipment
    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        List<Equipment> list = equipmentService.getAllEquipments();
        return ResponseEntity.ok(list);
    }

    // GET equipment by ID
    @GetMapping("/{id}")
    public Optional<ResponseEntity<Equipment>> getEquipmentById(@PathVariable Long id) {
        Optional<Equipment> opt = equipmentService.getEquipmentById(id);
        return opt.map(ResponseEntity::ok);
    }

    // ✅ NEW: GET equipment by sport name
    @GetMapping("/sport/{sportName}")
    public ResponseEntity<List<Equipment>> getEquipmentsBySport(@PathVariable String sportName) {
        List<Equipment> equipments = equipmentService.getEquipmentsBySport(sportName);
        return ResponseEntity.ok(equipments);
    }

    // POST create new equipment
    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        Equipment saved = equipmentService.saveEquipment(equipment);
        return ResponseEntity.status(201).body(saved);
    }

    // PUT update existing equipment
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
        try {
            Equipment saved = equipmentService.updateEquipment(id, updatedEquipment);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to update equipment: " + e.getMessage());
        }
    }

    // DELETE equipment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEquipment(@PathVariable Long id) {
        Optional<Equipment> opt = equipmentService.getEquipmentById(id);
        if (opt.isPresent()) {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.ok("Deleted equipment with id: " + id);
        } else {
            return ResponseEntity.status(404).body("Equipment not found with id: " + id);
        }
    }
}
