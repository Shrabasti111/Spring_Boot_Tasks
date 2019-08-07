package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MusicRepository extends MongoRepository<Track, Integer> {

    @Query("{name: '?0'}")
    List<Track> getTrackByName(String name);
}
