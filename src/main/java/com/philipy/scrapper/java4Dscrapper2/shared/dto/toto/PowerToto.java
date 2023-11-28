package com.philipy.scrapper.java4Dscrapper2.shared.dto.toto;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class PowerToto {

    private Optional<String> jackpotAmt;

    private Optional<List<String>> winningNumbers;

}
