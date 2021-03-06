package pl.jakubkowalik.springpastebin.entry;

import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.jakubkowalik.springpastebin.entry.exception.EntryNotFoundException;
import pl.jakubkowalik.springpastebin.entry.exception.EntryWrongPasswordException;
import pl.jakubkowalik.springpastebin.entry.request.EntryModifyRequest;
import pl.jakubkowalik.springpastebin.entry.request.EntryAddRequest;
import pl.jakubkowalik.springpastebin.entry.request.EntryResponse;
import pl.jakubkowalik.springpastebin.entry.request.EntryUpdateRequest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class EntryService {

    private final EntryRepository entryRepository;

    public Page<EntryResponse> getEntriesPage(Pageable pageable) {
        return entryRepository.findAll(pageable).map(EntryFactory::entityToResponse);
    }

    public EntryResponse getEntry(String id) {
        Entry entry = entryRepository.findById(id).orElseThrow(EntryNotFoundException::new);
        return EntryFactory.entityToResponse(entry);
    }

    @Transactional
    public EntryResponse addEntry(EntryAddRequest entry) {
        return EntryFactory.entityToResponse(entryRepository.save(EntryFactory.requestToEntity(entry)));
    }

    @Transactional
    public void deleteEntry(String id) {
        entryRepository.deleteById(id);
    }

    @Transactional
    public Entry updateEntry(EntryUpdateRequest entry, String id) {
        Entry oldEntry = entryRepository.findById(id).orElseThrow(EntryNotFoundException::new);

        if (oldEntry.getPassword().equals(entry.getPassword())) {
            oldEntry.setLocalDateTime(LocalDateTime.now());
            oldEntry.setEntryCode(entry.getEntryCode());
            if (entry.getNewPassword() != null)
                oldEntry.setPassword(entry.getNewPassword());
        } else
            throw new EntryWrongPasswordException();

        return entryRepository.save(oldEntry);
    }

    @Transactional
    public Entry modifyEntry(String id, EntryModifyRequest entryModifyRequest) throws JsonMappingException {
        Entry entry = entryRepository.findById(id).orElseThrow(EntryNotFoundException::new);

        if (entryModifyRequest.getPassword().isPresent())
            if (entry.getPassword().equals(entryModifyRequest.getPassword().get())) {
                if (entryModifyRequest.getEntryCode().isPresent())
                    if (entryModifyRequest.getEntryCode().get() != null) {
                        entry.setEntryCode(entryModifyRequest.getEntryCode().get());
                        entry.setLocalDateTime(LocalDateTime.now());
                    }
                if (entryModifyRequest.getNewPassword().isPresent())
                    if (entryModifyRequest.getNewPassword().get() != null)
                        entry.setPassword(entryModifyRequest.getNewPassword().get());
            } else
                throw new EntryWrongPasswordException();

        return entryRepository.save(entry);
    }
}
