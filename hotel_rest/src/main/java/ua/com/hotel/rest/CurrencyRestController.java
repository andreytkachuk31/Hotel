package ua.com.hotel.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Convert currencies from USD
 *
 * @author: Андрей
 * @since: 24.01.16
 */
@Controller
@RequestMapping("/currency")
public class CurrencyRestController {

    @RequestMapping(value = "/uah", method = GET)
    @ResponseBody
    public String getCurrecyUAH() {
        return "aaaa";
    }
}
