package ladder.controller;

import ladder.domain.game.LadderGame;
import ladder.domain.game.PlayResult;
import ladder.domain.game.Players;
import ladder.domain.game.Prizes;
import ladder.domain.generator.RandomBooleanGenerator;
import ladder.domain.ladder.Height;
import ladder.domain.ladder.Ladder;
import ladder.dto.LadderDto;
import ladder.dto.PlayersDto;
import ladder.utils.Converter;
import ladder.view.InputView;
import ladder.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class LadderController {
    private final InputView inputView;
    private final OutputView outputView;

    public LadderController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Players players = retryOnException(this::readPlayers);
        final Prizes prizes = retryOnException(() -> readPrizes(players.count()));
        final Height height = retryOnException(this::readLadderHeight);
        final Ladder ladder = new Ladder(players.count(), height.getValue(), new RandomBooleanGenerator());
        printLadder(players, ladder, prizes);

        final LadderGame ladderGame = new LadderGame(ladder, players, prizes.getPrizes());
        final PlayResult playResult = ladderGame.play();
        while (playResult.canAskResult()) {
            final String name = inputView.readNameToSeeResult();
            final Map<String, String> result = playResult.findByName(name);
            outputView.printResult(result, name);
        }
    }

    public Players readPlayers() {
        final String input = inputView.readPlayerNames();
        final List<String> playerNames = Converter.stringToList(input);

        return new Players(playerNames);
    }

    private Prizes readPrizes(final int playerCount) {
        final String readPrizes = inputView.readPrizes();
        final List<String> prizes = Converter.stringToList(readPrizes);

        return new Prizes(prizes, playerCount);
    }

    public Height readLadderHeight() {
        final int height = inputView.readLadderHeight();

        return new Height(height);
    }

    private void printLadder(final Players players, final Ladder ladder, final Prizes prizes) {
        outputView.printLadderResultMessage();
        outputView.printPlayerNames(PlayersDto.from(players));
        outputView.printLadder(LadderDto.from(ladder));
        outputView.printPrizes(prizes.getPrizes());
    }

    private <T> T retryOnException(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return retryOnException(supplier);
        }
    }
}
