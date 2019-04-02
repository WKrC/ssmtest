package com.wkr.Tools;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    public static String SixteenToTen(int[] input_Parameter) {
        StringBuffer result = new StringBuffer();
        for (int j = 0; j < input_Parameter.length; j++) {
            if (input_Parameter[j] >= 0 && input_Parameter[j] <= 9) {
                result.append("0" + input_Parameter[j]);
            }else {
                result.append(Long.toHexString(new Long(input_Parameter[j])));
            }
        }
        return result.toString().toUpperCase();
    }

    public static void log(Object o) {
        System.out.println(o);
    }

    public static String getDateString() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        return dateFormat.format(date);
    }

    public static String getIndexCode() {
        return (new Date().getTime() + "" + (int)(Math.random()*1000) + 100);//16位数
    }

    public static int[] StringToArray(String parameter) {
        int[] result = new int[8];
        int x = 0;
        char[] parameter_char = parameter.toCharArray();
        for (int i = 0; i < parameter_char.length; i += 2) {
            result[x] = Integer.valueOf(String.valueOf(parameter_char[i] + "" + parameter_char[i + 1]));
            x++;
        }
        return result;
    }

    public static String ArrayToString(Integer[] parameter) {
        StringBuffer result = new StringBuffer();
        for (int i = 1; i < parameter.length; i++) {
            result.append(parameter[i]);
        }
        return result.toString();
    }
}
