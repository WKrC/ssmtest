package com.wkr.Tools;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

public class MyTools {
    public static String getLocalMac() throws Exception {
        InetAddress ia = InetAddress.getLocalHost();
        //获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer("");
        for(int i=0; i<mac.length; i++) {
            if(i!=0) {
                sb.append("-");
            }
            //字节转换为整数
            int temp = mac[i]&0xff;
            String str = Integer.toHexString(temp);
            if(str.length()==1) {
                sb.append("0"+str);
            }else {
                sb.append(str);
            }
        }
        return sb.toString().toUpperCase();
    }

    public static String TenToSixteen(Long indexCode) {
        StringBuffer result = new StringBuffer();
        String indexCode_String = indexCode.toString();
        char[] indexCode_char = indexCode_String.toCharArray();
        for (int j = 0; j < indexCode_char.length; j += 2) {
            result.append(Long.toHexString(new Long(String.valueOf(indexCode_char[j]) + String.valueOf(indexCode_char[j + 1]))));
        }
        return result.toString().toUpperCase();
    }

    public static void log(Object o) {
        System.out.print(o + " ");
    }
}
