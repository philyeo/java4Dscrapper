package com.philipy.scrapper.java4Dscrapper2;

import static com.philipy.scrapper.java4Dscrapper2.EndpointConstants.TEST_MAINVIEW_DAMACAI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.philipy.scrapper.java4Dscrapper2.persistance.damacai.DamacaiResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class Java4DscrapperDamacai {

    private static Logger logger = LoggerFactory.getLogger(Java4DscrapperDamacai.class);

    public static void main(String[] args) throws IOException, URISyntaxException {

        CloseableHttpClient httpClient = HttpClients.custom()
              .setSSLHostnameVerifier(new NoopHostnameVerifier())
              .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        headers.add("authority", "www.damacai.com.my");
        headers.add("cookiesession", "31");
        headers.add("accept","*/*");
        headers.add("sec-ch-ua-mobile", "?0");
        headers.add("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36 Edg/95.0.1020.30");
        headers.add("sec-fetch-site", "same-origin");
        headers.add("sec-ch-ua", "\"Microsoft Edge\";v=\"95\", \"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"");
        headers.add("sec-fetch-mode", "cors");
        headers.add("sec-fetch-dest", "empty");
        headers.add("referer", "https://www.damacai.com.my/past-draw-result/");

        HttpEntity entity = new HttpEntity(headers);
        ObjectMapper objectMapper = new ObjectMapper();
        String request = "{\"some\":\"value\"}";

        Map<String, String> dataMap = objectMapper.readValue(request, Map.class);

        ResponseEntity<String> response = restTemplate.exchange(TEST_MAINVIEW_DAMACAI,
              HttpMethod.GET, entity, String.class, dataMap);

        Map<String, String> map = objectMapper.readValue(response.getBody(), Map.class);

        String drawResultURLlink = map.get("link");

        logger.debug(drawResultURLlink);
        DamacaiResult damacaiResult = objectMapper.readValue(getDrawResult(drawResultURLlink), DamacaiResult.class);


        logger.info(damacaiResult.toString());
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
