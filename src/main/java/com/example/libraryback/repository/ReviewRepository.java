package com.example.libraryback.repository;

import com.example.libraryback.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    Integer countByBookId(UUID bookId);

    List<Review> findAllByBookId(UUID bookId);

    boolean existsByBookIdAndUserId(UUID bookId, UUID userId);

    Page<Review> findAllByBookId(UUID bookId, Pageable pageable);

    Integer countAllByBookId(UUID bookId);
}
