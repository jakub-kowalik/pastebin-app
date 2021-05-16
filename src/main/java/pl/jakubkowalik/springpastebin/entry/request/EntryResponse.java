package pl.jakubkowalik.springpastebin.entry.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EntryResponse {
    private String id;
    private String entryCode;
    private LocalDateTime localDateTime;
}
