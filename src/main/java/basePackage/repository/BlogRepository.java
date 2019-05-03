package basePackage.repository;


import basePackage.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    Page<Blog> findBlogsByCategory_Name(String name, Pageable pageable);
}
