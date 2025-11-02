package com.sports.Sports.Service;

import com.sports.Sports.Repository.EquipmentRepository;
import com.sports.Sports.model.Equipment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImp  implements EquipmentService{
    private  final   EquipmentRepository equipmentRepository;

    public EquipmentServiceImp(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }
     

    @Override
    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    @Override
    public Optional<Equipment> getEquipmentById(Long id) {
        return equipmentRepository.findById(id);
    }

    @Override
    public Equipment saveEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment updateEquipment(Long id, Equipment updatedEquipment) {
        return equipmentRepository.findById(id)
                .map(existing -> {
                    existing.setSportName(updatedEquipment.getSportName());
                    existing.setNameOfEquipment(updatedEquipment.getNameOfEquipment());
                    existing.setIssuer(updatedEquipment.getIssuer());
                    existing.setIssueDate(updatedEquipment.getIssueDate());
                    existing.setReturnDate(updatedEquipment.getReturnDate());
                    existing.setFine(updatedEquipment.getFine());
                    existing.setItems(updatedEquipment.getItems());
                    existing.setName(updatedEquipment.getName());
                    existing.setQuantityAvailable(updatedEquipment.getQuantityAvailable());
                    existing.setStatus(updatedEquipment.getStatus());
                    existing.setIssueRecords(updatedEquipment.getIssueRecords());
                    existing.setAvailable(updatedEquipment.isAvailable());
                    existing.setTotalQuantity(updatedEquipment.getTotalQuantity());
                    existing.setIssuedQuantity(updatedEquipment.getIssuedQuantity());
                    return equipmentRepository.save(existing);
                })
                .orElseGet(() -> {
                    updatedEquipment.setId(id);
                    return equipmentRepository.save(updatedEquipment);
                });
    }
    @Override
    public void deleteEquipment(Long id) {
       equipmentRepository.deleteById(id);
    }

    @Override
    public List<Equipment> getEquipmentsBySport(String sportName) {
        return equipmentRepository.findBySportNameIgnoreCase(sportName);
    }

}
