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
import java.util.Formatter;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Luca Capra <luca.capra@gmail.com>
 */
public class Debug {

    static Logger log = LoggerFactory.getLogger("dbus");

    public static final String DEBUG = "DEBUG";
    public static final String VERBOSE = "VERBOSE";
    public static final String ERR = "ERR";
    public static final String INFO = "INFO";
    public static final String WARN = "WARN";

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
            case Debug.WARN:
                log.warn(msg);
                break;
        }
    }

    static public void print(String level, Throwable err) {
        log.error(err.getMessage(), err);
    }
    
    static public void print(Throwable err) {
        print(err);
    }
    
    static public void print(Object m) {
        print(Debug.DEBUG, m);
    }

    static public void print(String level, byte[] msg) {
        print(level, toHex(msg));
    }

    static public void print(String msg) {
        print(Debug.DEBUG, msg);
    }

    static public void print(String level, Object object) {
        Class c = object.getClass();
        print(Debug.DEBUG, c.getCanonicalName());
    }

    public static String toAscii(byte[] m, int ofs, int width) {
        // todo: slice buff
        return toAscii(m);    
    }
    
    public static String toAscii(byte[] m) {
        return new String(m);    
    }
    
    public static String format(byte[] buf) {
        return toHex(buf);
    }
        
    public static String toHex(byte[] buf, int ofs, int width) {
        //todo: slice buf
        return toHex(buf);
    }
    
    
    
    public static String toHex(byte[] ba) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        for (int j = 1; j < ba.length + 1; j++) {
            if (j % 8 == 1 || j == 0) {
                if (j != 0) {
                    sb.append("\n");
                }
                formatter.format("0%d\t|\t", j / 8);
            }
            formatter.format("%02X", ba[j - 1]);
            if (j % 4 == 0) {
                sb.append(" ");
            }
        }
        sb.append("\n");
        return sb.toString();
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
