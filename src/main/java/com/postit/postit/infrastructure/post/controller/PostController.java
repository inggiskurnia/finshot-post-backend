package com.postit.postit.infrastructure.post.controller;

import com.postit.postit.common.response.ApiResponse;
import com.postit.postit.infrastructure.post.dto.PaginatedPostRequestDTO;
import com.postit.postit.infrastructure.post.dto.PostRequestDTO;
import com.postit.postit.usecase.post.CreatePostUsecase;
import com.postit.postit.usecase.post.DeletePostUsecase;
import com.postit.postit.usecase.post.GetPostUsecase;
import com.postit.postit.usecase.post.UpdatePostUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final GetPostUsecase getPostUsecase;
    private final CreatePostUsecase createPostUsecase;
    private final UpdatePostUsecase updatePostUsecase;
    private final DeletePostUsecase deletePostUsecase;

    public PostController(GetPostUsecase getPostUsecase, CreatePostUsecase createPostUsecase, UpdatePostUsecase updatePostUsecase, DeletePostUsecase deletePostUsecase) {
        this.getPostUsecase = getPostUsecase;
        this.createPostUsecase = createPostUsecase;
        this.updatePostUsecase = updatePostUsecase;
        this.deletePostUsecase = deletePostUsecase;
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> getPostBySlug(@PathVariable String slug){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Get post success !", getPostUsecase.getPostBySlug(slug));
    }

    @GetMapping("/paginated")
    public ResponseEntity<?> getPaginatedPosts(@RequestParam("page") int page,
                                               @RequestParam("limit") int limit){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Get paginated post success", getPostUsecase.getPaginatedAllPost(new PaginatedPostRequestDTO(page, limit)));
    }

    @GetMapping("/paginated/users")
    public ResponseEntity<?> getPaginatedPostsByUser(@RequestParam("page") int page,
                                               @RequestParam("limit") int limit){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Get paginated post success", getPostUsecase.getPaginatedByUserId(new PaginatedPostRequestDTO(page, limit)));
    }


    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequestDTO req){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Create new post success !", createPostUsecase.createNewPost(req));
    }

    @PutMapping("/{slug}")
    public ResponseEntity<?> updatePost(@PathVariable String slug,
                                        @RequestBody PostRequestDTO req){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Update post success !", updatePostUsecase.updatePost(slug, req));
    }

    @PutMapping("/views/{slug}")
    public ResponseEntity<?> updateTotalView(@PathVariable String slug){
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Update total view post success !", updatePostUsecase.updateTotalView(slug));
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<?> deletePostBySlug(@PathVariable String slug){
        deletePostUsecase.deletePostBySlug(slug);
        return ApiResponse.successResponse(HttpStatus.OK.value(), "Delete post success", null);
    }
}
