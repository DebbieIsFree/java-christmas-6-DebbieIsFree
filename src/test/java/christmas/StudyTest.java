package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class StudyTest extends NsTest{
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 주문_예외_테스트_음수_수량() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라--1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 할인_금액_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<총혜택 금액>" + LINE_SEPARATOR + "0원");
        });
    }

    @Test
    void 할인_금액_있음_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("<총혜택 금액>" + LINE_SEPARATOR + "-31,246원");
        });
    }

    @Test
    void 증정_메뉴_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<증정 메뉴>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 중복_메뉴_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-1,아이스크림-1,아이스크림-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 없는_메뉴_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "크림불고기파스타-2,레드와인-1,초코케이크-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주말_할인_테스트() {
        assertSimpleTest(() -> {
            runException("2", "해산물파스타-1,초코케이크-1");
            assertThat(output()).contains("<총혜택 금액>" + LINE_SEPARATOR + "-3,123원");
        });
    }

    @Test
    void 평일_할인_테스트() {
        assertSimpleTest(() -> {
            runException("8", "해산물파스타-1,초코케이크-1");
            assertThat(output()).contains("<총혜택 금액>" + LINE_SEPARATOR + "-3,723원");
        });
    }

    @Test
    void 메뉴_형식_에러_테스트_1() {
        assertSimpleTest(() -> {
            runException("3", "바비큐립-1,레드와인-1초코케이크-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴_형식_에러_테스트_2() {
        assertSimpleTest(() -> {
            runException("3", "바비큐립-1,레드와인-1,초코케이크-1,");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
