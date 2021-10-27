package com.philipy.scrapper.java4Dscrapper2;

import static com.philipy.scrapper.java4Dscrapper2.EndpointConstants.TEST_MAINVIEW_DAMACAI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.philipy.scrapper.java4Dscrapper2.persistance.damacai.DamacaiResult;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

        // call get, https://www.damacai.com.my/callpassresult?pastdate=<date>
        // e.g. https://www.damacai.com.my/callpassresult?pastdate=20211016
        // return
        // {"link":"https://prddmcremt1.blob.core.windows.net/drawresult/DrawDate/20211016.json
        // ?sv=2014-02-14&sr=b&sig=UaBDVp2KUxP7Z8DxpJAyNHdKmb3aX%2FM1PkATZGGcxAU%3D&st=2021-10-25T08:45:18Z&se=2021-10-25T09:55:18Z&sp=r"}


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

        logger.info(map.get("link"));


//        ResponseEntity<DamacaiResult> damacaiResultResponseEntity = restTemplate.getForEntity(map.get("link"), DamacaiResult.class);
        HttpEntity<DamacaiResult> damacaiResultHttpEntity = restTemplate.exchange(map.get("link"), HttpMethod.GET, getHttpEntity(), DamacaiResult.class, getRequestParams(map.get("link")));
        DamacaiResult damacaiResult = damacaiResultHttpEntity.getBody();

//        DamacaiResult damacaiResult = objectMapper.readValue(map.get("link"), DamacaiResult.class);

        logger.info(damacaiResult.toString());
    }

    private static HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "en-GB,en;q=0.9,en-US;q=0.8,ms;q=0.7");
        headers.add("Connection", "keep-alive");
        headers.add("Host", "prddmcremt1.blob.core.windows.net");
        headers.add("Origin", "https://www.damacai.com.my/");
        headers.add("Referer", "https://www.damacai.com.my/");
        headers.add("sec-ch-ua", "\"Microsoft Edge\";v=\"95\", \"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"");
        headers.add("sec-ch-ua-mobile", "?0");
        headers.add("sec-ch-ua-platform", "macOS");
        headers.add("sec-fetch-dest", "empty");
        headers.add("sec-fetch-mode", "cors");
        headers.add("sec-fetch-site", "cross-site");
        headers.add("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36 Edg/95.0.1020.30");

        return new HttpEntity(headers);
    }

    private static Map<String, String> getRequestParams(String returnedUrl)
          throws URISyntaxException {
        List<NameValuePair> params = URLEncodedUtils.parse(new URI(returnedUrl), Charset.forName("UTF-8"));

        Map<String, String> mappedParams = params.stream().collect(
              Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));

        logger.info(mappedParams.toString());

        return mappedParams;

    }

}
