package pl.jakubkowalik.springpastebin.entry;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entries")
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping()
    public List<Entry> getAllEntries() {
        return entryService.getAllEntries();
    }

    @GetMapping("{id}")
    public Optional<Entry> getEntry(@PathVariable String id) {
        return entryService.getEntry(id);
    }

    @PostMapping()
    public ResponseEntity<?> addEntry(@RequestBody Entry entry) {
        Entry newEntry = entryService.addEntry(entry);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{entryId}")
                .buildAndExpand(newEntry.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public void deleteEntry(@PathVariable String id) {
        entryService.deleteEntry(id);
    }

    @PutMapping("{id}")
    public Optional<Entry> updateEntry(@RequestBody Entry entry, @PathVariable String id) {
           return entryService.updateEntry(entry, id);
    }
}
