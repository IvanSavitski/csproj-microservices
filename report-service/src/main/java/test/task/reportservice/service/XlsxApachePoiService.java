package test.task.reportservice.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import test.task.reportservice.models.CountryDTO;
import test.task.reportservice.models.CountryReportKafkaEvent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class XlsxApachePoiService {

    public PdfReportResponse generateCountryReport(CountryReportKafkaEvent countriesEvent) throws IOException {
        List<CountryDTO> countries = countriesEvent.getCountries();
        System.out.println(countries);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Countries");

        Row headerRow = sheet.createRow(0);
        String[] headers = {"Country Name", "Country Languages", "Languages Count", "Is Capital Population Bigger",
                "Is English Language", "Country Population", "Capital Population", "Non Capital Population"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);

        }

        int rowNum = 1;
        for (CountryDTO country : countries) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(country.getCountryName());
            System.out.println(country.getCountryName());
            row.createCell(1).setCellValue(String.join(", ", country.getCountryLanguagesList()));
            row.createCell(2).setCellValue(country.getLanguagesCount());
            row.createCell(3).setCellValue(country.isCapitalPopulationBigger());
            row.createCell(4).setCellValue(country.isEnglishLanguage());
            row.createCell(5).setCellValue(country.getCountryPopulation());
            row.createCell(6).setCellValue(country.getCapitalPopulation());
            row.createCell(7).setCellValue(country.getNonCapitalPopulation());
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        byte[] pdfBytes = bos.toByteArray();
        workbook.close();

        return new PdfReportResponse("PDF generated successfully", countries, pdfBytes);
    }

    public static class PdfReportResponse {
        private String message;

        private List<CountryDTO> countries;
        private byte[] pdf;

        public PdfReportResponse(String message, List<CountryDTO> countries, byte[] pdf) {
            this.message = message;
            this.countries = countries;
            this.pdf = pdf;
        }

        public String getMessage() {
            return message;
        }
        public List<CountryDTO> getCountries() {
            return countries;
        }
        public byte[] getPdf() {
            return pdf;
        }
    }
}