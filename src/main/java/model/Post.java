package model;

import java.util.List;

public class Post {
    private Long id;
    private final String content;
    private String created;
    private String updated;
    private PostStatus postStatus;
    private Long writerId;
    private List<Label> labels;

    public Post(String content, Long writerId) {
        this.content = content;
        this.writerId = writerId;
    }

    public Post(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Post(Long id, String content, String created, String updated,
                PostStatus postStatus, Long writerId, List<Label> labels) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.postStatus = postStatus;
        this.writerId = writerId;
        this.labels = labels;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getWriterId() {
        return writerId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", postStatus=" + postStatus +
                ", writerId=" + writerId +
                ", labels=" + labels +
                '}';
    }
}
