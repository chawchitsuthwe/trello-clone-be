package com.seclub.trelloclone.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "card")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Card extends MainModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date due_date;
    private Integer position;
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", nullable = false)
    @JsonIgnoreProperties("cards")
    private List list;

    @ManyToMany
    @JoinTable(
            name = "card_member",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "account_username")

    )
    private java.util.List<Account> accounts;

    @ManyToMany
    @JoinTable(
            name = "card_label",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private java.util.List<Label> labels;

    @OneToMany(
            mappedBy = "cardId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    @OrderBy("position asc")
    private java.util.List<Checklist> checklists;

    public Card(){}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public List getList() {
        return list;
    }
    public void setList(List list) {
        this.list = list;
    }

    public java.util.List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(java.util.List<Account> accounts) {
        this.accounts = accounts;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public java.util.List<Label> getLabels() {
        return labels;
    }

    public void setLabels(java.util.List<Label> labels) {
        this.labels = labels;
    }

    public java.util.List<Checklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(java.util.List<Checklist> checklists) {
        this.checklists = checklists;
    }
}