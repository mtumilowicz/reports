package entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import core.xml.converter.DateOnlyConverter;

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
    
    @XStreamConverter(DateOnlyConverter.class)
    @XStreamAlias("pub-date")
    private Date pubDate;
    
    private String review;
    
    private BookType type;

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
        return Objects.equals(id, book.id) &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(price, book.price) &&
                Objects.equals(pubDate, book.pubDate) &&
                Objects.equals(review, book.review) &&
                type == book.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, genre, price, pubDate, review, type);
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
}
