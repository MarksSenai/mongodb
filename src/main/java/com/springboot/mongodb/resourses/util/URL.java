package com.springboot.mongodb.resourses.util;

import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLDecoder;

public class URL {
    public static String deCodeParam(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}