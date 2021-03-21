package pl.jakubkowalik.springpastebin.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntryController {

    @Autowired
    private EntryService entryService;

    @RequestMapping("/entries")
    public List<Entry> getEntries() {
        return entryService.getAllEntries();
    }

    @GetMapping("/entries/{id}")
    public Entry getEntry(@PathVariable String id) {
        return entryService.getEntry(id);
    }

    @PostMapping("/entries")
    public void addEntry(@RequestBody Entry entry) {
        entryService.addEntry(entry);
    }
}
