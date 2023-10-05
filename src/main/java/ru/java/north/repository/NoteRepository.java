package ru.java.north.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.java.north.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
