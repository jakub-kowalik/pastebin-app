package pl.jakubkowalik.springpastebin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.jakubkowalik.springpastebin.entry.Entry;
import pl.jakubkowalik.springpastebin.entry.EntryRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EntryRepository repository;

    @Autowired
    public DatabaseLoader(EntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        this.repository.save(new Entry("test"));
    }
}
