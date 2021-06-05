package me.siyer.spring5webapp.bootstrap;

import me.siyer.spring5webapp.domain.Author;
import me.siyer.spring5webapp.domain.Book;
import me.siyer.spring5webapp.domain.Publisher;
import me.siyer.spring5webapp.repositories.AuthorRepository;
import me.siyer.spring5webapp.repositories.BookRepository;
import me.siyer.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        System.out.println("Started Bootstrap");
        Publisher pub1 = new Publisher("Publisher1", "111 street", "Navi Mumbai", "111221");
        publisherRepository.save(pub1);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(pub1);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB  = new Book("J2EE Development without EJB","23233244");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(pub1);

        authorRepository.save(rod);
        bookRepository.save(noEJB);


        System.out.println("Number of books: "+bookRepository.count());
        System.out.println("Number of publisher: "+publisherRepository.count());
    }
}
