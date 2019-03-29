package com.wkr.service.Impl;

import com.wkr.bean.ReaderBean;
import com.wkr.dao.ReaderDao;
import com.wkr.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    ReaderDao readerDao;

    @Override
    public ReaderBean fetchReaderByMAC(ReaderBean readerBean) {
        return readerDao.fetchReaderByMAC(readerBean);
    }

    @Override
    public void saveReaderSetting(ReaderBean readerBean) {
        readerDao.saveReaderSetting(readerBean);
    }

    @Override
    public List<ReaderBean> getTotalCount() {
        return readerDao.getTotalCount();
    }

    @Override
    public void updateReaderSetting(ReaderBean readerBean) {
        readerDao.updateReaderSetting(readerBean);
    }

    @Override
    public void updateReaderIsOnline(String MAC, int isOnline) {
        readerDao.updateReaderIsOnline(MAC, isOnline);
    }
}
