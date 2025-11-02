package com.sports.Sports.Service;

import com.sports.Sports.model.IssueRecord;

import java.util.List;
import java.util.Optional;

public interface IssueRecordService {
    List<IssueRecord> getAllIssueRecord();

    Optional<IssueRecord> getIssueRecordById(long id );
    IssueRecord saveIssueRecord(IssueRecord issueRecord);
    IssueRecord updatedIssueRecord(Long id ,  IssueRecord updatedRecord);
    void  deleteIssueRecord(long id);


}
