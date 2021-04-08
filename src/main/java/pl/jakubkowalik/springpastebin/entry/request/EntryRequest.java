package pl.jakubkowalik.springpastebin.entry.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntryRequest {
    private String entryCode;
    private String password;
}
