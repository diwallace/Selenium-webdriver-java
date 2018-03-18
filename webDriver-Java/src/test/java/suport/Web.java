package suport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createChrome() {
        System.setProperty("webdriver.chrome.driver", "");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }

    public static void aguardaToastSumir(WebDriver navegador){
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(navegador.findElement(By.id("toast-container"))));
    }

    public static void deslogaDoSite(WebDriver navegador) {
        navegador.findElement(By.linkText("Logout")).click();
    }
}
