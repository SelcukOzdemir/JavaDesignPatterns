package sso.designPatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

public class Iterrator {
	public static void main(String[] args) {
        // Kitap koleksiyonu oluşturuluyor
        BookCollection bookCollection = new BookCollection();

        // Koleksiyon üzerinde gezinmek için iterator oluşturuluyor
        Iterator<Book> iterator = bookCollection.createIterator();

        // Kitapları yazdırıyoruz
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println(book);
        }
    }
}
class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book[Title=" + title + ", Author=" + author + ", Year=" + year + "]";
    }
}
 interface Iterator<T> {
    boolean hasNext(); // Sonraki öğe var mı?
    T next();          // Sonraki öğe nedir?
}

 class BookIterator implements Iterator<Book> {
	    private List<Book> books;
	    private int currentIndex = 0;

	    public BookIterator(List<Book> books) {
	        this.books = books;
	    }

	    @Override
	    public boolean hasNext() {
	        return currentIndex < books.size();
	    }

	    @Override
	    public Book next() {
	        if (this.hasNext()) {
	            return books.get(currentIndex++);
	        }
	        return null; // Eğer koleksiyondaki öğeler bitmişse, null döner
	    }
	}
 
 class BookCollection {
	    private List<Book> books;

	    public BookCollection() {
	        books = new ArrayList<>();
	        // Koleksiyona kitap ekliyoruz
	        addBook("The Catcher in the Rye", "J.D. Salinger", 1951);
	        addBook("To Kill a Mockingbird", "Harper Lee", 1960);
	        addBook("1984", "George Orwell", 1949);
	        addBook("Moby Dick", "Herman Melville", 1851);
	    }

	    public void addBook(String title, String author, int year) {
	        books.add(new Book(title, author, year));
	    }

	    public Iterator<Book> createIterator() {
	        return new BookIterator(books);
	    }
	}