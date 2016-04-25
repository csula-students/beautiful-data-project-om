package edu.csula.datascience.acquisition;

/**
 * A simple model for testing
 */
public class S {
    private final String id;
    private final String content;

    public S(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public static S build(M data) {
        return new S(data.getId(), data.getContent());
    }
}
