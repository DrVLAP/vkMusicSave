package vkMusicSave;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by nikolaev-vi on 19.08.2016.
 */
public class TokenGrab {

    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "E:/IdeaProjects/vkMusicSave/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Test
    public void GivePermission() throws InterruptedException {
        AuthLoginAndPass loginAndPass = new AuthLoginAndPass();


        String tokengraburl = "https://oauth.vk.com/authorize?client_id=1&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&response_type=token&v=3.0";
        driver.get(tokengraburl);
        driver.findElement(By.xpath(".//*[@id='mcont']/div/div[2]/form/dl[1]/dd/div/input")).sendKeys(loginAndPass.getLogin());
        driver.findElement(By.xpath(".//*[@id='mcont']/div/div[2]/form/dl[2]/dd/div/input")).sendKeys(loginAndPass.getPassword());
        driver.findElement(By.className("button")).click();
        Thread.sleep(1000);
        driver.get(tokengraburl);
        driver.findElement(By.xpath(".//*[@id='mcont']/div/div[2]/form/div/input")).click();

        String tokenurl = driver.getCurrentUrl();
        String[] tokenurlparts = tokenurl.split("&");
        System.out.println(tokenurl);
        System.out.println(tokenurlparts[0]);

    }
}
