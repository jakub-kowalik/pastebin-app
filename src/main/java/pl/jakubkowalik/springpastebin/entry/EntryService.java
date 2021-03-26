package pl.jakubkowalik.springpastebin.entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
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
        Pageable firstPageWithTwoElements = PageRequest.of(0, 5);
        return entryRepository.findAllByOrderByLocalDateTimeDesc(firstPageWithTwoElements);
        /*List<Entry> entries = new ArrayList<>();
        entryRepository.findAll().forEach(entries::add);*/
        //return allEntries;
    }

    public Optional<Entry> getEntry(String id) {
        return entryRepository.findById(id);
    }

    public Entry addEntry(Entry entry) {
        entry.setLocalDateTime(LocalDateTime.now());
        return entryRepository.save(entry);
    }

    public void deleteEntry(String id) {
        entryRepository.deleteById(id);
    }

    public Optional<Entry> updateEntry(Entry entry, String id) {
        if (entryRepository.findById(id).isPresent()) {
            entry.setId(id);
            entry.setLocalDateTime(LocalDateTime.now());
            entryRepository.save(entry);
        }
        return entryRepository.findById(id);
    }
}
