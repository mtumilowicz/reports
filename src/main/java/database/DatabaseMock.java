package database;

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
        Book book1 = new Book.Builder().id("ISBN1")
                .author("Dostoyevsky Fyodor")
                .title("Crime and Punishment")
                .genre("Plays")
                .price(null)
                .pubDate(new Date())
                .review("5/5")
                .type(BookType.ALL)
                .build();

        Book book2 = new Book.Builder().id("ISBN2")
                .author("Tolstoy Leo")
                .title("War and Peace")
                .genre("Novels")
                .price(BigDecimal.TEN)
                .pubDate(new Date())
                .review("4/5")
                .type(BookType.ELECTRONIC)
                .build();

        Book book3 = new Book.Builder().id("ISBN3")
                .author("Hemingway Ernest")
                .title("For Whom the Bell Tolls")
                .genre("Novels")
                .price(BigDecimal.TEN)
                .pubDate(new Date())
                .review("5/5")
                .type(BookType.PAPER)
                .build();

        Book book4 = new Book.Builder().id("ISBN4")
                .author("Bukowski Charles")
                .title("Factotum")
                .genre("Novels")
                .price(BigDecimal.TEN)
                .pubDate(new Date())
                .review("4/5")
                .type(BookType.PAPER)
                .build();

        Book book5 = new Book.Builder().id("ISBN5")
                .author("Mrożek Sławomir")
                .title("Tango")
                .genre("Plays")
                .genre("Novels")
                .price(BigDecimal.TEN)
                .pubDate(new Date())
                .review("3/5")
                .type(BookType.PAPER)
                .build();
        
        return new LinkedList<>(Arrays.asList(book1,
                                                book2,
                                                book3,
                                                book4,
                                                book5));
    }
}
