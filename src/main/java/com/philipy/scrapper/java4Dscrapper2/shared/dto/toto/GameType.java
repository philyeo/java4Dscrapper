package com.philipy.scrapper.java4Dscrapper2.shared.dto.toto;


import java.util.Arrays;
import java.util.List;

public enum GameType {
    TOTO4D("TOTO4D", new String[]{"FirstPrize", "SecondPrize", "ThirdPrize"}),
    TOTO4D_JACKPOT("TOTO4D_JACKPOT", new String[]{"Jackpot1_Amount", "Jackpot2_Amount", "WinningNumbers"}),
    TOTO4D_ZODIAC("TOTO4D_ZODIAC", new String[]{"Zodiac_URL", "FirstPrize", "SecondPrize", "ThirdPrize", "FourthPrize", "FifthPrize", "SixthPrize"}),
    SUPREME_TOTO("SUPREME_TOTO", new String[]{"Jackpot_Amount", "WinningNumber"}),
    POWER_TOTO("POWER_TOTO", new String[]{"Jackpot_Amount", "WinningNumber"}),
    STAR_TOTO("START_TOTO", new String[]{"Jackpot1_Amount", "Jackpot2_Amount", "WinningNumbers"}),
    TOTO5D("TOTO5D", new String[]{"FirstPrize", "SecondPrize", "ThirdPrize"}),
    TOTO6D("TOTO6D", new String[]{"FirstPrize", "SecondPrize", "ThirdPrize", "FourthPrize", "FifthPrize"});

    private final String gameType;
    private final String[] attribs;

    private GameType(String gameType, String[] attribs) {
        this.gameType = gameType;
        this.attribs = attribs;
    }

    public String getGameType() {
        return gameType;
    }

    public List<String> getAttributes() {
        return Arrays.asList(attribs);
    }

}
