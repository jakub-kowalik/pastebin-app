package pl.jakubkowalik.springpastebin.entry;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.jakubkowalik.springpastebin.entry.exception.EntryNotFoundException;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EntryService {

    private final EntryRepository entryRepository;

    public List<Entry> getAllEntries() {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 5);
        return entryRepository.findAllByOrderByLocalDateTimeDesc(firstPageWithTwoElements);
        /*List<Entry> entries = new ArrayList<>();
        entryRepository.findAll().forEach(entries::add);*/
        //return allEntries;
    }

    public Page<Entry> getEntriesPage(Pageable pageable) {
        return entryRepository.findAll(pageable);
    }

    public Optional<Entry> getEntry(String id) {
        return entryRepository.findById(id);
    }

    @Transactional
    public Entry addEntry(Entry entry) {
        entry.setLocalDateTime(LocalDateTime.now());
        return entryRepository.save(entry);
    }

    @Transactional
    public void deleteEntry(String id) {
        entryRepository.deleteById(id);
    }

    @Transactional
    public Entry updateEntry(Entry entry, String id) {
        Entry oldEntry = entryRepository.findById(id).orElseThrow(EntryNotFoundException::new);

        oldEntry.setLocalDateTime(LocalDateTime.now());
        oldEntry.setEntryCode(entry.getEntryCode());

        return entryRepository.save(oldEntry);
    }
}
