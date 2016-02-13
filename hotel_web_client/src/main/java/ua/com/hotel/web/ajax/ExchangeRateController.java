package ua.com.hotel.web.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/exchangeRate")
@Controller
public class ExchangeRateController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "usd/{value}", method = RequestMethod.GET)
    @ResponseBody
    public double convertUsdToUah(@PathVariable double value) {
        return value;
    }
}
