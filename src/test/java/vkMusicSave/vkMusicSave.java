package vkMusicSave;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by First on 13.08.2016.
 */
public class vkMusicSave {

    public WebDriver driver;
    private AuthLoginAndPass authLoginAndPassword = new AuthLoginAndPass();
    public List<WebElement> musicElementList;
    public ArrayList<String> musicList = new ArrayList<String>();
    public Integer musicElementIndex;


    @BeforeMethod
    public void setUp(){
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "E:/IdeaProjects/vkMusicSave/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void  musicSave() throws InterruptedException, IOException {
        System.out.println("Process started");
        openPage();
        //authLoginAndPassword.setAuthLoginAndPath();
        authorization();
        System.out.println("Authorized;");

        musicTabLogick();
        scrollingToTheEnd();
        makeMusicList();
        driver.close();
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
        Thread.sleep(1500);
        driver.findElement(By.id("l_aud")).click();
        System.out.println("Music tab opened;");
        Thread.sleep(1500);

    }

    public void makeMusicList() throws IOException {
        musicElementList = driver.findElements(By.cssSelector("*[class^='audio_row']"));
        for (int m = 1; m < musicElementList.size(); m++) {
            String[] musicData = musicElementList.get(m).getAttribute("data-audio").split("\",");
//            System.out.println("Исполнитель: " + musicData[4]);
//            System.out.println("Композиция: " + musicData[3]);
//            System.out.println("------------------------------");
//            System.out.println(musicList.size());
            musicList.add( musicData[4].replaceAll("\"", "")+": "+musicData[3].replaceAll("\"", ""));
//            System.out.println("------------------------------");
        }
        System.out.println("----------------------------------");
        System.out.println("Список композиций:");
        FileWriter writer = new FileWriter("C:/vkmusic.txt", true);
        for (String compositionId: musicList){

            System.out.println(compositionId.replaceAll("&#039;", "'").replaceAll("&amp;","&").replaceAll("&quot;","\""));
            //TODO Следующие две строки не работают. Допилить запись в файл
            writer.write(compositionId.replaceAll("&#039;", "'").replaceAll("&amp;","&").replaceAll("&quot;","\"")+"\n\r");
            writer.write("\n");
            writer.flush();
        }
    }
    //TODO scrolling music method
    public void scrollingToTheEnd() throws InterruptedException {
        System.out.println("Start scrolling;");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        List<WebElement> tempMusicElementList = null;
        int c = 1;
        for (int i = 0; i<c; i++) {
            musicElementList = driver.findElements(By.cssSelector("*[class^='audio_row']"));
            jse.executeScript("scroll(0, "+(c*2250)+");");
            System.out.println(c*2250);
            Thread.sleep(5000);
            tempMusicElementList = driver.findElements(By.cssSelector("*[class^='audio_row']"));
            System.out.println("Размер musicElementList = "+musicElementList.size());
            System.out.println("Размер tempMusicElementList = "+tempMusicElementList.size());
            System.out.println("-----------------------------");
            if (musicElementList.size() < tempMusicElementList.size()){
                c++;
            }
            else
            {
                System.out.println("Reached end of music list;");
                return;
            }
        }
    }

    //TODO music saving method
    public void musicSaving(){

    }
}
