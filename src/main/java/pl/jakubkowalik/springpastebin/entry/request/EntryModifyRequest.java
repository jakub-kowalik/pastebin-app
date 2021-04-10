package pl.jakubkowalik.springpastebin.entry.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class EntryModifyRequest {
    @NotNull
    @Size(min = 1)
    private JsonNullable<String> entryCode = JsonNullable.undefined();
    private JsonNullable<String> password = JsonNullable.undefined();
    private JsonNullable<String> newPassword = JsonNullable.undefined();
}
