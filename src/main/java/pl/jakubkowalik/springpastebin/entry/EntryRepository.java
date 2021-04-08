package pl.jakubkowalik.springpastebin.entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface EntryRepository extends PagingAndSortingRepository<Entry, String> {

    List<Entry> findAllByOrderByLocalDateTimeDesc(Pageable pageable);

    Page<Entry> findAll(Pageable pageable);
}