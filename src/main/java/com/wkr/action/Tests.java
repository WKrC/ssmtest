package com.wkr.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.wkr.Tools.MyTools;
import org.apiguardian.api.API;
import org.junit.jupiter.api.Test;
import org.springframework.util.concurrent.ListenableFuture;

import javax.xml.ws.Response;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tests {
    private static final String BAIDU_APP_KEY = "qHKySMydjCE3dT5YtVuXUwZ82MG6m4PN";
    public static String getPublicIP() {
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 返回经纬度坐标的map longitude(经度),latitude(纬度)
     */
    public static Map<String, String> getLatitude(String address) {
        try {
            address = URLEncoder.encode(address, "UTF-8");
            URL resjson = new URL("http://api.map.baidu.com/geocoder?address="
                    + address + "&output=json&key=" + BAIDU_APP_KEY);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    resjson.openStream()));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            in.close();
            String str = sb.toString();
            if(str!=null&&!str.equals("")){
                Map<String, String> map = null;
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                    String longitude = str.substring(lngStart + 5, lngEnd);
                    String latitude = str.substring(lngEnd + 7, latEnd);
                    map = new HashMap<String, String>();
                    map.put("longitude", longitude);
                    map.put("latitude", latitude);
                    return map;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //百度
    public Object[] getCoordinate(String addr) throws IOException {
        String lng = null;//经度
        String lat = null;//纬度
        String address = null;
        try {
            address = java.net.URLEncoder.encode(addr, "UTF-8");
        }catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String key = "qHKySMydjCE3dT5YtVuXUwZ82MG6m4PN";
        String url = String .format("http://api.map.baidu.com/geocoder?address=%s&output=json&key=%s", address, key);
        URL myURL = null;
        URLConnection httpsConn = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
            if (httpsConn != null) {
                insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = null;
                int count = 1;
                while((data= br.readLine())!=null){
                    if(count==5){
                        lng = (String)data.subSequence(data.indexOf(":")+1, data.indexOf(","));//经度
                        count++;
                    }else if(count==6){
                        lat = data.substring(data.indexOf(":")+1);//纬度
                        count++;
                    }else{
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(insr!=null){
                insr.close();
            }
            if(br!=null){
                br.close();
            }
        }
        return new Object[]{lng,lat};
    }
//    private static String getLocalMac(InetAddress ia) throws SocketException {
//        //获取网卡，获取地址
//        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
//        StringBuffer sb = new StringBuffer("");
//        for(int i=0; i<mac.length; i++) {
//            if(i!=0) {
//                sb.append("-");
//            }
//            //字节转换为整数
//            int temp = mac[i]&0xff;
//            String str = Integer.toHexString(temp);
//            if(str.length()==1) {
//                sb.append("0"+str);
//            }else {
//                sb.append(str);
//            }
//        }
//        System.out.println("本机MAC地址:"+sb.toString().toUpperCase());
//        return sb.toString().toUpperCase();
//    }

    @Test
    public void getThis(){
        try {
            Object[] o = getCoordinate("北京市昌平区龙泽苑东区25号楼");
            System.out.println(o[0]);//经度
            System.out.println(o[1]);//纬度
            System.out.println("--------------------------");
            MyTools myTools = new MyTools();
            myTools.getLocalMac();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}