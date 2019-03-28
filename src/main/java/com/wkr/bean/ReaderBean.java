package com.wkr.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Reader", catalog = "postgres")
public class ReaderBean {
    @Id
    private int ReaderHEXAddr;
    @Column(name = "readername")
    private String ReaderName;
    @Column(name = "readeraddress")
    private String ReaderAddress;
    @Column(name = "readergps")
    private String ReaderGPS;
    @Column(name = "readersetmac")
    private String ReaderSetMAC;

    public int getReaderHEXAddr() {
        return ReaderHEXAddr;
    }

    public void setReaderHEXAddr(int readerHEXAddr) {
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
}
