package com.philipy.scrapper.java4Dscrapper2.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DamacaiResult {

    private String id;
    private String status;
    private String NewGame;
    private String drawDate;
    private String p1HorseNo;
    private String p2HorseNo;
    private String p3HorseNo;
    private String drawNo;
    private List<String> starterHorseList;
    private List<String> consolidateList;
    private List<String> starterList;

    @JsonProperty("1+3DJackpot1")
    private String _1plus3DJackpot1;

    @JsonProperty("1+3DJackpot2")
    private String _1plus3DJackpot2;

    private String dmcJackpot1;
    private String dmcJackpot2;

    @JsonProperty("3DJackpot")
    private String _3DJackpot;

    @JsonProperty("1+3DJackpotWinning1")
    private String _1plus3DJackpotWinning1;

    @JsonProperty("1+3DJackpotWinning2")
    private String _1plus3DJackpotWinning2;

    @JsonProperty("3DJackpotWinning")
    private String _3DJackpotWinning;

    private String dmcJackpotWinning1;
    private String dmcJackpotWinning2;

    private String drawVenue;
    private String raceDate;
    private String raceNo;
    private String raceVenue;
    private String p1;
    private String p2;
    private String p3;

    private List<String> consolidateList3p3d;
    private List<String> starterList3p3d;
    private String p13p3d;
    private String p23p3d;
    private String p33p3d;
    private String zodiac3dp1;
    private String zodiac3dp2;
    private String zodiac3dp3;

    @JsonProperty("3+3DBonusp1")
    private String _3plus3DBonusp1;

    @JsonProperty("3+3DBonusp2")
    private String _3plus3DBonusp2;

    @JsonProperty("3+3DBonusp3")
    private String _3plus3DBonusp3;

    @JsonProperty("3+3DBonusp1Winning")
    private String _3plus3DBonusp1Winning;

    @JsonProperty("3+3DBonusp2Winning")
    private String _3plus3DBonusp2Winning;

    @JsonProperty("3+3DBonusp3Winning")
    private String _3plus3DBonusp3Winning;

    private List<String> winninghistoryconsolidateList;
    private List<String> winninghistoryconsolidateListPrize;
    private List<String> winninghistorystarterList;
    private List<String> winninghistorystarterListPrize;
    private List<String> winninghistoryconsolidateList3p3d;
    private List<String> winninghistoryconsolidateList3p3dPrize;
    private List<String> winninghistorystarterList3p3d;
    private List<String> winninghistorystarterList3p3dPrize;

    private String winninghistoryp1;
    private String winninghistoryp1prize;
    private String winninghistoryp2;
    private String winninghistoryp2prize;
    private String winninghistoryp3;
    private String winninghistoryp3prize;
    private String winninghistoryp13d;
    private String winninghistoryp13dprize;
    private String winninghistoryp23d;
    private String winninghistoryp23dprize;
    private String winninghistoryp33d;
    private String winninghistoryp33dprize;
    private String winninghistoryp13p3d;
    private String winninghistoryp13p3dprize;
    private String winninghistoryp23p3d;

    private String winninghistoryp23p3dprize;
    private String winninghistoryp33p3d;
    private String winninghistoryp33p3dprize;
    private String oneplusthreedjackpot1msg;
    private String oneplusthreedjackpot2msg;
    private String dmcwinnerstatus4msg;

    private String dmcwinnerstatus5msg;
    private String threedjackpotmsg;
    private String threeplustatus1bonusmsg;
    private String threeplustatus2bonusmsg;
    private String threeplustatus3bonusmsg;

}
