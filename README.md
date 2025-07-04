# 🤖 Projeto de Automação WhatsApp com Java

Este projeto é uma automação desenvolvida em **Java + Selenium** que realiza o envio automático de **links diários para grupos ou contatos no WhatsApp Web**, com controle de envio, visualização de imagem, e uma interface gráfica para facilitar a configuração.

---

## 🚀 Funcionalidades

- ✅ Leitura de links a partir de uma **planilha Excel (.xlsx)**
- ✅ Envio automático para grupos ou contatos do WhatsApp
- ✅ Evita envio repetido com base em registro `.txt`
- ✅ Limite de até **20 links por dia** configurável
- ✅ Tempo entre envios configurável (ex: a cada 3 minutos)
- ✅ Aguarda a imagem de pré-visualização antes de enviar
- ✅ Interface gráfica em **Swing** com opções:
  - Grupo/Contato de destino
  - Tempo de envio entre links
  - Número de links por dia

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Descrição |
|------------|-----------|
| ☕ **Java** | Linguagem principal do projeto |
| 🧪 **JUnit** | Para testes (caso aplicável) |
| 🧭 **Selenium WebDriver** | Para controle do navegador |
| 🌐 **WhatsApp Web** | Plataforma de envio |
| 📊 **Apache POI** | Leitura de planilhas Excel |
| 🖥️ **Java Swing** | Criação da interface gráfica |
| 🧠 **Lógica de automação** | Com controle de estado e persistência |

---

## 📸 Interface Gráfica

A interface gráfica foi feita com **Java Swing** e permite que você configure:

- Quantidade de links
- Intervalo entre envios
- Escolha entre **grupo** ou **contato**
- Botão para iniciar a automação após escanear o QR Code

*(adicione imagem do app aqui)*  
`Ex: ![Interface](imgs/interface.png)`

---



Instale o Chrome e baixe o ChromeDriver

Coloque o arquivo do ChromeDriver na pasta /webdrivers

Altere os caminhos dos arquivos (shoop.xlsx e chromedriver.exe) se necessário

Execute WhatsAppBoot.java ou use a interface com WhatsAppInterface.java

Escaneie o QR Code no WhatsApp Web

Assista a mágica acontecer! 🎯

📌 Observações
Certifique-se que o WhatsApp Web esteja visível no navegador.

A pré-visualização do link só carrega se o WhatsApp conseguir gerar a imagem.

Ainda em desenvolvimento a integração com Telegram.

💡 Próximos Passos
 Exportar logs de envios com data

 Adicionar suporte ao Telegram via Bot API

 Melhorias visuais com JavaFX (opcional)

🧑‍💻 Autor
Thomas Farias
Desenvolvedor e entusiasta em automação | LinkedIn: https://www.linkedin.com/in/thomas-farias-13b865169/
Projeto feito com dedicação e aprendizado prático.
