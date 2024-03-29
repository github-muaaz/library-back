package com.example.libraryback.controller;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.review.ReviewAddDTO;
import com.example.libraryback.payload.review.ReviewDTO;
import com.example.libraryback.payload.review.ReviewDTOList;
import com.example.libraryback.payload.review.ReviewsDTO;
import com.example.libraryback.security.CurrentUser;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/review")
public interface ReviewController {

    @GetMapping("/{size}/{page}/{bookId}")
    ApiResult<ReviewsDTO> get(@PathVariable Integer size, @PathVariable Integer page, @PathVariable UUID bookId);

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);

    @GetMapping("/{id}")
    ApiResult<ReviewDTO> get(@PathVariable UUID id);

    @GetMapping("/book-review/{bookId}")
    ApiResult<List<ReviewDTOList>> getBookReview(@PathVariable UUID bookId);

    @PostMapping()
    ApiResult<ReviewDTO> add(@NotNull @ModelAttribute ReviewAddDTO reviewAddDTO, @CurrentUser User user);

    @PutMapping("/{id}")
    ApiResult<ReviewDTO> edit(@PathVariable UUID id, @RequestBody ReviewAddDTO reviewAddDTO, @CurrentUser User user);

}
