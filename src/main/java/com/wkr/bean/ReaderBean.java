package com.wkr.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Reader", catalog = "postgres")
public class ReaderBean {
    @Id
    private Integer ReaderHEXAddr;
    @Column(name = "readername")
    private String ReaderName;
    @Column(name = "readeraddress")
    private String ReaderAddress;
    @Column(name = "readergps")
    private String ReaderGPS;
    @Column(name = "readersetmac")
    private String ReaderSetMAC;
    @Column(name = "isonline")
    private Integer isOnline;

    public Integer getReaderHEXAddr() {
        return ReaderHEXAddr;
    }

    public void setReaderHEXAddr(Integer readerHEXAddr) {
        ReaderHEXAddr = readerHEXAddr;
    }

    public String getReaderName() {
        return ReaderName;
    }

    public void setReaderName(String readerName) {
        ReaderName = readerName;
    }

    public String getReaderAddress() {
        return ReaderAddress;
    }

    public void setReaderAddress(String readerAddress) {
        ReaderAddress = readerAddress;
    }

    public String getReaderGPS() {
        return ReaderGPS;
    }

    public void setReaderGPS(String readerGPS) {
        ReaderGPS = readerGPS;
    }

    public String getReaderSetMAC() {
        return ReaderSetMAC;
    }

    public void setReaderSetMAC(String ReadersetMAC) {
        ReaderSetMAC = ReadersetMAC;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }
}
