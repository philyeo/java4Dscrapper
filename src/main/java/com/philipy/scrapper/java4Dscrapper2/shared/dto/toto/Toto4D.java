package com.philipy.scrapper.java4Dscrapper2.shared.dto.toto;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Toto4D {

    private Optional<String> firstPrize;

    private Optional<String> secondPrize;

    private Optional<String> thirdPrize;

    private Optional<List<String>> specialPrize;

    private Optional<List<String>> consolationPrize;


//    public Optional<List<String>> getSpecialPrize() {
//        return Optional.of(specialPrize.get().stream().map(e -> String.format("%04d", e)).collect(
//              Collectors.toList()));
//    }

    @Override
    public String toString() {
        return "Toto4D{" +
              "firstPrize=" + firstPrize +
              ", secondPrize=" + secondPrize +
              ", thirdPrize=" + thirdPrize +
              ", specialPrize=" + specialPrize +
              ", consolationPrize=" + consolationPrize +
              '}';
    }
}
