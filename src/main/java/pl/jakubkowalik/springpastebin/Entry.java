package pl.jakubkowalik.springpastebin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Entry {

    private @Id @GeneratedValue Long id;
    private String code;
    private LocalDateTime localDateTime;

    public Entry() { }

    public Entry(String code) {
        this.code = code;
        this.localDateTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return id.equals(entry.id) &&
                code.equals(entry.code) &&
                localDateTime.equals(entry.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, localDateTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
                ", code='" + code + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
