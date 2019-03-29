package com.wkr.dao;

import com.wkr.bean.ReaderBean;

import java.util.List;

public interface ReaderDao {
    ReaderBean fetchReaderByMAC(ReaderBean readerBean);
    void saveReaderSetting(ReaderBean readerBean);
    List<ReaderBean> getTotalCount();
    void updateReaderSetting(ReaderBean readerBean);
    void updateReaderIsOnline(String MAC, int isOnline);
}
