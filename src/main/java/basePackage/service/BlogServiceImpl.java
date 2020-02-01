package basePackage.service;

import basePackage.model.Blog;
import basePackage.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import basePackage.repository.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public void save(Blog model) {
        blogRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        blogRepository.delete(id);
    }

    @Override
    public Page<Blog> findBlogsByCategory_Name(String name, Pageable pageable) {
        return blogRepository.findBlogsByCategory_Name(name,pageable);
    }

    @Override
    public Page<Blog> findByTitle(String name, Pageable pageable) {
        return blogRepository.findByTitle(name,pageable);
    }

    @Override
    public Page<Blog> findByAuthor(String name, Pageable pageable) {
        return blogRepository.findByAuthor(name,pageable);
    }

    @Override
    public Page<Blog> findRandom(Pageable pageable) {
        return blogRepository.findRandom(pageable);
    }


}
