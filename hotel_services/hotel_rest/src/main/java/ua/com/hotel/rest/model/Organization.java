package ua.com.hotel.rest.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author: Андрей
 * @since: 06.02.16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Organization {

    private Currencies currencies;

    public Currencies getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currencies currencies) {
        this.currencies = currencies;
    }
}
