package com.hostelworld.junit.utils;

import com.hostelworld.junit.exceptions.CustomTestException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class FileUtils {

    public static String readResource(Class klass, String path) {
        StringWriter sw = new StringWriter();
        InputStream is = klass.getClassLoader().getResourceAsStream(path);
        try {
            IOUtils.copy(is, sw, "UTF-8");
            return sw.toString();
        } catch (IOException ioe) {
            throw new CustomTestException("Could not read file " + path);
        }
    }

    public static String readResource(final String resourcePath) {
        return readResource(FileUtils.class, resourcePath);
    }

}
