package ua.com.hotel.rest.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author: Андрей
 * @since: 01.02.16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currencies {

    @JsonProperty("EUR")
    private CurrencyType eur;
    @JsonProperty("RUB")
    private CurrencyType rub;
    @JsonProperty("USD")
    private CurrencyType usd;

    public CurrencyType getEur() {
        return eur;
    }

    public void setEur(CurrencyType eur) {
        this.eur = eur;
    }

    public CurrencyType getRub() {
        return rub;
    }

    public void setRub(CurrencyType rub) {
        this.rub = rub;
    }

    public CurrencyType getUsd() {
        return usd;
    }

    public void setUsd(CurrencyType usd) {
        this.usd = usd;
    }
}

