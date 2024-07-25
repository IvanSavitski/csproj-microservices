package test.task.emailservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import test.task.emailservice.service.EmailPdfServiceImpl;
import test.task.emailservice.service.EmailXlsxServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/email")
@Slf4j
public class EmailController {

    private final EmailXlsxServiceImpl emailXlsxServiceImpl;

    private final EmailPdfServiceImpl emailPdfServiceImpl;

    public EmailController(EmailXlsxServiceImpl emailXlsxServiceImpl,
                           EmailPdfServiceImpl emailPdfServiceImpl)
    {
        this.emailXlsxServiceImpl = emailXlsxServiceImpl;
        this.emailPdfServiceImpl = emailPdfServiceImpl;
    }

    @PostMapping("/pdf")
    public ResponseEntity<Void> handlePdfFile(@RequestBody byte[] pdfBytes) {

        log.info("Email service PDF_BYTES:\n" + Arrays.toString(pdfBytes));

        emailPdfServiceImpl.sendEmailWithAttachments(
                "ivanzoofficial@yandex.by",
                "Pdf report - all countries info",
                pdfBytes
        );
        return ResponseEntity.ok().build();
    }

    @PostMapping("/xlsx")
    public ResponseEntity<Void> handleXlsxFile(@RequestBody byte[] xlsxBytes) {

        log.info("Email service XLSX_BYTES:\n" + Arrays.toString(xlsxBytes));

        emailXlsxServiceImpl.sendEmailWithAttachments(
                "ivanzoofficial@yandex.by",
                "Xlsx report - all countries info",
                xlsxBytes
        );

        return ResponseEntity.ok().build();
    }
}