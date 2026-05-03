package org.lvmp.emailapi.model;

import lombok.Builder;

import java.util.Map;

@Builder
public record EmailRequest(String to, String subject, String templateName, Map<String, Object> variables) { }
