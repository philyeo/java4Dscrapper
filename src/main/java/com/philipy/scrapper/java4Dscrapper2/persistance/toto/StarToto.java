package com.philipy.scrapper.java4Dscrapper2.persistance.toto;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class StarToto {

    private Optional<String> jackpot1Amt;

    private Optional<String> jackpot2Amt;

    private Optional<List<String>> winningNumbers;


}
