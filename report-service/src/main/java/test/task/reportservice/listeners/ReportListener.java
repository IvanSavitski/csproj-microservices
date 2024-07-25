package test.task.reportservice.listeners;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import test.task.reportservice.models.CountryReportKafkaEvent;
import test.task.reportservice.service.PdfApachePoiService;
import test.task.reportservice.service.XlsxApachePoiService;
import test.task.csproj.events.CountryKafkaEvent;

import java.util.Arrays;

@Service
@Slf4j
public class ReportListener {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private PdfApachePoiService pdfApachePoiService;

    @Autowired
    XlsxApachePoiService xlsxApachePoiService;

    @KafkaListener(topics = "countries_sending_topic", groupId = "report-group",
            properties = {"spring.json.value.default.type=test.task.csproj.events.CountryKafkaEvent"})

    public void consume(CountryKafkaEvent countriesEvent) {

        log.info("Getting event " + countriesEvent);

        try {
            if (countriesEvent.getType().equals("CountriesSending") && countriesEvent.getCountries() != null) {
                CountryReportKafkaEvent countriesReportEvent = modelMapper.map(countriesEvent, CountryReportKafkaEvent.class);

                try {
                    byte[] xlsxBytes = pdfApachePoiService.generateCountryReport(countriesReportEvent);
                    XlsxApachePoiService.PdfReportResponse pdfReportResponse = xlsxApachePoiService.generateCountryReport(countriesReportEvent);
                    byte[] pdfBytes = pdfReportResponse.getPdf();

                    log.info("Report service PDF_BYTES:\n" + Arrays.toString(pdfBytes));
                    log.info("Report service XLSX_BYTES:\n" + Arrays.toString(xlsxBytes));

                    sendFilesToAnotherService(pdfBytes, xlsxBytes);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendFilesToAnotherService(byte[] pdfBytes, byte[] xlsxBytes) {
        WebClient webClient = webClientBuilder.build();

        // Отправка PDF файла
        webClient.post()
                .uri("http://localhost:8080/api/email/pdf")
                .bodyValue(pdfBytes)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();

        // Отправка XLSX файла
        webClient.post()
                .uri("http://localhost:8080/api/email/xlsx")
                .bodyValue(xlsxBytes)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }
}