package com.sports.Sports.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity    // create table in database
@Data   // create getter and setter of the element
public class Equipment {
    @Id    // unique id
    @GeneratedValue(strategy = GenerationType.AUTO)   //genreated unique id
    private long id;
    private String sportName; // Add this field

    private String nameOfEquipment;

    private String issuer;
    private Date issueDate;
    private Date returnDate;
    private int fine;
    @ElementCollection      /* need for storing items Why use @ElementCollection?
    You use it when:

    You want to store a list, set, or map of basic or embeddable values.

    These values are not full-fledged entities, but just belong to the parent entity.

    You don’t need a separate entity or primary key for them.

🧠 How it works:
    JPA creates a new table to store the collection.

            The new table will have:

    A foreign key referencing the parent entity.

    A column for each element in the collection.  */
    private List<String> items ;
    private String name;
    private int quantityAvailable;
    private String status;
    @OneToMany(mappedBy = "equipment" , cascade = CascadeType.ALL ,orphanRemoval = true)

    /*
    mapped by use on one side
    use only for
    onetomany
    manytomany
    one to many means one equipment have links to many issuse recorde
mappedBy = "equipment" → This means the IssueRecord entity owns the relationship, and it has a field named equipment (which should be annotated with @ManyToOne).

cascade = CascadeType.ALL → Any operation (like save, delete, update) done on the Equipment will cascade to all related IssueRecord entities.
     */
@JsonManagedReference
    private List<IssueRecord> issueRecords;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getNameOfEquipment() {
        return nameOfEquipment;
    }

    public void setNameOfEquipment(String nameOfEquipment) {
        this.nameOfEquipment = nameOfEquipment;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<IssueRecord> getIssueRecords() {
        return issueRecords;
    }

    public void setIssueRecords(List<IssueRecord> issueRecords) {
        this.issueRecords = issueRecords;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getIssuedQuantity() {
        return issuedQuantity;
    }

    public void setIssuedQuantity(int issuedQuantity) {
        this.issuedQuantity = issuedQuantity;
    }

    private boolean available = true;
    private int totalQuantity;
    private int issuedQuantity;
}
