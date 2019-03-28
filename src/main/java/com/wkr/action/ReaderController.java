package com.wkr.action;

import com.wkr.Tools.MyTools;
import com.wkr.bean.ReaderBean;
import com.wkr.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReaderController {

    @Autowired
    ReaderService readerService;

    /**
     * 查找阅读器
     * @return
     */
    @RequestMapping(value = "/fetchReaderControl", method = RequestMethod.POST)
    @ResponseBody
    public ReaderBean fetchReaderControl() {
        ReaderBean readerBean = new ReaderBean();
        try {
            readerBean.setReaderSetMAC(MyTools.getLocalMac());
        }catch (Exception e){
            e.getStackTrace();
        }
        ReaderBean readerResult = readerService.fetchReaderByMAC(readerBean);
        if (readerResult == null) {
            return null;
        }else {
            return readerResult;
        }

    }

    /**
     * 保存或更新阅读器设置
     * @param readerBean 阅读器实体类对象
     * @return 结果码
     */
    @RequestMapping(value = "saveOrUpdateReaderSettingController", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveOrUpdateReaderSettingController(ReaderBean readerBean) {
        Map<String, Object> result = new HashMap<>();
        try{
            ReaderBean temp = new ReaderBean();//临时参数作为后面数据库的查找参数
            temp.setReaderSetMAC(MyTools.getLocalMac());
            ReaderBean rb = readerService.fetchReaderByMAC(temp);//包含本机MAC的阅读器对象
            if (rb != null) {//更新设置
                /*readerBean.setReaderSetMAC(rb.getReaderSetMAC());
                readerBean.setReaderHEXAddr(rb.getReaderHEXAddr());
                readerService.updateReaderSetting(readerBean);*/
                //错误模板 两个对象主键相同 hibernate是不允许两个主键相同的对象在同一个session里面
                //同一个session一般指同一个方法内
                /**
                 * 以下为正确模板
                 */
                rb.setReaderAddress(readerBean.getReaderAddress());
                rb.setReaderName(readerBean.getReaderName());
                rb.setReaderGPS(readerBean.getReaderGPS());
                readerService.updateReaderSetting(rb);
                result.put("resultCode", 2);
            }else {//保存设置
                List<ReaderBean> readerBeanList =  readerService.getTotalCount();
                readerBean.setReaderHEXAddr(readerBeanList.size() + 1);
                readerBean.setReaderSetMAC(MyTools.getLocalMac());
                readerService.saveReaderSetting(readerBean);
                result.put("resultCode", 1);
            }
        }catch (Exception e) {
            result.put("resultCode", -1);
            e.printStackTrace();
        }
        return result;
    }
}
