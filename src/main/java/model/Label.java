package model;

public class Label {
    private final Long id;
    private final String name;
    private Long postId;

    public Label(Long id, String name, Long postId) {
        this.name = name;
        this.id = id;
        this.postId = postId;
    }

    public Label(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public long getPostId() {
        return this.postId;
    }

    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return "Label{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", postId=" + getPostId() +
                '}';
    }
}
