package com.philipy.scrapper.java4Dscrapper2.admin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.philipy.scrapper.java4Dscrapper2.shared.dto.DamacaiDrawResultResponse;
import com.philipy.scrapper.java4Dscrapper2.shared.dto.DamacaiResult;
import com.philipy.scrapper.java4Dscrapper2.shared.persistance.document.DamacaiResults;
import com.philipy.scrapper.java4Dscrapper2.shared.persistance.repository.DamacaiRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.philipy.scrapper.java4Dscrapper2.shared.EndpointConstants.TEST_MAINVIEW_DAMACAI;

@Service
@AllArgsConstructor
public class DamacaiScrapperService {

    private static Logger logger = LoggerFactory.getLogger(DamacaiScrapperService.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    private DamacaiRepository repository;


    public void scrapDrawResult(String date) throws IOException {
        String initialUrl = TEST_MAINVIEW_DAMACAI;
        String innerLink = getDrawResultURL(initialUrl).getLink();

        DamacaiResult damacaiResult = objectMapper.readValue(getDrawResult(innerLink), DamacaiResult.class);

        repository.insert(DamacaiResults.builder()
            .drawDate(date)
            .result(damacaiResult)
            .build());
    }

    private DamacaiDrawResultResponse getDrawResultURL(String initialUrl) {
        try {
            //            String url = "https://www.damacai.com.my/callpassresult?pastdate=20231104";
            URL obj = new URL(initialUrl);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set the request method to GET
            con.setRequestMethod("GET");

            // Set request headers as specified in the curl command
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("accept-language", "en-GB,en-US;q=0.9,en;q=0.8");
            con.setRequestProperty("cookie", "_gid=GA1.3.244083715.1701090867; _ga_HMH99E1KTY=GS1.1.1701173012.3.1.1701173026.0.0.0; _ga=GA1.1.2134452614.1701090867");
            con.setRequestProperty("cookiesession", "403");
            con.setRequestProperty("referer", "https://www.damacai.com.my/past-draw-result/");
            con.setRequestProperty("sec-ch-ua", "\"Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24\"");
            con.setRequestProperty("sec-ch-ua-mobile", "?0");
            con.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
            con.setRequestProperty("sec-fetch-dest", "empty");
            con.setRequestProperty("sec-fetch-mode", "cors");
            con.setRequestProperty("sec-fetch-site", "same-origin");
            con.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
            con.setRequestProperty("x-requested-with", "XMLHttpRequest");

            // Get the response code
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return objectMapper.readValue(response.toString(), DamacaiDrawResultResponse.class);

        } catch (IOException e) {
            return null;
        }
    }


    private static String getDrawResult(String link) throws IOException {
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        String responseBody = response.toString();
        System.out.println("Response: " + responseBody);

        connection.disconnect();
        return  responseBody;
    }

}
