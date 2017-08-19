package entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import core.xml.converter.DateConverter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-06-25.
 */
@XStreamAlias("book")
public class Book extends XmlEntity {
    
    @XStreamAsAttribute
    private String id;
    
    private String author;
    
    private String title;
    
    private String genre;
    
    private BigDecimal price;
    
    @XStreamConverter(DateConverter.class)
    @XStreamAlias("pub-date")
    private Date pubDate;
    
    private String review;
    
    private BookType type;
    
    private Book(Builder builder) {
        id = builder.id;
        author = builder.author;
        title = builder.title;
        genre = builder.genre;
        price = builder.price;
        pubDate = builder.pubDate;
        review = builder.review;
        type = builder.type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", pubDate=" + pubDate +
                ", review='" + review + '\'' +
                ", type=" + type +
                '}';
    }
    
    public static final class Builder {
        
        private String id;
        private String author;
        private String title;
        private String genre;
        private BigDecimal price;
        private Date pubDate;
        private String review;
        private BookType type;

        public Builder id(String id) {
            this.id = id;
            
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            
            return this;
        }

        public Builder pubDate(Date pubDate) {
            this.pubDate = pubDate;
            
            return this;
        }

        public Builder review(String review) {
            this.review = review;
            
            return this;
        }

        public Builder type(BookType type) {
            this.type = type;
            
            return this;
        }
        
        public Book build() {
            return new Book(this);
        }
    }
}
