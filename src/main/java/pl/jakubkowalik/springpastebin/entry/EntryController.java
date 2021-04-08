package pl.jakubkowalik.springpastebin.entry;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.jakubkowalik.springpastebin.entry.exception.EntryNotFoundException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/entries")
public class EntryController {

    private final EntryService entryService;

    @GetMapping
    public Page<Entry> getRegionsPage(
            @PageableDefault(page = 0, size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "localDateTime", direction = Sort.Direction.DESC)
            })
                    Pageable pageable) {
        return entryService.getEntriesPage(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Entry> getEntry(@PathVariable String id) {
        try {
            Optional<Entry> regionEntity = entryService.getEntry(id);
            return regionEntity.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EntryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Entry> addEntry(@RequestBody Entry entry) {
        try {
            Entry newEntry = entryService.addEntry(entry);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{entryId}")
                    .buildAndExpand(newEntry.getId())
                    .toUri();
            return ResponseEntity.created(location).body(newEntry);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EntryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable String id) {
        try {
            entryService.deleteEntry(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EntryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Entry> updateEntry(@RequestBody Entry entry, @PathVariable String id) {
        try {
            return new ResponseEntity<>(entryService.updateEntry(entry, id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EntryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
