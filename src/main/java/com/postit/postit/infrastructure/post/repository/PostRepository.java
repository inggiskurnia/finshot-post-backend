package com.postit.postit.infrastructure.post.repository;

import com.postit.postit.entity.Post;
import com.postit.postit.infrastructure.post.dto.PostResponseDTO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByAuthorIdAndTitleIgnoreCase(Long authorId, String title);

    Optional<Post> findByTitleIgnoreCase(String title);

    @Query("""
        SELECT new com.postit.postit.infrastructure.post.dto.PostResponseDTO(
            p.id,
            p.title,
            p.body,
            p.totalViews,
            p.author.name,
            p.createdAt,
            p.updatedAt
        )
        FROM Post p
        WHERE p.author.id = :userId
        ORDER BY p.createdAt DESC
    """)
    Page<PostResponseDTO> findPaginatedByUserId(@Param("userId") Long userId, Pageable pageable);


    @Query(value = """
            SELECT new com.postit.postit.infrastructure.post.dto.PostResponseDTO(
                    p.id,
                    p.title,
                    p.body,
                    p.totalViews,
                    p.author.name,
                    p.createdAt,
                    p.updatedAt
                )
                FROM Post p
                ORDER BY p.createdAt DESC
            """)
    Page<PostResponseDTO> findPaginatedAllPost(Pageable pageable);
}
