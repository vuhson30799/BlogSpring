package basePackage.repository;


import basePackage.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    Page<Blog> findBlogsByCategory_Name(String name, Pageable pageable);
    Page<Blog> findByTitle(String name,Pageable pageable);
    Page<Blog> findByAuthor(String name,Pageable pageable);
    @Query(value = "select blog from Blog blog order by rand()")
    Page<Blog> findRandom(Pageable pageable);
}
