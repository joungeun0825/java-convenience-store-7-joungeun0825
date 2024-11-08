package store.domain.promotion;

public enum TotalPromotion {
    탄산2_1("탄산2+1",new Promotion("탄산2+1")),
    MD추천상품("MD추천상품", new Promotion("MD추천상품")),
    반짝할인("반짝할인", new Promotion("반짝할인"));

    private final String displayName;
    private final Promotion promotion;

    TotalPromotion(String displayName, Promotion promotion){
        this.displayName = displayName;
        this.promotion = promotion;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public static TotalPromotion fromDisplayName(String displayName) {
        for (TotalPromotion promotion : TotalPromotion.values()) {
            if (promotion.getDisplayName().equals(displayName)) {
                return promotion;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 프로모션 이름입니다: " + displayName);
    }

}
