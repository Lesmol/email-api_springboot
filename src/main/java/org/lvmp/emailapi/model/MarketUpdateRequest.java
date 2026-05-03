package org.lvmp.emailapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MarketUpdateRequest extends BaseEmailRequest {
    private List<StockData> stocks;
    private ForexData forex;

    public MarketUpdateRequest() {
        super();
    }

    public MarketUpdateRequest(List<StockData> stocks, ForexData forex, List<String> recipients) {
        super(recipients);
        this.stocks = stocks;
        this.forex = forex;
    }
}
