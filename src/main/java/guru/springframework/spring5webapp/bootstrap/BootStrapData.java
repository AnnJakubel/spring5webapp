package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Publisher varrak = new Publisher(
                "Varrak",
                "Tammsaare tee 1",
                "Tallinn", "Estonia",
                1233);

        Publisher heaLugu = new Publisher(
                "Hea Lugu",
                "J.Poska 1",
                "Tallinn", "Estonia",
                1233);

        publisherRepository.save(varrak);
        publisherRepository.save(heaLugu);

        Author cal = new Author("Cal", "Newport");
        Book good = new Book("So Good They Can't Ignore You", "123123");
        cal.getBooks().add(good);
        good.getAuthors().add(cal);
        good.setPublisher(heaLugu);

        authorRepository.save(cal);
        bookRepository.save(good);

        Author liane = new Author("Liane", "Moriarty");
        Book nine = new Book("Nine Perfect Strangers", "123124");
        liane.getBooks().add(nine);
        nine.getAuthors().add(liane);
        nine.setPublisher(varrak);

        authorRepository.save(liane);
        bookRepository.save(nine);

        varrak.getBooks().add(nine);
        heaLugu.getBooks().add(good);

        publisherRepository.save(varrak);
        publisherRepository.save(heaLugu);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("The amount of Publishers: " + publisherRepository.count());
        System.out.println("Varrak number of books: " + varrak.getBooks().size());
    }
}
