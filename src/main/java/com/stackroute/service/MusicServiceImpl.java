package com.stackroute.service;


import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicServiceImpl implements MusicService, ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {

    MusicRepository musicRepository;

    @Autowired
    public MusicServiceImpl(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {

        if(musicRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        Track savedTrack = musicRepository.save(track);

        if(savedTrack == null) {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getTrack() {
        return (List<Track>)musicRepository.findAll();
    }

    @Override
    public Track getById(int id) throws TrackNotFoundException {


        Optional<Track> getByIdTrack = musicRepository.findById(id);

        if(getByIdTrack.isEmpty()) {
            throw new TrackNotFoundException("Track does not exist");
        }

        return getByIdTrack.get();
    }

    @Override
    public void deleteById(int id) throws TrackNotFoundException {

        Optional<Track> getByIdTrack = musicRepository.findById(id);

        if(getByIdTrack.isEmpty()) {
            throw new TrackNotFoundException("Track does not exist");
        }
        musicRepository.deleteById(id);

    }

    @Override
    public boolean updateById(Track track, int id) throws TrackNotFoundException {

        Optional<Track> updateTrack = musicRepository.findById(id);

        if(updateTrack.isEmpty()) {
            throw new TrackNotFoundException("Track does not exist");
        }


        track.setId(id);
        musicRepository.save(track);
        return true;
    }

    @Override
    public List<Track> getTrackByName(String name) {

        List<Track> user_id = musicRepository.getTrackByName(name);

        return user_id;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        musicRepository.save(new Track(1, "Good things fall apart", "Illenium"));
        musicRepository.save(new Track(2, "Borderline", "Tame Impala"));
    }

    @Override
    public void run(String... args) throws Exception {

    }
}


