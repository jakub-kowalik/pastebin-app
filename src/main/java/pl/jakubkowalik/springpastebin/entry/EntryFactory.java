package pl.jakubkowalik.springpastebin.entry;

import pl.jakubkowalik.springpastebin.entry.exception.EntryNotFoundException;
import pl.jakubkowalik.springpastebin.entry.request.EntryRequest;

import java.time.LocalDateTime;

public class EntryFactory {
    public static Entry dtoToEntity(EntryRequest entryRequest) throws EntryNotFoundException {
        return Entry.builder()
                .entryCode(entryRequest.getEntryCode())
                .password(entryRequest.getPassword())
                .localDateTime(LocalDateTime.now())
                .build();
    }
}
