package ua.com.hotel.model.pagination;

/**
 * @author Andrii_Tkachuk
 * @since 8/31/2015
 */
public class Pageable {

    private int page;
    private int perPage;
    private String sort;
    private String filter;

    public Pageable() {
    }

    public Pageable(int page, int perPage, String sort, String filter) {
        this.page = page;
        this.perPage = perPage;
        this.sort = sort;
        this.filter = filter;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
