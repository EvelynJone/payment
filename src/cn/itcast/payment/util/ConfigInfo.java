package cn.itcast.payment.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2016/11/1.
 */
public class ConfigInfo {
    private static Properties cache = new Properties();

    static {
        try {
            cache.load(ConfigInfo.class.getClassLoader().getResourceAsStream("merchantInfo.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return cache.getProperty(key);
    }
}
