package ua.com.dog.hotel.model.pagination;

import java.util.List;

/**
 * @author Andrii_Tkachuk
 * @since 9/1/2015
 */
public class PaginatedResults<E> {

    private int numberOfPages;
    private int totalCount;
    private List<E> results;

    public PaginatedResults() {
    }

    public PaginatedResults(int numberOfPages, int totalCount, List<E> results) {
        this.numberOfPages = numberOfPages;
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<E> getResults() {
        return results;
    }

    public void setResults(List<E> results) {
        this.results = results;
    }
}
