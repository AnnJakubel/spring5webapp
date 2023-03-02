package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Author cal = new Author("Cal", "Newport");
        Book good = new Book("So Good They Can't Ignore You", "123123");
        cal.getBooks().add(good);
        good.getAuthors().add(cal);

        authorRepository.save(cal);
        bookRepository.save(good);

        Author liane = new Author("Liane", "Moriarty");
        Book nine = new Book("Nine Perfect Strangers", "123124");
        liane.getBooks().add(nine);
        nine.getAuthors().add(liane);

        authorRepository.save(liane);
        bookRepository.save(nine);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
    }
}
