package edu.csula.datascience.acquisition;

/**
 * Mock raw data
 */
public class M {
    private final String id;
    private final String content;

    public M(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
