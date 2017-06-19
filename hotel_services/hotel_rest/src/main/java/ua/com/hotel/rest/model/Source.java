package ua.com.hotel.rest.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * @author: Андрей
 * @since: 06.02.16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Source {

    private List<Organization> organizations;

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
}
