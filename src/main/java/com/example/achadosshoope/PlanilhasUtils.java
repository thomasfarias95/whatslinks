package com.example.achadosshoope;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class PlanilhasUtils {

    public static List<String> lerLinks(String caminhoArquivo) {
        List<String> links = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(caminhoArquivo);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    String link = getValorComoTexto(cell);
                    if (link != null && !link.trim().isEmpty()) {
                        links.add(link.trim());
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler planilha: " + e.getMessage());
        }

        return links;
    }

    private static String getValorComoTexto(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula(); // pode ajustar se quiser resultado da fórmula
            default -> null;
        };
    }
}
