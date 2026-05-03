package org.lvmp.emailapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lvmp.emailapi.excpetion.SendEmailException;
import org.lvmp.emailapi.model.EmailRequest;
import org.lvmp.emailapi.model.ForexData;
import org.lvmp.emailapi.model.StockData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import software.amazon.awssdk.services.sesv2.model.SendEmailRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final SesV2Client sesClient;
    private final TemplateEngine templateEngine;

    public ResponseEntity<Void> sendMarketUpdateEmail(List<String> recipients, List<StockData> stocks, ForexData forex) {
        log.info("Sending market update email to: {}", recipients);
        Map<String, Object> variables = new HashMap<>();
        variables.put("title", "Weekly Market Insights");
        variables.put("stocks", stocks);
        variables.put("forex", forex);

        for (String recipient : recipients) {
            EmailRequest request = new EmailRequest(
                    recipient,
                    "📊 Market Update",
                    "market-report",
                    variables
            );

            try {
            this.sendTemplateEmail(request);
            } catch (SendEmailException e) {
                log.error("Failed to send to {} : {}", recipient, e.getMessage());
            }
        }

        log.info("Successfully sent email(s).");
        return ResponseEntity.ok().build();
    }

    private void sendTemplateEmail(EmailRequest request) {
        Context context = new Context();

        if (request.variables() == null) {
            throw new SendEmailException("Variables cannot be null.");
        }

        context.setVariables(request.variables());

        try {
            String htmlBody = templateEngine.process(request.templateName(), context);

            SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                    .fromEmailAddress("updates@lvmolemi.com")
                    .destination(d -> d.toAddresses(request.to()))
                    .content(c -> c.simple(m -> m
                            .subject(s -> s.data(request.subject()).charset("UTF-8"))
                            .body(b -> b.html(h -> h.data(htmlBody).charset("UTF-8")))
                    ))
                    .build();

            sesClient.sendEmail(sendEmailRequest);
        } catch (Exception e) {
            throw new SendEmailException("Failed to process template or send email to " + request.to(), e);
        }
    }
}