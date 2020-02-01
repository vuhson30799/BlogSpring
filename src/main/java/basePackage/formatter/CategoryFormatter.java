package basePackage.formatter;

import basePackage.model.Category;
import basePackage.repository.CategoryRepository;
import basePackage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormatter implements Formatter<Category> {

//    @Autowired
    private CategoryService categoryService;

//    @Autowired
    public CategoryFormatter(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @Override
    public Category parse(String s, Locale locale) throws ParseException {
        return categoryService.findById(Long.parseLong(s));
    }

    @Override
    public String print(Category category, Locale locale) {
        return "[" + category.getId() + ", " + category.getName() + "]";
    }
}
