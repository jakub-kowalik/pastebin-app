package pl.jakubkowalik.springpastebin.entry;

import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.jakubkowalik.springpastebin.entry.exception.EntryNotFoundException;
import pl.jakubkowalik.springpastebin.entry.exception.EntryWrongPasswordException;
import pl.jakubkowalik.springpastebin.entry.request.EntryModifyRequest;
import pl.jakubkowalik.springpastebin.entry.request.EntryAddRequest;
import pl.jakubkowalik.springpastebin.entry.request.EntryResponse;
import pl.jakubkowalik.springpastebin.entry.request.EntryUpdateRequest;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/entries")
public class EntryController {

    private final EntryService entryService;

    @GetMapping
    public Page<EntryResponse> getRegionsPage(
            @PageableDefault(size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "localDateTime", direction = Sort.Direction.DESC)
            })
                    Pageable pageable) {
        return entryService.getEntriesPage(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<EntryResponse> getEntry(@PathVariable String id) {
        try {
            EntryResponse entryEntity = entryService.getEntry(id);
            return new ResponseEntity<>(entryEntity, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EntryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<EntryResponse> addEntry(@Valid @RequestBody EntryAddRequest entry) {
        try {
            EntryResponse newEntry = entryService.addEntry(entry);

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
    public ResponseEntity<Entry> updateEntry(@Valid @RequestBody EntryUpdateRequest entry, @PathVariable String id) {
        try {
            return new ResponseEntity<>(entryService.updateEntry(entry, id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EntryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (EntryWrongPasswordException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Entry> modifyEntry(@PathVariable String id, @Valid @RequestBody EntryModifyRequest entryModifyRequest) {
        try {
            return new ResponseEntity<>(entryService.modifyEntry(id, entryModifyRequest), HttpStatus.OK);
        } catch (EntryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (JsonMappingException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EntryWrongPasswordException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
