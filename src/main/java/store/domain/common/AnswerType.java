package store.domain.common;

public enum AnswerType {
    Y("Y"),
    N("N");

    private final String type;

    AnswerType(String type) {
        this.type = type;
    }

    public static boolean checkYes(String inputType) {
        if (Y.type.equals(inputType.toUpperCase())) {
            return true;
        } else if (N.type.equals(inputType.toUpperCase())) {
            return false;
        }
        throw new IllegalArgumentException("Y 또는 N만 입력 가능합니다.");
    }
}
