package ladder.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PlayResultTest {

    @ParameterizedTest
    @CsvSource(value = {"pobi, true", "all, true", "nono, false"})
    @DisplayName("참여자 이름에 해당하는 결과가 존재하는지 판별할 수 있다.")
    void hasResultOf(String name, boolean expected) {
        PlayResult playResult = new PlayResult(Map.of("pobi", "5000"));
        boolean actual = playResult.hasResultOf(name);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"pobi, 5000", "honux, 3000", "crong, 꽝", "jk, 꽝"})
    @DisplayName("참여자 이름에 해당하는 결과를 얻을 수 있다.")
    void checkPlayerResultByName(String name, String expected) {
        PlayResult playResult = new PlayResult(Map.of(
                "pobi", "5000",
                "crong", "꽝",
                "honux", "3000",
                "jk", "꽝"));
        Map<String, String> result = playResult.checkPlayerResultByName(name);
        String actual = result.get(name);

        assertThat(actual).isEqualTo(expected);
    }
}
