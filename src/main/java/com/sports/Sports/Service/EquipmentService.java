package com.sports.Sports.Service;

import com.sports.Sports.model.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {
List<Equipment> getAllEquipments();
Optional<Equipment> getEquipmentById(Long id);
Equipment saveEquipment(Equipment equipment);

Equipment updateEquipment(Long id ,  Equipment updatedEquipment);
 void deleteEquipment(Long id);

    List<Equipment> getEquipmentsBySport(String sportName);
}
