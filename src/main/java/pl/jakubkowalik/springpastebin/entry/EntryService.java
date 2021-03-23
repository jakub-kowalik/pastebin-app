package pl.jakubkowalik.springpastebin.entry;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EntryService {

    EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<Entry> getAllEntries() {
        List<Entry> entries = new ArrayList<>();
        entryRepository.findAll().forEach(entries::add);
        return entries;
    }

    public Optional<Entry> getEntry(String id) {
        return entryRepository.findById(UUID.fromString(id));
    }

    public Entry addEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    public void deleteEntry(String id) {
        entryRepository.deleteById(UUID.fromString(id));
    }
}
