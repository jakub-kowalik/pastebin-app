package pl.jakubkowalik.springpastebin.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"password"})
public class Entry {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String entryCode;
    private LocalDateTime localDateTime;
    @JsonProperty("password")
    private String password;

    public Entry() {
    }

    public Entry(String entryCode) {
        this.entryCode = entryCode;
    }

    public Entry(String entryCode, String password) {
        this.entryCode = entryCode;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String code) {
        this.entryCode = code;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return id.equals(entry.id) &&
                entryCode.equals(entry.entryCode) &&
                localDateTime.equals(entry.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entryCode, localDateTime);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", code='" + entryCode + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }

/*    @PrePersist
    void setDate() {
        this.localDateTime = LocalDateTime.now();
    }*/
}
