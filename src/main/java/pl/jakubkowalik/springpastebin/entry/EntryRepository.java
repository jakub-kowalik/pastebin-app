package pl.jakubkowalik.springpastebin.entry;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EntryRepository extends CrudRepository<Entry, UUID> {

}