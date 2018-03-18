package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends BasePage {

    public MePage(WebDriver navegador) {
        super(navegador);
    }

    public MePage clicarAbaMoreDataAboutYou(){
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
        return this;
    }

    public AddContactPage clicarBotaoMoreDataAboutYou(){
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
        return new AddContactPage(navegador);
    }

    public MePage excluirContato(String contato){
        navegador.findElement(By.xpath("//span[text()=\"" + contato + "\"]/following-sibling::a")).click();
        navegador.switchTo().alert().accept();
        return this;
    }
}
