import com.example.achadosshoope.PlanilhasUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WhatsAppGuiApp extends JFrame {
    private static final String CAMINHO_PLANILHA = "C:\\Users\\thoma\\Desktop\\webdrivers\\shoop.xlsx";
    private static final String CAMINHO_REGISTRO = "links_enviados.txt";
    private static final String CHROME_DRIVER_PATH = "C:\\Users\\thoma\\Desktop\\webdrivers\\chromedriver.exe";

    private JTextArea logArea;
    private JButton iniciarBotao;
    private JTextField grupoField;

    public WhatsAppGuiApp() {
        setTitle("WhatsApp Sender");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Nome do Grupo:"));
        grupoField = new JTextField("Achados", 20);
        topPanel.add(grupoField);

        iniciarBotao = new JButton("Iniciar Envio");
        topPanel.add(iniciarBotao);

        add(topPanel, BorderLayout.NORTH);

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);

        iniciarBotao.addActionListener(e -> new Thread(this::executarEnvio).start());
    }

    private void log(String texto) {
        SwingUtilities.invokeLater(() -> logArea.append(texto + "\n"));
    }

    private void executarEnvio() {
        iniciarBotao.setEnabled(false);
        String nomeGrupo = grupoField.getText().trim();

        try {
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
            WebDriver driver = new ChromeDriver();

            driver.get("https://web.whatsapp.com");
            log("Escaneie o QR code no navegador do WhatsApp Web e pressione ENTER no console...");
            System.in.read();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement campoBusca = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@role='textbox' and @contenteditable='true']")));

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

                Thread.sleep(6000); // Espera carregar pré-visualização

                campoMensagem.sendKeys("\n");
                salvarLinkEnviado(link);

                log("Enviado: " + link);
                Thread.sleep(180000); // Espera 3 minutos entre envios (180000ms)
            }

            log("Todos os links foram enviados com sucesso!");
            // driver.quit(); // opcional
        } catch (Exception ex) {
            log("Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            iniciarBotao.setEnabled(true);
        }
    }

    private Set<String> carregarLinksEnviados() {
        Set<String> enviados = new HashSet<>();
        File file = new File(CAMINHO_REGISTRO);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    enviados.add(linha.trim());
                }
            } catch (IOException e) {
                log("Erro ao ler arquivo de links enviados");
            }
        }
        return enviados;
    }

    private void salvarLinkEnviado(String link) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_REGISTRO, true))) {
            bw.write(link);
            bw.newLine();
        } catch (IOException e) {
            log("Erro ao salvar link: " + link);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WhatsAppGuiApp().setVisible(true));
    }
}
