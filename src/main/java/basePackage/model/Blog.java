package basePackage.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Filter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "Blog")
public class Blog {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private String author;

    private String description;

    @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss")
    private Date datePublish;

    public Blog(){}

    public Blog(Date date){
        this.datePublish = date;
    }

    public Date getDatePublish() {
        return datePublish;
    }

    public void setDatePublish(Date datePublish) {
        this.datePublish = datePublish;
    }

    @ManyToOne
    private Category category;

//    @Override
//    public String toString(){
//        return String.format("Id: Title: %s\nContent: %s\nAuthor: %s\nPublishDate: %s\nDescription: %s"
//                ,title,content,author,publishDate,description);
//    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

//    public Date getPublishDate() {
//        return publishDate;
//    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public void setPublishDate(Date publishDate) {
//        this.publishDate = publishDate;
//    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
