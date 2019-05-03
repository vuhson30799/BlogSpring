package basePackage.controller;

import basePackage.model.Category;
import basePackage.service.BlogService;
import basePackage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/category")
    public ModelAndView listCategory(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("category/list");
        modelAndView.addObject("categories",categoryService.findAll(pageable));
        return modelAndView;
    }
    @GetMapping("/create-category")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }
    @PostMapping("/create-category")
    public ModelAndView create(@ModelAttribute(name = "Category")Category category){
        ModelAndView modelAndView = new ModelAndView("category/create");
        categoryService.save(category);
        modelAndView.addObject("message","create successful");
        modelAndView.addObject("category",new Category());
        return modelAndView;
    }
    @GetMapping("/category/{id}")
    public ModelAndView listBlog(@PathVariable Long id,Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("blog/list");
        String name = categoryService.findById(id).getName();
        modelAndView.addObject("blogList",blogService.findBlogsByCategory_Name(name,pageable));
        modelAndView.addObject("category",categoryService.findById(id));
        return modelAndView;
    }
    @GetMapping("/edit-category/{id}")
    public ModelAndView editForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("category/edit");
        modelAndView.addObject("category",categoryService.findById(id));
        return modelAndView;
    }
    @PostMapping("/edit-category/{id}")
    public ModelAndView update(@PathVariable Long id,Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("category/list");
        categoryService.save(categoryService.findById(id));
        modelAndView.addObject("categories",categoryService.findAll(pageable));
        return modelAndView;
    }
    @GetMapping("/delete-category/{id}")
    public ModelAndView delete(@PathVariable Long id,Pageable pageable){
        categoryService.delete(id);
        ModelAndView modelAndView = new ModelAndView("blog/list");
        modelAndView.addObject("blogList",blogService.findAll(pageable));
        return modelAndView;
    }
}
