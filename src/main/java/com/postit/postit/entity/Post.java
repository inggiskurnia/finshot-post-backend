package com.postit.postit.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.time.OffsetDateTime;

@Setter
@Getter
@Entity
@Table(name = "posts")
@SQLDelete(sql = "UPDATE user SET deleted_at = now() WHERE id = ?")
@FilterDef(name = "deletedFilter")
@Filter(name = "deletedFilter", condition = "deleted_at IS NULL")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_id_gen")
    @SequenceGenerator(name = "posts_id_gen", sequenceName = "posts_id_gen")
    private Long id;

    @Max(255)
    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @ColumnDefault("0")
    @Column(name = "total_views")
    private Integer totalViews;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "created_at")
    private OffsetDateTime updatedAt;

    @Column(name = "created_at")
    private OffsetDateTime deletedAt;
}
