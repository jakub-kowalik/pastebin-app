package pl.jakubkowalik.springpastebin.entry;

import org.springframework.stereotype.Service;

import pl.jakubkowalik.springpastebin.entry.exception.EntryNotFoundException;
import pl.jakubkowalik.springpastebin.entry.request.EntryAddRequest;
import pl.jakubkowalik.springpastebin.entry.request.EntryResponse;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class EntryFactory {
    public static Entry requestToEntity(EntryAddRequest entryAddRequest) throws EntryNotFoundException {
        return Entry.builder()
                .entryCode(entryAddRequest.getEntryCode())
                .password(entryAddRequest.getPassword())
                .localDateTime(LocalDateTime.now(Clock.systemUTC()))
                .build();
    }

    public static EntryResponse entityToResponse(Entry entry) {
        return EntryResponse.builder()
                .id(entry.getId())
                .entryCode(entry.getEntryCode())
                .localDateTime(entry.getLocalDateTime())
                .build();
    }


}
