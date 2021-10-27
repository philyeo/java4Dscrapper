package com.philipy.scrapper.java4Dscrapper2.persistance.toto;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Toto6D {

    private Optional<String> firstPrize;

    private Optional<List<String>> secondPrize;

    private Optional<List<String>> thirdPrize;

    private Optional<List<String>> fourthPrize;

    private Optional<List<String>> fifthPrize;
}
