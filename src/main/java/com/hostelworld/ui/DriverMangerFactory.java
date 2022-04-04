package com.hostelworld.ui;

public class DriverMangerFactory {

    public static DriverManager getManager(DriverTypes type) {
        DriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;
    }

}
