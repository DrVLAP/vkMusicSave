package vkMusicSave;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by First on 27.08.2016.
 */
public class Run {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
//        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
//        driver = new ChromeDriver();
    }

    @Test
    public void Run() throws IOException, InterruptedException, ParseException {
        TokenGrab tokenGrab = new TokenGrab();
        tokenGrab.GivePermission(driver);
        tokenGrab.getToken();
        MusicGet musicGet = new MusicGet();
        musicGet.GetMusicCount(tokenGrab.getToken());
        musicGet.GetMusicList(tokenGrab.getToken());
        musicGet.GetMusicArrayForReport();
        musicGet.AudioSave();
        driver.close();
    }
}
