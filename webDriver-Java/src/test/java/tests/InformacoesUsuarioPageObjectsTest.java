package tests;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;

import static org.junit.Assert.*;
import static suport.Screenshot.take;

import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import suport.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioPageObjectsTest.csv")
public class InformacoesUsuarioPageObjectsTest {
    private String diretorio =  "";
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){
        navegador = Web.createChrome();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(
            @Param(name="login") String login,
            @Param(name="senha") String senha,
            @Param(name="tipo")String tipo,
            @Param(name="contato")String contato,
            @Param(name="mensagem")String mensagem){
        String textoToast = new LoginPage(navegador)
                .clicarSignIn()
                .fazerLogin(login, senha)
                .clicarMe()
                .clicarAbaMoreDataAboutYou()
                .clicarBotaoMoreDataAboutYou()
                .adicionarContato(tipo, contato)
                .capturarTextoToast();
        take(navegador, diretorio + test.getMethodName() + ".jpg");
        assertEquals(mensagem,textoToast);
    }

    @Test
    public void testRemoverUmContatoDeUmUsuario(
            @Param(name="login") String login,
            @Param(name="senha") String senha,
            @Param(name="contato")String contato) {
        String textToast = new LoginPage(navegador)
                .clicarSignIn()
                .fazerLogin(login, senha)
                .clicarMe()
                .clicarAbaMoreDataAboutYou()
                .excluirContato(contato)
                .capturarTextoToast();
        take(navegador, diretorio + test.getMethodName() + ".jpg");
        assertEquals("Rest in peace, dear phone!", textToast);
        //A mensagem tem virgula e o easytest considera como outra outra variavel no arquivo csv

        Web.aguardaToastSumir(navegador);
        Web.deslogaDoSite(navegador);
    }

    @After
    public void tearDown() {
        navegador.quit();
    }
}
