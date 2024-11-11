package store.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.domain.discount.MembershipDiscount;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MembershipDiscountTest {
    @Test
    @DisplayName("생성 시 초기 할인 가격이 0인지 확인")
    void testInitialDiscountPrice() {
        MembershipDiscount membershipDiscount = new MembershipDiscount();

        assertEquals(0, membershipDiscount.getDiscountPrice(), "초기 할인 가격이 0이 아닙니다.");
    }

    @Test
    @DisplayName("applyMembershipDiscount로 할인 가격이 올바르게 적용되는지 테스트")
    void testApplyMembershipDiscount() {
        MembershipDiscount membershipDiscount = new MembershipDiscount();

        int discountPrice = 500;
        membershipDiscount.applyMembershipDiscount(discountPrice);

        assertEquals(discountPrice, membershipDiscount.getDiscountPrice(), "할인 가격이 올바르게 적용되지 않았습니다.");
    }
}
