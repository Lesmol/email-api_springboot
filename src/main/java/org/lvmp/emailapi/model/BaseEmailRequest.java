package org.lvmp.emailapi.model;

import lombok.Getter;

import java.util.List;

@Getter
public class BaseEmailRequest {
    protected List<String> recipients;

    public BaseEmailRequest() {
    }

    public BaseEmailRequest(List<String> recipients) {
        this.recipients = recipients;
    }
}
