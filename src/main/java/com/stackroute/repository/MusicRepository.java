package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MusicRepository extends JpaRepository<Track, Integer> {

    @Query(value = "SELECT * FROM Track  where name = ?",
            nativeQuery = true )
    List<Track> getTrackByName(String name);
}
