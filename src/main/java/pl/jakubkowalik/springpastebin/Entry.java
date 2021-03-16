package pl.jakubkowalik.springpastebin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Entry {

    private @Id @GeneratedValue Long id;
    private String entryCode;
    private LocalDateTime localDateTime;

    public Entry() { }

    public Entry(String entryCode) {
        this.entryCode = entryCode;
        this.localDateTime = LocalDateTime.now();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", code='" + entryCode + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
