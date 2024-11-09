package store.domain.promotion;

import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Promotion {
    private String name;
    private int buy;
    private int get;
    private String startDate;
    private String endDate;

    public Promotion(String name){
        this.name = name;
    }

    public void updatePromotion(int buy, int get, String startDate, String endDate) {
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isWithinPromotionPeriod() {
        LocalDateTime today = DateTimes.now();
        return !today.isBefore(convertToLocalDate(startDate)) && !today.isAfter(convertToLocalDate(endDate));
    }

    public int calculateTotalPromotionQuantity(int quantity) {
        return quantity * (buy + get);
    }

    public int calculateDiscountQuantity(int purchaseQuantity) {
        return purchaseQuantity / (buy + get);
    }

    public int calculateRealDiscountQuantity(int leftQuantity) {
        return leftQuantity / (buy + get);
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

    private LocalDateTime convertToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter).atStartOfDay();
    }
}
