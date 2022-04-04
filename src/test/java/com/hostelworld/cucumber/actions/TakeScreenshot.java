package com.hostelworld.cucumber.actions;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeScreenshot {

    public static String timestamp() {

        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

    public static boolean takeScreen(WebDriver driver, String scenarioName) throws IOException {
        Screenshot myScreen = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1))
                .takeScreenshot(driver);
        BufferedImage ashotImage = myScreen.getImage();
        return ImageIO.write(ashotImage, "jpg", new File("target/screenshots/" + File.separator + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + File.separator + scenarioName + "/" + "_image_" + timestamp() + ".jpg"));
    }
}
