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

    private String token;

    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "E:/IdeaProjects/vkMusicSave/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Test
    public void GivePermission() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:/IdeaProjects/vkMusicSave/chromedriver.exe");
        driver = new ChromeDriver();

        AuthLoginAndPass loginAndPass = new AuthLoginAndPass();


        String tokengraburl = "https://oauth.vk.com/authorize?client_id=1&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=audio&response_type=token&v=3.0&revoke=0";
        driver.get(tokengraburl);
        Thread.sleep(10000);
        System.out.println("Ввод логина и пароля");
        driver.findElement(By.xpath(".//*[@id='mcont']/div/div[2]/form/dl[1]/dd/div/input")).sendKeys(loginAndPass.getLogin());
        driver.findElement(By.xpath(".//*[@id='mcont']/div/div[2]/form/dl[2]/dd/div/input")).sendKeys(loginAndPass.getPassword());
        System.out.println("Логин и пароль введены");
        driver.findElement(By.className("button")).click();
        Thread.sleep(10000);
        System.out.println("Урл, на который произошел редирект");
        driver.get(driver.getCurrentUrl());
        Thread.sleep(10000);
        try {
            System.out.println("Клик по кнопке \"Разрешить\"");
            driver.findElement(By.xpath(".//*[@id='mcont']/div/div[2]/form/div/input")).click();
        } catch (Exception e) {
            getTokenFromUrl();
            return;
        }
        getTokenFromUrl();
    }

    public String getToken(){
        return token;
    }

    private void getTokenFromUrl(){
        String tokenurl = driver.getCurrentUrl();
        String[] tokenurlparts = tokenurl.split("&");
        System.out.println(tokenurl);
        System.out.println(tokenurlparts[0]);
        tokenurlparts = tokenurlparts[0].split("=");
        token = tokenurlparts[1];
        System.out.println(token);
    }

}
