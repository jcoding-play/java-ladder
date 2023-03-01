package laddergame;

import laddergame.controller.LadderController;
import laddergame.domain.ladder.line.RandomBooleanGenerator;
import laddergame.view.InputView;
import laddergame.view.OutputView;

public class LadderApplication {
    public static void main(String[] args) {
        LadderController ladderController = new LadderController(
                new InputView(), new OutputView(), new RandomBooleanGenerator());
        run(ladderController);
    }

    private static void run(LadderController ladderController) {
        try {
            ladderController.run();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }
}
