package com.berla.pwrapps.isiapp.service

import com.berla.pwrapps.isiapp.dto.GetCostReturnDto
import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j
import groovyx.net.http.RESTClient
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import static groovyx.net.http.ContentType.HTML

@Service
@Slf4j
class GoogleMapsApiService {

    @Value('${api.google.maps.api-key}')
    String apiKey
    @Value('${api.google.maps.language}')
    String apiLanguage

    public GetCostReturnDto getDistanceBetweenPoints(String origin, String destination) {
        String url =
                "https://maps.googleapis.com/maps/api/distancematrix/json?key=" +
                        apiKey +
                        "&origins=" + origin +//"Vancouver+BC|Seattle"
                        "&destinations=" + destination + //"San+Francisco|Victoria+BC"
                        "&language=" + apiLanguage;
        log.info(url)
        def returnFromApi = new JsonSlurper().parse(new StringReader(getBase64ImageFromURL(url)));

        log.debug("Return from google maps distance matrix api is " + returnFromApi)

        if(returnFromApi.rows[0] != null) {
            String distance = returnFromApi.rows[0].elements[0].distance.text;
            String duration = returnFromApi.rows[0].elements[0].duration.text;
            String status = returnFromApi.rows[0].elements[0].status;
            println distance + " " + duration + " " + status

            return new GetCostReturnDto(distance, duration, status)
        }
        null
    }

    /**
     * Method for reading text from URL
     * @param url url from which the text has to be read
     * @return all the text from the selected URL
     */
    static String readTextFromUrl(String url) {
        RESTClient restClient = new RESTClient(url)
        def resp = restClient.get(contentType: HTML)
        String dataAsString = IOUtils.toString(resp.getData())
        println dataAsString;
        restClient.shutdown()
        dataAsString
    }

    static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

    static String getBase64ImageFromURL(String url) {
        String result = "";
        try {
            URL pageUrl = new URL(url)
            HttpURLConnection ucon = (HttpURLConnection)pageUrl.openConnection()
            ucon.setRequestProperty("User-Agent", USER_AGENT)

            if(ucon.getResponseCode()==200){
                InputStream stream = new BufferedInputStream(ucon.getInputStream());
                if (stream != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null)
                        result += line;
                }
                stream.close();
                return result;
            }

        } catch (Exception e) {
            log.error("Exception during getting image from URL")
            log.error(e.getMessage())
        }
        null
    }
}
