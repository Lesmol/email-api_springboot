package org.lvmp.emailapi.dto;

import java.util.List;

public record MarketUpdateRequest(List<StockData> stocks, ForexData forex, List<String> recipients) { }
