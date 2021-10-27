package com.philipy.scrapper.java4Dscrapper2.persistance.toto;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GameResult {

    @NotBlank
    private Optional<String> drawDate;

    @NotBlank
    private Optional<String> drawNo;

    @NotBlank
    private GameType gameType;

    public Function<GameType, List<String>> getGameAttributes = e -> e.getAttributes();

    public GameResult(Optional<String> drawDate, Optional<String> drawNo, GameType gameType) {
        this.drawDate = drawDate;
        this.drawNo = drawNo;
        this.gameType = gameType;
    }

}
