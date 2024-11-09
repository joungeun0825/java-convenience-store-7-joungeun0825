package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.domain.common.AnswerType;

public class AskCustomerInputView {
    private static final String ASK_TO_BUY_AGAIN = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";
    private static final String ASK_CUSTOMER_TO_GET_MORE = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    private static final String ASK_CUSTOMER_TO_KEEP_GOING = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    private static final String ASK_CUSTOMER_TO_APPLY_MEMBERSHIP = "멤버십 할인을 받으시겠습니까? (Y/N)";

    public static boolean askToBuyAgain() {
        try {
            System.out.println(ASK_TO_BUY_AGAIN);
            String answer = Console.readLine().trim().toUpperCase();
            return AnswerType.checkYes(answer);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            return askToBuyAgain();
        }
    }

    public static boolean askCustomerToGetMoreProduct(String name, int get) {
        try {
            System.out.println(String.format(ASK_CUSTOMER_TO_GET_MORE, name, get));
            String answer = Console.readLine().trim().toUpperCase();
            return AnswerType.checkYes(answer);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            return askCustomerToGetMoreProduct(name, get);
        }
    }

    public static boolean askCustomerToKeepGoing(String name, int notDiscountProduct) {
        try {
            System.out.println(String.format(ASK_CUSTOMER_TO_KEEP_GOING, name, notDiscountProduct));
            String answer = Console.readLine().trim().toUpperCase();
            return AnswerType.checkYes(answer);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            return askCustomerToGetMoreProduct(name, notDiscountProduct);
        }
    }

    public static boolean askCustomerToApplyMembership() {
        try {
            System.out.println(ASK_CUSTOMER_TO_APPLY_MEMBERSHIP);
            String answer = Console.readLine().trim().toUpperCase();
            return AnswerType.checkYes(answer);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            return askCustomerToApplyMembership();
        }
    }
}
