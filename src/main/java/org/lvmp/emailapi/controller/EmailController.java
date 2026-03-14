package org.lvmp.emailapi.controller;

import jakarta.validation.constraints.Email;
import org.lvmp.emailapi.dto.ForexData;
import org.lvmp.emailapi.dto.MarketUpdate;
import org.lvmp.emailapi.dto.StockData;
import org.lvmp.emailapi.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class EmailController {
    final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-market-update")
    public ResponseEntity<String> sendMarketUpdate(@RequestBody MarketUpdate marketUpdate, String recipient) {
        emailService.sendMarketUpdateEmail(recipient, marketUpdate.stocks(), marketUpdate.forex());

        return ResponseEntity.ok("Email queued successfully");
    }

}
