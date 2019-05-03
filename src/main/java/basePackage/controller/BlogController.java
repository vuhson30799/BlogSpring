package basePackage.controller;

import basePackage.model.Blog;
import basePackage.model.Category;
import basePackage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import basePackage.service.BlogService;
import javax.transaction.Transactional;
import java.util.Date;

@Controller
@Transactional
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Page<Category> categories(Pageable pageable){ return categoryService.findAll(pageable);}

    @GetMapping("/")
    public ModelAndView createBlog(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView();
        Page<Blog> blogPage = blogService.findAll(pageable);
        if (blogPage.getSize() == 0){
            Blog blog = new Blog(new Date());
            modelAndView.setViewName("blog/create");
            modelAndView.addObject("blog",blog);
            return modelAndView;
        }else {
            modelAndView.setViewName("blog/list");
            modelAndView.addObject("blogList",blogPage);
            return modelAndView;
        }

    }
    @PostMapping("/create-blog")
    public ModelAndView create (@Validated @ModelAttribute(name = "blog")Blog blog, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("blog/create");
        if (!bindingResult.hasFieldErrors()){
            blogService.save(blog);
            blog = new Blog(new Date());
            modelAndView.addObject("blog",blog);
            modelAndView.addObject("message","message");
            return modelAndView;
        }else {
            modelAndView.addObject("blog",blog);
            return modelAndView;
        }

    }
    @GetMapping("/create-blog")
    public ModelAndView createForm (Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blog",new Blog(new Date()));
        return modelAndView;
    }
    @GetMapping("/list")
    public ModelAndView listBlog(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("blog/list","blogList",blogService.findAll(pageable));
        modelAndView.addObject("message","Successful");
        return modelAndView;
    }
    @GetMapping("/content/{id}")
    public ModelAndView view(@PathVariable Long id){
        return new ModelAndView("blog/view","blog",blogService.findById(id));
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id,Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blog",blogService.findById(id));
        modelAndView.addObject("categories",categoryService.findAll(pageable));
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView update(@Validated @ModelAttribute(name = "blog")Blog blog,BindingResult bindingResult){

        if (!bindingResult.hasFieldErrors()){
            blogService.save(blog);
            ModelAndView modelAndView = new ModelAndView("blog/view");
            modelAndView.addObject("message","Update successful");
            modelAndView.addObject("blog",blog);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("blog/edit");
            modelAndView.addObject("blog",blog);
            return modelAndView;
        }
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("blog/delete");
        modelAndView.addObject("blog",blogService.findById(id));
        return modelAndView;
    }
    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute(name = "id") Long id,Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("blog/list");
        blogService.remove(id);
        modelAndView.addObject("blogList",blogService.findAll(pageable));
        return modelAndView;
    }

}