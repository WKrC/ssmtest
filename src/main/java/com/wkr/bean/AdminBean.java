package com.wkr.bean;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin", catalog = "postgres")
public class AdminBean {
    @Id
    private String adminindexcode;
    @Column(name = "adminpassword")
    private String adminpassword;

    public String getAdminindexcode() {
        return adminindexcode;
    }

    public void setAdminindexcode(String adminindexcode) {
        this.adminindexcode = adminindexcode;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }
}
