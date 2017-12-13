package damasterham.learningandroid.rest;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import damasterham.learningandroid.rest.entities.Info;

public class RestService
{
    private static final int PORT = 42000;
    // !!! IMPORTANT !!! local ip adress has to be changed each time you get a new one assigned
    // Use cmd ipconfig
    private static final String API_URL = "http://192.168.0.13:" + PORT + "/api";  //"http://10.111.179.252:42000/api";

    private RestTemplate rest;
    //private Data data = Data.getInstance();

    private static RestService restService;

    private static ClientHttpRequestFactory getClientHttpRequestFactory()
    {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }

    private RestService()
    {
        // Create rest template for http json calls, with request factory
        rest = new RestTemplate();//getClientHttpRequestFactory());
        //List<HttpMessageConverter<?>> list = rest.getMessageConverters();

        // Configure JSON converter
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public static RestService getInstance()
    {
        if (restService == null)
            restService = new RestService();

        return restService;
    }

    public Info getRunnerInfo(long id)
    {
        return rest.getForObject(API_URL + "/runner/" + id + "/info", Info.class);
    }

}
