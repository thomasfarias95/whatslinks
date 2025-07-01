package com.example.achadosshoope;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ControleEnvioLinks {

    private static final String CAMINHO_ARQUIVO = "C:\\Users\\thoma\\Desktop\\webdrivers\\links_enviados.txt";

    // Carrega os links que já foram enviados
    public static Set<String> carregarLinksEnviados() {
        Set<String> enviados = new HashSet<>();

        try {
            if (Files.exists(Paths.get(CAMINHO_ARQUIVO))) {
                List<String> linhas = Files.readAllLines(Paths.get(CAMINHO_ARQUIVO));
                enviados.addAll(linhas);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de controle: " + e.getMessage());
        }

        return enviados;
    }

    // Salva novos links enviados ao final do arquivo
    public static void salvarLinksEnviados(List<String> novosLinks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {
            for (String link : novosLinks) {
                writer.write(link);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar links enviados: " + e.getMessage());
        }
    }
}
