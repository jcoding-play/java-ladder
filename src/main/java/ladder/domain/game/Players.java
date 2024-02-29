package ladder.domain.game;

import ladder.domain.Direction;
import ladder.domain.ladder.Ladder;
import ladder.domain.ladder.Line;
import ladder.domain.ladder.Rung;
import ladder.domain.player.Player;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Players {
    private static final int MINIMUM_PLAYER_SIZE = 2;
    private static final int MAXIMUM_PLAYER_SIZE = 10;

    private final List<Player> players;

    public Players(final List<String> playerNames) {
        validatePlayers(playerNames);
        this.players = mapPlayer(playerNames);
    }

    private List<Player> mapPlayer(final List<String> playerNames) {
        return IntStream.range(0, playerNames.size())
                .mapToObj(index -> generatePlayer(playerNames, index))
                .toList();
    }

    private Player generatePlayer(final List<String> playerNames, final Integer index) {
        return new Player(playerNames.get(index), index);
    }

    private void validatePlayers(final List<String> playerNames) {
        validateSize(playerNames);
        validateDuplicatedName(playerNames);
    }

    private void validateSize(final List<String> playerNames) {
        final int playerSize = playerNames.size();

        if (playerSize > MAXIMUM_PLAYER_SIZE || playerSize < MINIMUM_PLAYER_SIZE) {
            throw new IllegalArgumentException(
                    String.format("참가자들의 수는 %d~%d여야 합니다.", MINIMUM_PLAYER_SIZE, MAXIMUM_PLAYER_SIZE));
        }
    }

    private void validateDuplicatedName(final List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException("참가자들의 이름은 중복될 수 없습니다.");
        }
    }

    public int count() {
        return players.size();
    }

    public void climb(final Ladder ladder) {
        for (final Line line : ladder.getLines()) {
            final List<Rung> rungs = line.getRungs();
            move(rungs);
        }
    }

    private void move(final List<Rung> rungs) {
        for (final Player player : players) {
            final Direction direction = player.findMovableDirection(rungs);
            player.moveTo(direction);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
