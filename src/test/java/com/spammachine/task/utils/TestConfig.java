package com.spammachine.task.utils;

import java.io.InputStream;
import java.util.Properties;

public class TestConfig {

    private String propertiesName = "test.properties";
    private static final TestConfig INSTANCE = new TestConfig();
    private Properties configs = new Properties();

    private TestConfig() {
        try {
            InputStream is = TestConfig.class.getClassLoader().getResourceAsStream(propertiesName);
            if (is == null) {
                throw new RuntimeException("Cannot execute tests with undefined configuration: " + propertiesName);
            }
            configs.load(is);
//            configs.entrySet();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getConfig(String key) {
        return configs.get(key).toString();
    }

    public static TestConfig getINSTANCE() {
        return new TestConfig();
    }
}
