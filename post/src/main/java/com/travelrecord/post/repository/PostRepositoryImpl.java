package com.travelrecord.post.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelrecord.common.persistence.SortDirection;
import com.travelrecord.post.domain.Post;
import com.travelrecord.post.domain.PostSortType;
import com.travelrecord.post.domain.QPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Post> findPosts(Long userId, Boolean mainDisplay) {
        QPost post = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(post.userId.eq(userId));

        if (mainDisplay != null) {
            builder.and(post.mainDisplay.eq(mainDisplay));
        }

        return queryFactory.selectFrom(post)
                .where(builder)
                .fetch();
    }

    @Override
    public Page<Post> findPosts(Long userId, Boolean mainDisplay, Pageable pageable) {
        QPost post = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(post.userId.eq(userId));

        if (mainDisplay != null) {
            builder.and(post.mainDisplay.eq(mainDisplay));
        }

        // Extract sort information from Pageable
        PostSortType sortType = PostSortType.CREATED_AT; // Default sort
        SortDirection direction = SortDirection.DESC; // Default direction

        if (pageable.getSort().isSorted()) {
            Sort.Order order = pageable.getSort().iterator().next(); // Get the first sort order
            try {
                sortType = PostSortType.valueOf(order.getProperty().toUpperCase());
            } catch (IllegalArgumentException e) {
                // If the property name doesn't match any PostSortType, use default
                sortType = PostSortType.CREATED_AT;
            }
            direction = order.getDirection().isAscending() ? SortDirection.ASC : SortDirection.DESC;
        }

        JPAQuery<Post> contentQuery = queryFactory.selectFrom(post)
                .where(builder)
                .orderBy(pagingSort(post, sortType, direction))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Post> content = contentQuery.fetch();

        // Count query
        Long total = queryFactory.select(post.count())
                .from(post)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0);
    }

    private OrderSpecifier<?> pagingSort(QPost post, PostSortType sortType, SortDirection direction) {
        if (sortType == null) {
            return post._super.createdAt.desc();
        }

        switch (sortType) {
            case CREATED_AT:
                return direction == SortDirection.ASC
                        ? post._super.createdAt.asc()
                        : post._super.createdAt.desc();

            case UPDATED_AT:
                return direction == SortDirection.ASC
                        ? post._super.updatedAt.asc()
                        : post._super.updatedAt.desc();

            default:
                return post._super.createdAt.desc();
        }
    }
}
