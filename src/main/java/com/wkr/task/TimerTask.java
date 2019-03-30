package com.wkr.task;

import UHF.Reader18;
import com.wkr.Tools.MyTools;
import com.wkr.bean.ReaderBean;
import com.wkr.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TimerTask {

    @Autowired
    ReaderService readerService;
    /**
     * 阅读器定时标签巡查任务
     */
    @Scheduled(cron = "0/1 * * * * ?")//每隔1秒隔行一次
    public void ReaderFetchTagTimerTask() {
        try{
            System.loadLibrary("UHF_Reader18");
            Reader18 reader18 = new Reader18();
            ReaderBean temp = new ReaderBean();
            temp.setReaderSetMAC(MyTools.getLocalMac());
            ReaderBean readerBean = readerService.fetchReaderByMAC(temp);
            if (readerBean != null) {//设置了阅读器
                int[] input = {readerBean.getReaderHEXAddr(), 5};
                int[] output= reader18.AutoOpenComPort(input);
                if (output[0] == 0){ //阅读器连接成功
                    //读写标签部分代码
                } else if (output[0] != 0 && readerBean.getIsOnline() != 0){//阅读器连接失败更新阅读器状态
                    readerService.updateReaderIsOnline(readerBean.getReaderSetMAC(), 0);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }


    }

}
