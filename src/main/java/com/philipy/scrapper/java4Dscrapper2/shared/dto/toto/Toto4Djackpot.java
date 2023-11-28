package com.philipy.scrapper.java4Dscrapper2.shared.dto.toto;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Toto4Djackpot {

    private Optional<String> jackpot1Amt;

    private Optional<String> jackpot2Amt;

    private Optional<List<String>> numbers;

}
