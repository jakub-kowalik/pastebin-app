package pl.jakubkowalik.springpastebin.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EntryService {

    @Autowired
    EntryRepository entryRepository;

    public List<Entry> getAllEntries() {
        List<Entry> entries = new ArrayList<>();
        entryRepository.findAll().forEach(entries::add);
        return entries;
    }

    public Entry getEntry(String id) {
       return entryRepository.findById(UUID.fromString(id)).orElse(null);
    }

    public void addEntry(Entry entry) {
        entryRepository.save(entry);
    }
}
