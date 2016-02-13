package ua.com.hotel.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import ua.com.hotel.rest.model.Currencies;
import ua.com.hotel.rest.model.CurrencyType;
import ua.com.hotel.rest.model.Organization;
import ua.com.hotel.rest.model.Source;

import static com.google.common.collect.Iterables.getFirst;
import static com.google.common.collect.Iterables.getOnlyElement;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Convert currencies from USD
 *
 * @author: Андрей
 * @since: 24.01.16
 */
@Controller
@RequestMapping("/exchangeRate")
public class ExchangeRateController {

    @Value("${exchangeRate.url.json}")
    private String exchangeRateUrl;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/usd/{value}", method = GET)
    @ResponseBody
    public double convertUsdToUah(@PathVariable double value) {
        Source source = restTemplate.getForObject(exchangeRateUrl, Source.class);

        double exchangeRate = getAverageExchangeRateForUSD(source);

        return value * exchangeRate;
    }

    public double getAverageExchangeRateForUSD(Source source) {
        Organization organization = getFirst(source.getOrganizations(), null);
        if (organization != null) {
            Currencies currencies = organization.getCurrencies();
            CurrencyType usd = currencies.getUsd();

            double ask = usd.getAsk();
            double bid = usd.getBid();

            return (ask + bid) / 2;
        }

        return 0.0;
    }
}
