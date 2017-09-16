/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.freedesktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Luca Capra <luca.capra@gmail.com>
 */
public class Debug {

    static Logger log = LoggerFactory.getLogger("dbus");

    public static final String DEBUG = "debug";
    public static final String VERBOSE = "verbose";
    public static final String ERR = "error";
    public static final String INFO = "info";

    public static boolean debug = true;

    static public void print(String level, String msg) {
        switch (level) {
            case Debug.VERBOSE:
                log.trace(msg);
                break;
            case Debug.DEBUG:
                log.debug(msg);
                break;
            case Debug.ERR:
                log.error(msg);
                break;
            case Debug.INFO:
                log.info(msg);
                break;
        }
    }

    static public void print(String level, Throwable err) {
        log.error(err.getMessage(), err);
    }

    static public void print(String msg) {
        print(Debug.DEBUG, msg);
    }

    static public void setThrowableTraces(boolean flag) {
        // todo
    }
    
    static public void setHexDump(boolean flag) {
        // todo
    }

    static public Properties loadConfig(File file) throws IOException {
        Properties prop = new Properties();
        InputStream input = new FileInputStream(file);
        prop.load(input);
        return prop;
    }

}
