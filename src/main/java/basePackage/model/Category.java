package basePackage.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "Category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Blog.class)
    private List<Blog> blogList;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    @Override
    public String toString (){
        return this.name;
    }
}
