package basePackage.service;

import basePackage.model.Blog;
import basePackage.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

public interface BlogService {
    Page<Blog> findAll(Pageable pageable);
    Blog findById(Long id);
    void save(Blog model);
    void remove(Long id);
    Page<Blog> findBlogsByCategory_Name(String name,Pageable pageable);
    Page<Blog> findByTitle(String name,Pageable pageable);
    Page<Blog> findByAuthor(String name,Pageable pageable);
}
