package com.philipy.scrapper.java4Dscrapper2.persistance.toto;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Document
public class TotoResult {

    @Id
    private String drawDate;

    private String drawNumber;

    private Toto4D toto4D;

    private Toto4Djackpot toto4Djackpot;

    private Toto4Dzodiac toto4Dzodiac;

    private SupremeToto supremeToto;

    private PowerToto powerToto;

    private StarToto starToto;

    private Toto5D toto5D;

    private Toto6D toto6D;

}
