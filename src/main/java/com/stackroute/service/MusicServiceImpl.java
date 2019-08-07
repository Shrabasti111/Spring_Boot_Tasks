package com.stackroute.service;


import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicServiceImpl implements MusicService, ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {


    @Value("${track.1.name:default}")
    String name1;
    @Value("${track.1.comments:default}")
    String comments1;
    @Value("${track.2.name:default}")
    String name2;
    @Value("${track.2.comments:default}")
    String comments2;


    private MusicRepository musicRepository;

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
    public List<Track> getTrack() throws TrackNotFoundException {
        List<Track> trackList = musicRepository.findAll();
        if(trackList.isEmpty()) {
            throw new TrackNotFoundException("No tracks found");
        }
        return trackList;
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
    public boolean deleteById(int id) throws TrackNotFoundException {

        Optional<Track> getByIdTrack = musicRepository.findById(id);

        if(getByIdTrack.isEmpty()) {
            throw new TrackNotFoundException("Track does not exist");
        }
        musicRepository.deleteById(id);
        return true;

    }

    @Override
    public Track updateById(Track track, int id) throws TrackNotFoundException {

        Optional<Track> updateTrack = musicRepository.findById(id);

        if(updateTrack.isEmpty()) {
            throw new TrackNotFoundException("Track does not exist");
        }


        track.setId(id);
        musicRepository.save(track);
        return updateTrack.get();
    }

    @Override
    public List<Track> getTrackByName(String name) throws TrackNotFoundException {

        List<Track> user_id = musicRepository.getTrackByName(name);

        if(user_id.isEmpty()) {
            throw new TrackNotFoundException("Track not found");
        }

        return user_id;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        musicRepository.save(new Track(1, name1, comments1));
        musicRepository.save(new Track(2, name2, comments2));
    }

    @Override
    public void run(String... args) throws Exception {

    }
}


