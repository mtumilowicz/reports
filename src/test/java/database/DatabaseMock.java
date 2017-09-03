package database;

import core.builder.GenericBuilder;
import entity.Book;
import entity.BookType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mtumilowicz on 2017-07-07.
 */
public class DatabaseMock {

    public static List<Book> getAllBooks() {
        Book book1 = GenericBuilder.of(Book::new)
                .with(Book::setId, "ISBN1")
                .with(Book::setAuthor, "Dostoyevsky Fyodor")
                .with(Book::setTitle, "Crime and Punishment")
                .with(Book::setGenre, "Plays")
                .with(Book::setPrice, null)
                .with(Book::setPubDate, new Date())
                .with(Book::setReview, "5/5")
                .with(Book::setType, BookType.ALL)
                .build();

        Book book2 = GenericBuilder.of(Book::new)
                .with(Book::setId, "ISBN2")
                .with(Book::setAuthor, "Tolstoy Leo")
                .with(Book::setTitle, "War and Peace")
                .with(Book::setGenre, "Novels")
                .with(Book::setPrice, BigDecimal.TEN)
                .with(Book::setPubDate, new Date())
                .with(Book::setReview, "4/5")
                .with(Book::setType, BookType.ELECTRONIC)
                .build();

        Book book3 = GenericBuilder.of(Book::new)
                .with(Book::setId, "ISBN3")
                .with(Book::setAuthor, "Hemingway Ernest")
                .with(Book::setTitle, "For Whom the Bell Tolls")
                .with(Book::setGenre, "Novels")
                .with(Book::setPrice, BigDecimal.TEN)
                .with(Book::setPubDate, new Date())
                .with(Book::setReview, "5/5")
                .with(Book::setType, BookType.PAPER)
                .build();

        Book book4 = GenericBuilder.of(Book::new)
                .with(Book::setId, "ISBN4")
                .with(Book::setAuthor, "Bukowski Charles")
                .with(Book::setTitle, "Factotum")
                .with(Book::setGenre, "Novels")
                .with(Book::setPrice, BigDecimal.TEN)
                .with(Book::setPubDate, new Date())
                .with(Book::setReview, "4/5")
                .with(Book::setType, BookType.PAPER)
                .build();

        Book book5 = GenericBuilder.of(Book::new)
                .with(Book::setId, "ISBN5")
                .with(Book::setAuthor, "Mrożek Sławomir")
                .with(Book::setTitle, "Tango")
                .with(Book::setGenre, "Plays")
                .with(Book::setPrice, BigDecimal.TEN)
                .with(Book::setPubDate, new Date())
                .with(Book::setReview, "3/5")
                .with(Book::setType, BookType.PAPER)
                .build();
        
        return new LinkedList<>(Arrays.asList(book1,
                                                book2,
                                                book3,
                                                book4,
                                                book5));
    }
}
