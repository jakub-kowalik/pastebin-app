package pl.jakubkowalik.springpastebin.entry.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class EntryAddRequest {
    @NotNull
    @Size(min = 1)
    private String entryCode;
    private String password;
}
