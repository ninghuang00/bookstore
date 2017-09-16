package cn.hn.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by huangning on 2017/9/12.
 */
public class Log {
    private static Logger logger = Logger.getLogger("hn");
    static{
        logger.setLevel(Level.INFO);
    }
    public static void loggerInfo(String info){
        logger.info(" \r\n###My log info is ### ===>  " + info);

    }
   /* public static void loggerInfo(boolean info){
        logger.info("My log info is ===> " + info);

    }*/

}
