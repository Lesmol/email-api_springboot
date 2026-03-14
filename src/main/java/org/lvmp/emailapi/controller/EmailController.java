package org.lvmp.emailapi.controller;

import org.lvmp.emailapi.dto.MarketUpdateRequest;
import org.lvmp.emailapi.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class EmailController {
    final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-market-update")
    public ResponseEntity<Void> sendMarketUpdate(@RequestBody MarketUpdateRequest marketUpdate) {
        return emailService.sendMarketUpdateEmail(marketUpdate.recipients(), marketUpdate.stocks(), marketUpdate.forex());
    }

}
