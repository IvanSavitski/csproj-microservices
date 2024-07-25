package test.task.reportservice.service;

import com.itextpdf.text.Element;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.awt.Color;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import test.task.reportservice.models.CountryDTO;
import test.task.reportservice.models.CountryReportKafkaEvent;


@Service
public class PdfApachePoiService {

    public byte[] generateCountryReport(CountryReportKafkaEvent countriesEvent) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        List<CountryDTO> countries = countriesEvent.getCountries();
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, baos);

            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(18);
            font.setColor(Color.BLUE);

            Paragraph p = new Paragraph("Country Details", font);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            PdfPTable table = new PdfPTable(9); // Количество колонок
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{2.5f, 2.5f, 1.5f, 2.0f, 2.0f, 2.5f, 2.5f, 2.5f, 2.5f});
            table.setSpacingBefore(10);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(Color.BLUE);
            cell.setPadding(5);

            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA);
            headerFont.setColor(Color.WHITE);

            cell.setPhrase(new Phrase("Country Name", headerFont));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Country Languages", headerFont));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Country Languages List", headerFont));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Languages Count", headerFont));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Is Capital Population Bigger", headerFont));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Is English Language", headerFont));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Country Population", headerFont));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Capital Population", headerFont));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Non Capital Population", headerFont));
            table.addCell(cell);

            for (CountryDTO country : countries) {
                table.addCell(country.getCountryName());

                table.addCell(String.valueOf(country.getCountryLanguages()));
                table.addCell(String.join(", ", country.getCountryLanguagesList()));
                table.addCell(String.valueOf(country.getLanguagesCount()));
                table.addCell(String.valueOf(country.isCapitalPopulationBigger()));
                table.addCell(String.valueOf(country.isEnglishLanguage()));
                table.addCell(String.valueOf(country.getCountryPopulation()));
                table.addCell(String.valueOf(country.getCapitalPopulation()));
                table.addCell(String.valueOf(country.getNonCapitalPopulation()));
            }

            document.add(table);
            document.close();
            return baos.toByteArray();
        } catch (
            com.lowagie.text.DocumentException e) {
            e.printStackTrace();
            return null;
        } finally {
            document.close();
        }
    }
}