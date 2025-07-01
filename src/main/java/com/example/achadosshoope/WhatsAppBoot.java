import com.example.achadosshoope.PlanilhasUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.*;

public class WhatsAppBoot {

    private static final String CAMINHO_PLANILHA = "C:\\Users\\thoma\\Desktop\\webdrivers\\shoop.xlsx";
    private static final String CAMINHO_REGISTRO = "links_enviados.txt";

    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\thoma\\Desktop\\webdrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://web.whatsapp.com");
        System.out.println("Escaneie o QR code e pressione ENTER aqui...");
        System.in.read();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement campoBusca = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='textbox' and @contenteditable='true']")));

        String nomeGrupo = "Achados";

        campoBusca.click();
        campoBusca.clear();
        campoBusca.sendKeys(nomeGrupo);

        Thread.sleep(3000);

        WebElement grupo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@title='" + nomeGrupo + "']")));
        grupo.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pane-side")));

        Set<String> enviados = carregarLinksEnviados();
        List<String> todosLinks = PlanilhasUtils.lerLinks(CAMINHO_PLANILHA);

        List<String> linksParaEnviar = todosLinks.stream()
                .filter(link -> !enviados.contains(link))
                .limit(20)
                .toList();

        for (String link : linksParaEnviar) {
            WebElement campoMensagem = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@contenteditable='true' and @data-tab='10']")));

            campoMensagem.click();
            campoMensagem.sendKeys(link);

            WebDriverWait previewWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                // Aguarda a pré-visualização aparecer
                WebElement preview = previewWait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='link-preview']"))
                );

                // Agora aguarda a imagem da prévia aparecer dentro do preview
                WebDriverWait imageWait = new WebDriverWait(driver, Duration.ofSeconds(5));
                imageWait.until(driver1 ->
                        preview.findElements(By.xpath(".//img")).size() > 0 ||
                                preview.findElements(By.xpath(".//span[contains(@style, 'background-image')]")).size() > 0
                );

                System.out.println("Preview com imagem carregado!");
            } catch (Exception e) {
                System.out.println("Imagem do preview não carregou. Enviando mesmo assim...");
            }

            campoMensagem.sendKeys("\n");
            salvarLinkEnviado(link);
            Thread.sleep(1500);
        }

        System.out.println("Links enviados com sucesso!");
    }

    private static Set<String> carregarLinksEnviados() {
        Set<String> enviados = new HashSet<>();
        File file = new File(CAMINHO_REGISTRO);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    enviados.add(linha.trim());
                }
            } catch (IOException e) {
                System.err.println("Erro ao ler registro de links enviados.");
            }
        }
        return enviados;
    }

    private static void salvarLinkEnviado(String link) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_REGISTRO, true))) {
            bw.write(link);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar link enviado: " + link);
        }
    }
}
