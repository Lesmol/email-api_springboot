package org.lvmp.emailapi.service;

import org.lvmp.emailapi.dto.EmailRequest;
import org.lvmp.emailapi.dto.ForexData;
import org.lvmp.emailapi.dto.StockData;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import software.amazon.awssdk.services.sesv2.model.SendEmailRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    private final SesV2Client sesClient;
    private final TemplateEngine templateEngine;

    public EmailService(SesV2Client sesClient, TemplateEngine templateEngine) {
        this.sesClient = sesClient;
        this.templateEngine = templateEngine;
    }

    public void sendMarketUpdateEmail(String recipient, List<StockData> stocks, ForexData forex) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("title", "Weekly Market Insights");
        variables.put("stocks", stocks);
        variables.put("forex", forex);

        EmailRequest request = new EmailRequest(
                recipient,
                "📊 Market Update",
                "market-report",
                variables
        );

        this.sendTemplateEmail(request);
    }

    public void sendTemplateEmail(EmailRequest request) {
        Context context = new Context();
        if (request.variables() != null) {
            context.setVariables(request.variables());
        }

        String htmlBody = templateEngine.process(request.templateName(), context);

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .fromEmailAddress("lesedi@lvmolemi.com")
                .destination(d -> d.toAddresses(request.to()))
                .content(c -> c.simple(m -> m
                        .subject(s -> s.data(request.subject()).charset("UTF-8"))
                        .body(b -> b.html(h -> h.data(htmlBody).charset("UTF-8")))
                ))
                .build();

        try {
            sesClient.sendEmail(sendEmailRequest);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email via SES: " + e.getMessage(), e);
        }
    }
}