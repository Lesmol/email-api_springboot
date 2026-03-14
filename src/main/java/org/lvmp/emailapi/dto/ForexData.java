package org.lvmp.emailapi.dto;

import java.math.BigDecimal;

public record ForexData(String ticker, BigDecimal open, BigDecimal openingPrice) { }
