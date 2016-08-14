package vkMusicSave;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by First on 13.08.2016.
 */
public class vkMusicSave {

    public WebDriver driver;
    private AuthLoginAndPass authLoginAndPassword = new AuthLoginAndPass();
    public List<WebElement> musicElementList;
    public List<String> musicList;
    public Integer musicElementIndex;

    @BeforeMethod
    public void setUp(){
        driver = new FirefoxDriver();
    }

    @Test
    public void  musicSave() throws InterruptedException {
        System.out.println("Process started");
        openPage();
        //authLoginAndPassword.setAuthLoginAndPath();
        authorization();
        System.out.println("Authorized;");
        musicTabLogick();
        makeMusicList();
    }

    public void openPage(){
        System.out.println("Opening browser;");
        driver.manage().window();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        System.out.println("Browser opened;");
        System.out.println("Open vk.com;");
        driver.get("https://new.vk.com/");
        System.out.println("vk.com opened;");
    }

    public void authorization(){
        driver.findElement(By.id("index_email")).sendKeys(authLoginAndPassword.getLogin());
        System.out.println("Login entered;");
        driver.findElement(By.id("index_pass")).sendKeys(authLoginAndPassword.getPassword());
        System.out.println("Password entered;");
        driver.findElement(By.id("index_login_button")).click();
    }

    public void musicTabLogick() throws InterruptedException {
        driver.findElement(By.id("l_aud")).click();
        System.out.println("Music tab opened;");
        Thread.sleep(1500);
        musicElementList = driver.findElements(By.cssSelector("*[class^='audio_row']"));
        for (int m = 1; m < musicElementList.size(); m++){
            System.out.println(musicElementList.get(m).getAttribute("data-audio"));
//            musicList.add(musicElementList.get(m+1).getAttribute("data-audio"));
        }
    }

    public void makeMusicList() {
        for (int m = 1; m < musicElementList.size(); m++) {
            String[] musicData = musicElementList.get(m).getAttribute("data-audio").split(",");
            System.out.println("Исполнитель: " + musicData[4]);
            System.out.println("Композиция: " + musicData[3]);
            System.out.println("------------------------------");
            musicList.add(musicData[4]+": "+musicData[3]);
            System.out.println("------------------------------");
        }
        for (String compositionId: musicList){
            System.out.println(musicList.get(Integer.parseInt(compositionId)));
        }
    }

}
