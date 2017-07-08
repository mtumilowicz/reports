package dao;

import entity.Book;

import java.util.List;

import static database.DatabaseMock.getAllBooks;

/**
 * Created by mtumilowicz on 2017-07-05.
 */
public class BookDAO {
    public static List<Book> getAllEntities() {
        return getAllBooks();
    }
}
