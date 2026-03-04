package tech.sjostrom.entsoeaiservice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DailySummary {
    private LocalDate date;
    private BigDecimal averageSekKwh;
    private BigDecimal averageSekKwhInclVat;
    private BigDecimal minSekKwh;
    private BigDecimal maxSekKwh;
    private List<PriceAnalysis> cheapestHours;
    private List<PriceAnalysis> expensiveHours;

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public BigDecimal getAverageSekKwh() { return averageSekKwh; }
    public void setAverageSekKwh(BigDecimal averageSekKwh) { this.averageSekKwh = averageSekKwh; }
    public BigDecimal getAverageSekKwhInclVat() { return averageSekKwhInclVat; }
    public void setAverageSekKwhInclVat(BigDecimal averageSekKwhInclVat) { this.averageSekKwhInclVat = averageSekKwhInclVat; }
    public BigDecimal getMinSekKwh() { return minSekKwh; }
    public void setMinSekKwh(BigDecimal minSekKwh) { this.minSekKwh = minSekKwh; }
    public BigDecimal getMaxSekKwh() { return maxSekKwh; }
    public void setMaxSekKwh(BigDecimal maxSekKwh) { this.maxSekKwh = maxSekKwh; }
    public List<PriceAnalysis> getCheapestHours() { return cheapestHours; }
    public void setCheapestHours(List<PriceAnalysis> cheapestHours) { this.cheapestHours = cheapestHours; }
    public List<PriceAnalysis> getExpensiveHours() { return expensiveHours; }
    public void setExpensiveHours(List<PriceAnalysis> expensiveHours) { this.expensiveHours = expensiveHours; }
}