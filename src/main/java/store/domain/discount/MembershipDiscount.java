package store.domain.discount;

public class MembershipDiscount {
    private int discountPrice;

    public MembershipDiscount() {
        this.discountPrice = 0;
    }

    public void applyMembershipDiscount(int discountPrice){
        this.discountPrice = discountPrice;
    }

    public int getDiscountPrice(){
        return this.discountPrice;
    }

}
