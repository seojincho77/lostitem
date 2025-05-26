package com.example.lostitem.services;

import com.example.lostitem.models.*;
import com.example.lostitem.repositories.PostRepository;
import com.example.lostitem.repositories.PostSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getRecentPosts() {
        return postRepository.findTop7ByOrderByCreatedAtDesc();
    }

    public List<Post> getLostPosts() {
        return postRepository.findByPostType(PostType.lost);
    }

    public List<Post> getFoundPosts() {
        return postRepository.findByPostType(PostType.found);
    }

    public Optional<Post> getPostById(Integer id) {
        return postRepository.findById(id);
    }

    public List<Post> getPostsByUser(Users user) {
        return postRepository.findByUser(user);
    }

    /*public List<Post> getFilteredLostPosts(DateType date, CategoryType category, String keyword, String place, PostType postType, StatusType status) {
        List<Post> lostPosts = postRepository.findByPostType(postType);

        return lostPosts.stream()
                .filter(post -> {
                    LostItem lostItem = post.getLostItem();
                    boolean match = true;

                    if (category != CategoryType.NONE) {
                        match &= lostItem.getCategory() == category;
                    }

                    if (date != DateType.NONE) {
                        LocalDate lostDate = lostItem.getLostDate();
                        LocalDate now = LocalDate.now();

                        switch (date) {
                            case DAY -> match &= lostDate.equals(now);
                            case WEEK -> match &= lostDate.isAfter(now.minusDays(7));
                            case MONTH -> match &= lostDate.isAfter(now.minusDays(30));
                            case YEAR -> match &= lostDate.isAfter(now.minusDays(365));
                        }
                    }

                    if (keyword != null && !keyword.trim().isEmpty()) {
                        String lowerKeyword = keyword.toLowerCase();
                        String title = post.getTitle() != null ? post.getTitle().toLowerCase() : "";

                        match &= title.contains(lowerKeyword);
                    }

                    if (status != StatusType.NONE) {
                        boolean isRecovered = post.getIsRecovered() != null && post.getIsRecovered();

                        if (status == StatusType.PROCESSING) {
                            match &= !isRecovered;
                        } else if (status == StatusType.DONE) {
                            match &= isRecovered;
                        }
                    }

                    if(postType == PostType.lost) {
                        if (place != null && !place.trim().isEmpty()) {
                            String lowerPlace = place.toLowerCase();
                            String lostPlace = lostItem.getLostPlace() != null ? lostItem.getLostPlace().toLowerCase() : "";

                            match &= lostPlace.contains(lowerPlace);
                        }
                    }else if(postType == PostType.found) {
                        if (place != null && !place.trim().isEmpty()) {
                            String lowerPlace = place.toLowerCase();
                            String lostPlace = post.getFoundPlace() != null ? post.getFoundPlace().toLowerCase() : "";

                            match &= lostPlace.contains(lowerPlace);
                        }
                    }

                    return match;

                })
                .collect(Collectors.toList());
    }*/

    public Page<Post> getFilteredLostPosts(
            DateType date,
            CategoryType category,
            String keyword,
            String place,
            PostType type,
            StatusType status,
            int page
    ) {
        Pageable pageable = PageRequest.of(page, 7, Sort.by(Sort.Direction.DESC, "createdAt"));
        Specification<Post> spec = PostSpecification.filterPosts(date, category, keyword, place, type, status);
        return postRepository.findAll(spec, pageable);
    }

    public void toggleIsRecovered(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Boolean current = post.getIsRecovered();
        post.setIsRecovered(!current); // true면 false, false면 true

        // 업데이트 타임도 바꾸고 싶다면 아래 추가
//        post.setRecoveredAt(!current ? LocalDateTime.now() : null);

        postRepository.save(post); // 변경 내용 저장
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}