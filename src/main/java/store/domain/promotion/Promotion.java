package store.domain.promotion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Promotion {
    private String name;
    private int buy;
    private int get;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Promotion(String name, int buy, int get, String startDate, String endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = convertToLocalDate(startDate);
        this.endDate = convertToLocalDate(endDate);
    }

    public boolean isWithinPromotionPeriod(LocalDateTime today) {
        return !today.isBefore(startDate) && !today.isAfter(endDate);
    }

    public int calculateTotalPromotionQuantity(int quantity) {
        return quantity * (buy + get);
    }

    public int calculateDiscountQuantity(int purchaseQuantity) {
        return purchaseQuantity / (buy + get);
    }

    public String getName() {
        return this.name;
    }

    public int getBuy() {
        return this.buy;
    }

    public int getGet() {
        return this.get;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.startDate;
    }

    private LocalDateTime convertToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter).atStartOfDay();
    }
}
