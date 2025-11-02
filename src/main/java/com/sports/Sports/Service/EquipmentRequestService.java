package com.sports.Sports.Service;

import com.sports.Sports.Repository.EquipmentRepository;
import com.sports.Sports.Repository.EquipmentRequestRepository;
import com.sports.Sports.Repository.IssueRecordRepository;
import com.sports.Sports.Repository.UserRepository;
import com.sports.Sports.model.Equipment;
import com.sports.Sports.model.EquipmentRequest;
import com.sports.Sports.model.IssueRecord;
import com.sports.Sports.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EquipmentRequestService {

    private final EquipmentRequestRepository requestRepository;
    private final EquipmentRepository equipmentRepository;
    private final IssueRecordRepository issueRecordRepository;
    private final UserRepository userRepository;

    public EquipmentRequestService(
            EquipmentRequestRepository requestRepository,
            EquipmentRepository equipmentRepository,
            IssueRecordRepository issueRecordRepository,
            UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.equipmentRepository = equipmentRepository;
        this.issueRecordRepository = issueRecordRepository;
        this.userRepository = userRepository;
    }

    // USER: Create Equipment Request
    public EquipmentRequest createRequest(String equipmentName , String email) {


        User user = userRepository.findByEmail(email).orElseThrow();

        EquipmentRequest request = new EquipmentRequest();
        request.setEquipmentName(equipmentName);
        request.setStatus("PENDING");
        request.setRequestDate(LocalDateTime.now());
        request.setUser(user);

        return requestRepository.save(request);
    }

    // USER: View own requests
    public List<EquipmentRequest> getUserRequests() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return requestRepository.findByUserEmail(email);
    }

    // ADMIN: View all requests
    public List<EquipmentRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    // ADMIN: Approve or Reject Equipment Request
    public EquipmentRequest updateStatusAndIssue(Long requestId, String status) {
        EquipmentRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (status.equalsIgnoreCase("APPROVED")) {
            Equipment equipment = (Equipment) equipmentRepository.findByNameOfEquipment(request.getEquipmentName())
                    .orElseThrow(() -> new RuntimeException("Equipment not found"));


            if (equipment.getQuantityAvailable() <= 0) {
                throw new RuntimeException("No available equipment");
            }


            IssueRecord record = new IssueRecord();
            record.setEquipment(equipment);
            record.setIssuedTo(request.getUser().getName());
            record.setIssueDate(LocalDateTime.now());
            record.setReturned(false);
            record.setReturnDate(null);

            issueRecordRepository.save(record);


            equipment.setIssuedQuantity(equipment.getIssuedQuantity() + 1);
            equipment.setQuantityAvailable(equipment.getQuantityAvailable() - 1);
            equipment.getIssueRecords().add(record);
            equipmentRepository.save(equipment);
        }

        request.setStatus(status.toUpperCase());
        return requestRepository.save(request);
    }
}
