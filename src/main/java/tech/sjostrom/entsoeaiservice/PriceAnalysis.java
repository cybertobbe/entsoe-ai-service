package tech.sjostrom.entsoeaiservice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceAnalysis {
    private LocalDateTime timestamp;
    private BigDecimal priceEurMwh;
    private BigDecimal priceSekKwh;
    private BigDecimal priceSekKwhInclVat;

    public LocalDateTime getTimestamp() { return timestamp; }
    public BigDecimal getPriceEurMwh() { return priceEurMwh; }
    public BigDecimal getPriceSekKwh() { return priceSekKwh; }
    public BigDecimal getPriceSekKwhInclVat() { return priceSekKwhInclVat; }

    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setPriceEurMwh(BigDecimal priceEurMwh) { this.priceEurMwh = priceEurMwh; }
    public void setPriceSekKwh(BigDecimal priceSekKwh) { this.priceSekKwh = priceSekKwh; }
    public void setPriceSekKwhInclVat(BigDecimal priceSekKwhInclVat) { this.priceSekKwhInclVat = priceSekKwhInclVat; }
}