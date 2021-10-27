package com.philipy.scrapper.java4Dscrapper2.persistance.toto;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class SupremeToto {

    private Optional<String> jackpotAmt;

    private Optional<List<Integer>> winningNumbers;

}
