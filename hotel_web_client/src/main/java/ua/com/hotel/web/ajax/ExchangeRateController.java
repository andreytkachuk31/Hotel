package ua.com.hotel.web.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/exchangeRate")
public class ExchangeRateController {

    @Value("${rest.url}")
    private String restUrl;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "usd/{value}", method = GET)
    @ResponseBody
    public double convertUsdToUah(@PathVariable double value) {
        return restTemplate.getForObject(restUrl + "/exchangeRate/usd/{value}", Double.class, value);
    }
}
