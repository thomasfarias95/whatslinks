# 📦 Projeto: Automação de Envio de Links no WhatsApp Web

Este projeto realiza a automação do envio de links de uma planilha Excel para um grupo no **WhatsApp Web**, utilizando **Java**, **Selenium WebDriver** e **Apache POI**.

---

## 🧰 Tecnologias Utilizadas

| Ferramenta     | Finalidade                             | Logo                                                  |
|----------------|-----------------------------------------|--------------------------------------------------------|
| Java 23        | Linguagem de programação principal     | ![Java](https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg) |
| Selenium       | Automação de navegador                 | ![Selenium](https://raw.githubusercontent.com/devicons/devicon/master/icons/selenium/selenium-original.svg) |
| Apache POI     | Leitura de arquivos Excel (.xlsx)      | ![Apache POI](https://poi.apache.org/images/project-logo.png) |
| ChromeDriver   | Driver do navegador Chrome             | ![Chrome](https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Google_Chrome_icon_%282011%29.png/600px-Google_Chrome_icon_%282011%29.png) |

---

## 📁 Estrutura do Projeto

com.example.achadosshoope
│
├── WhatsAppBoot.java # Classe principal que executa a automação
├── PlanilhasUtils.java # Utilitário que lê os links do Excel
├── enviados.txt # Arquivo que armazena os links já enviados
├── shoop.xlsx # Planilha com os links a serem enviados
└── webdrivers/
└── chromedriver.exe # Driver do Chrome


---

## 🚀 Como Usar

1. **Instale o Java 17+** e configure o `chromedriver.exe` compatível com seu Chrome.
2. Crie a planilha `shoop.xlsx` com os links na **coluna A (linha 1 para baixo)**.
3. Execute a classe `WhatsAppBoot.java`.
4. **Escaneie o QR Code** do WhatsApp Web com seu celular.
5. O bot enviará **até 20 links não repetidos** para o grupo "Achados".
6. Os links enviados serão registrados no arquivo `enviados.txt`.

---

## ✅ Funcionalidades

- ✅ Leitura da planilha Excel com Apache POI  
- ✅ Limite de **20 links por dia**
- ✅ Evita **repetição de links**
- ✅ Carrega a **prévia (descrição/imagem)** do link no WhatsApp
- ✅ Registro automático dos links já enviados

---

## 📷 Demonstração

### WhatsApp Web com bot ativo:

> *(Exemplo ilustrativo – substitua por screenshot real se quiser)*

![Exemplo WhatsApp Web](https://raw.githubusercontent.com/rafaelalmeidatk/imagens-readme/main/whatsapp-automation.png)

---

## ⚠️ Observações

- O envio de mensagens automatizadas no WhatsApp pode violar os **Termos de Uso do WhatsApp**. Use com responsabilidade.
- O seletor do WhatsApp Web pode mudar com o tempo — verifique o XPath se algo quebrar.
- Testado no Chrome **v138.0.7204.50** com ChromeDriver correspondente.

---

## 📄 Licença

MIT License © [thomasfarias95](https://github.com/thomasfarias95)

---

## ✉️ Contato

📧 Email: thomasfarias0995@gmail.com
🐙 GitHub: [thomasfarias95](https://github.com/thomasfarias95)
