package com.philipy.scrapper.java4Dscrapper2;

import com.google.gson.Gson;
import com.philipy.scrapper.java4Dscrapper2.persistance.toto.PowerToto;
import com.philipy.scrapper.java4Dscrapper2.persistance.toto.StarToto;
import com.philipy.scrapper.java4Dscrapper2.persistance.toto.SupremeToto;
import com.philipy.scrapper.java4Dscrapper2.persistance.toto.Toto4D;
import com.philipy.scrapper.java4Dscrapper2.persistance.toto.Toto4Djackpot;
import com.philipy.scrapper.java4Dscrapper2.persistance.toto.Toto4Dzodiac;
import com.philipy.scrapper.java4Dscrapper2.persistance.toto.Toto5D;
import com.philipy.scrapper.java4Dscrapper2.persistance.toto.Toto6D;
import com.philipy.scrapper.java4Dscrapper2.persistance.toto.TotoResult;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;


import static com.philipy.scrapper.java4Dscrapper2.EndpointConstants.*;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class Java4DscrapperToto {

    private static Logger logger = LoggerFactory.getLogger(Java4DscrapperToto.class);

    static String drawDate;
    static String drawNumber;
    static String gameType1;
    static List<String> toto4Dtop3Results;
    static List<String> toto4DspecialPrizes;
    static List<String> toto4DconsolationPrizes;
    static String zodiacURL;


    public static void main(String[] args) {

        SpringApplication.run(Java4DscrapperToto.class, args);

        System.setProperty("webdriver.chrome.driver", "/Users/philipyeo/Downloads/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get(TEST_TOTO);

        List<WebElement> els1 = driver.findElements(By.cssSelector("div#popup_container * span.txt_black6"));

        drawDate = getDrawHeaderDetailsFunction.apply(els1.get(0).getText(), 0);
        drawNumber = els1.get(1).getText();

        logger.info("drawDate: " + drawDate);
        logger.info("drawNumber: " + drawNumber);

        List<WebElement> els2 = driver.findElements(By.cssSelector("div#popup_container * div.col-sm-6 * td.txt_white5"));
        gameType1 = els2.get(0).getText();

        logger.info(gameType1);

        toto4Dtop3Results = driver.findElements(By.cssSelector("div#popup_container * tr.txt_black2")).get(0).findElements(
              By.cssSelector("td")).stream().map(e -> e.getText()).collect(Collectors.toList());

        logger.info(toto4Dtop3Results.toString());

        toto4DspecialPrizes = driver.findElements(By.cssSelector("div#popup_container * div.col-sm-6 > table > tbody > tr > td > table > tbody")).get(1)
                                    .findElements(By.cssSelector("td")).stream()
                                                                .map(e ->  e.getText())
                                                                .filter(k -> !k.isBlank())
                                                                .filter(k -> k.matches("[0-9]+"))
                                                    .collect(Collectors.toList());


        toto4DconsolationPrizes = driver.findElements(By.cssSelector("div#popup_container > div > div > div:nth-child(1) > table:nth-child(1) > "
                    + "tbody > tr:nth-child(3) > td > table:nth-child(3) > tbody")).get(0)
              .findElements(By.cssSelector("tr.txt_black2 > td"))
              .stream()
              .map(e -> e.getText())
              .filter(k -> !k.isBlank())
              .filter(k -> k.matches("[0-9]+"))
              .collect(Collectors.toList());

        logger.info(toto4DspecialPrizes.toString());
        logger.info(toto4DconsolationPrizes.toString());

        Toto4D toto4D = new Toto4D(Optional.of(toto4Dtop3Results.get(0)), Optional.of(toto4Dtop3Results.get(1)), Optional.of(toto4Dtop3Results.get(2)),
                                    Optional.of(toto4DspecialPrizes), Optional.of(toto4DconsolationPrizes));

        logger.info(toto4D.toString());
//        logger.info("special prizes: " + toto4D.getSpecialPrize().toString());


        List<String> toto4dJackpotraw = driver.findElements(By.cssSelector("div#popup_container * div.col-sm-6 > table > tbody > tr > td > table > tbody")).get(3)
                                        .findElements(By.cssSelector("td")).stream().map(e -> e.getText()).collect(Collectors.toList());

        logger.info("toto4dJackpotraw: " + toto4dJackpotraw.toString());

        Toto4Djackpot toto4Djackpot = new Toto4Djackpot(Optional.of(toto4dJackpotraw.get(1).replaceAll("\\s", "")),
                                                        Optional.of(toto4dJackpotraw.get(9).replaceAll("\\s", "")),
                                                        Optional.of(toto4dJackpotraw.subList(2, 8).stream()
                                                              .map(e -> e.replaceAll("\\s", ""))
                                                              .collect(Collectors.toList())));

        logger.info(toto4Djackpot.toString());


        zodiacURL = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(1) > table:nth-child(4) > tbody > "
              + "tr:nth-child(1) > td.txt_black2.txt_left > span > img")).getAttribute("src");

        String toto4Dzodiac1stPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(1) > table:nth-child(4) >"
              + " tbody > tr:nth-child(1) > td:nth-child(2)")).getText();
        String toto4Dzodiac2ndPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(1) > table:nth-child(4) >"
              + " tbody > tr:nth-child(2) > td:nth-child(2)")).getText();
        String toto4Dzodiac3rdPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(1) > table:nth-child(4) >"
              + " tbody > tr:nth-child(3) > td:nth-child(2)")).getText();
        String toto4Dzodiac4thPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(1) > table:nth-child(4) >"
              + " tbody > tr:nth-child(4) > td:nth-child(2)")).getText();
        String toto4Dzodiac5thPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(1) > table:nth-child(4) >"
              + " tbody > tr:nth-child(5) > td:nth-child(2)")).getText();
        String toto4Dzodiac6thPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(1) > table:nth-child(4) >"
              + " tbody > tr:nth-child(6) > td:nth-child(2)")).getText();

        Toto4Dzodiac toto4Dzodiac = new Toto4Dzodiac(Optional.ofNullable(zodiacURL), Optional.ofNullable(toto4Dzodiac1stPrize),
                                                     Optional.ofNullable(toto4Dzodiac2ndPrize), Optional.ofNullable(toto4Dzodiac3rdPrize),
                                                     Optional.ofNullable(toto4Dzodiac4thPrize), Optional.ofNullable(toto4Dzodiac5thPrize),
                                                     Optional.ofNullable(toto4Dzodiac6thPrize));

        logger.info(toto4Dzodiac.toString());


        String supremeToto_jackpotAmt = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(2) > "
              + "table:nth-child(2) > tbody > tr:nth-child(2) > td.txt_red1")).getText();
        List<Integer> supremeToto_winningNumbers = driver.findElements(By.cssSelector("#popup_container > div > div > "
              + "div:nth-child(2) > table:nth-child(2) > tbody > tr:nth-child(1) > td.txt_black2")).stream().map(e -> Integer.parseInt(e.getText())).collect(
              Collectors.toList());

        SupremeToto supremeToto = new SupremeToto(Optional.ofNullable(supremeToto_jackpotAmt), Optional.ofNullable(supremeToto_winningNumbers));
        logger.info(supremeToto.toString());

        String powerToto_jackpotAmt = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(2) > "
              + "table:nth-child(3) > tbody > tr:nth-child(2) > td.txt_red1")).getText();

        List<String> powerToto_winningNumbers = driver.findElements(By.cssSelector("#popup_container > div > div > "
                                                                        + "div:nth-child(2) > table:nth-child(3) > tbody > tr:nth-child(1) > td.txt_black2")).stream()
                                                        .map(e -> e.getText()).collect(Collectors.toList());

        PowerToto powerToto = new PowerToto(Optional.ofNullable(powerToto_jackpotAmt), Optional.ofNullable(powerToto_winningNumbers));

        logger.info(powerToto.toString());


        String starToto_jackpot1Amt = driver.findElement(By.cssSelector("#popup_container > div > div > "
              + "div:nth-child(2) > table:nth-child(4) > tbody > tr:nth-child(2) > td.txt_red1")).getText();

        String starToto_jackpot2Amt = driver.findElement(By.cssSelector("#popup_container > div > div > "
              + "div:nth-child(2) > table:nth-child(4) > tbody > tr:nth-child(3) > td.txt_red1")).getText();

        List<String> starToto_winningNumbers = driver.findElements(By.cssSelector("#popup_container > div > div > div:nth-child(2) > "
                                                            + "table:nth-child(4) > tbody > tr:nth-child(1) > td.txt_black2")).stream()
                                                        .map(e -> e.getText()).collect(Collectors.toList());

        StarToto starToto = new StarToto(Optional.ofNullable(starToto_jackpot1Amt),
                                         Optional.ofNullable(starToto_jackpot2Amt),
                                         Optional.ofNullable(starToto_winningNumbers));

        logger.info(starToto.toString());

        String toto5D_firstPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(2) >"
                                                        + " table:nth-child(6) > tbody > tr:nth-child(1) > td:nth-child(3)")).getText();

        String toto5D_secondPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(2) > "
              + "table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(2)")).getText();
        String toto5D_thirdPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(2) > "
              + "table:nth-child(6) > tbody > tr:nth-child(3) > td:nth-child(2)")).getText();
        String toto5D_fourthPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(2) > "
              + "table:nth-child(6) > tbody > tr:nth-child(1) > td:nth-child(5)")).getText();
        String toto5D_fifthPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(2) > "
              + "table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(4)")).getText();
        String toto5D_sixthPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(2) > "
              + "table:nth-child(6) > tbody > tr:nth-child(3) > td:nth-child(4)")).getText();

        Toto5D toto5D = new Toto5D(Optional.of(toto5D_firstPrize), Optional.of(toto5D_secondPrize), Optional.of(toto5D_thirdPrize),
                                   Optional.of(toto5D_fourthPrize), Optional.of(toto5D_fifthPrize), Optional.of(toto5D_sixthPrize));

        logger.info(toto5D.toString());

        String toto6D_firstPrize = driver.findElement(By.cssSelector("#popup_container > div > div > div:nth-child(2) >"
                                            + " table:nth-child(7) > tbody > tr:nth-child(1) > td.txt_black4")).getText();

        List<String> toto6D_secondPrize = driver.findElements(By.cssSelector("#popup_container > div > div > div:nth-child(2) >"
                                            + " table:nth-child(7) > tbody > tr:nth-child(2) > td.txt_black4")).stream()
                    .map(e -> e.getText()).collect(Collectors.toList());

        List<String> toto6D_thirdPrize = driver.findElements(By.cssSelector("#popup_container > div > div > div:nth-child(2) > "
                    + "table:nth-child(7) > tbody > tr:nth-child(3) > td.txt_black4")).stream()
              .map(e -> e.getText()).collect(Collectors.toList());

        List<String> toto6D_fourthPrize = driver.findElements(By.cssSelector("#popup_container > div > div > div:nth-child(2) > "
                    + "table:nth-child(7) > tbody > tr:nth-child(4) > td.txt_black4")).stream()
              .map(e -> e.getText()).collect(Collectors.toList());

        List<String> toto6D_fifthPrize = driver.findElements(By.cssSelector("#popup_container > div > div > div:nth-child(2) > "
                    + "table:nth-child(7) > tbody > tr:nth-child(5) > td.txt_black4")).stream()
              .map(e -> e.getText()).collect(Collectors.toList());

        Toto6D toto6D = new Toto6D(Optional.of(toto6D_firstPrize),Optional.of(toto6D_secondPrize),Optional.of(toto6D_thirdPrize),
                                   Optional.of(toto6D_fourthPrize),Optional.of(toto6D_fifthPrize));

        logger.info(toto6D.toString());

        TotoResult totoResult = new TotoResult(drawDate, drawNumber, toto4D, toto4Djackpot, toto4Dzodiac, supremeToto,
                                                powerToto, starToto, toto5D, toto6D);

//        System.out.println(new Gson().toJson(totoResult));
        logger.info(new Gson().toJson(totoResult));

        driver.quit();

    }


    static BiFunction<String, Integer, String> getDrawHeaderDetailsFunction = (extractedText, pos) -> extractedText.split(",")[pos];

}
