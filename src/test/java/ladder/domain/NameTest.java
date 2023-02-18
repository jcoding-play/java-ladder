package ladder.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    void 글자5초과는_에러() {
        assertThatThrownBy(() -> new Name("123456"))
                .hasMessage("글자수가 5글자를 초과했습니다")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 글자5이하는_통과() {
        assertDoesNotThrow(() -> new Name("12345"));
    }

    @Test
    void 글자_0은_에러() {
        assertThatThrownBy(() -> new Name(""))
                .hasMessage("이름이 빈 문자열이 될 수 없습니다")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 글자_null_에러() {
        assertThatThrownBy(() -> new Name(null))
                .hasMessage("이름이 null이 되면 안됩니다")
                .isInstanceOf(IllegalArgumentException.class);
    }
}
