package pl.jakubkowalik.springpastebin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.jakubkowalik.springpastebin.entry.Entry;
import pl.jakubkowalik.springpastebin.entry.EntryRepository;

import java.time.LocalDateTime;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EntryRepository repository;

    @Autowired
    public DatabaseLoader(EntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {

        for (int i = 0; i < 10; i++) {
            Entry entry = new Entry("test" + i);
            entry.setLocalDateTime(LocalDateTime.now());
            this.repository.save(entry);
        }
    }
}
