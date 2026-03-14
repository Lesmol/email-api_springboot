package org.lvmp.emailapi.dto;

import java.util.List;

public record MarketUpdate(List<StockData> stocks, ForexData forex) { }
