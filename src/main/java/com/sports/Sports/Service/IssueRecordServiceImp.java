package com.sports.Sports.Service;

import com.sports.Sports.Repository.IssueRecordRepository;
import com.sports.Sports.model.IssueRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class IssueRecordServiceImp implements IssueRecordService{
    private final  IssueRecordRepository issueRecordRepository;

    public IssueRecordServiceImp(IssueRecordRepository issueRecordRepository) {
        this.issueRecordRepository = issueRecordRepository;
    }

    @Override
    public List<IssueRecord> getAllIssueRecord() {
        return issueRecordRepository.findAll();
    }

    @Override
    public Optional<IssueRecord> getIssueRecordById(long id) {
        return issueRecordRepository.findById(id);
    }

    @Override
    public IssueRecord saveIssueRecord(IssueRecord issueRecord) {
        return issueRecordRepository.save(issueRecord);
    }

    @Override
    public IssueRecord updatedIssueRecord(Long id, IssueRecord updatedRecord) {
        return issueRecordRepository.findById(id)
                .map(existing -> {
                    existing.setEquipment(updatedRecord.getEquipment());
                    existing.setTeam(updatedRecord.getTeam());
                    existing.setIssuedTo(updatedRecord.getIssuedTo());
                    existing.setIssueDate(updatedRecord.getIssueDate());
                    existing.setReturnDate(updatedRecord.getReturnDate());
                    existing.setReturned(updatedRecord.isReturned());
                    return issueRecordRepository.save(existing);
                })
                .orElseGet(() -> {
                    updatedRecord.setId(id);
                    return issueRecordRepository.save(updatedRecord);
                });
    }

    @Override
    public void deleteIssueRecord(long id) {
        issueRecordRepository.deleteById(id);
    }
}
