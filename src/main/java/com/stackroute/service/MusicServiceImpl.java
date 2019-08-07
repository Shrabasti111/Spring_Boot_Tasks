package com.stackroute.service;


import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicServiceImpl implements MusicService {

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
    public Track getById(int id) throws TrackNotFoundException {


        Optional<Track> getByIdTrack = musicRepository.findById(id);

        if(getByIdTrack.isEmpty()) {
            throw new TrackNotFoundException("Track does not exist");
        }

        return getByIdTrack.get();
    }
    
    @Override
    public List<Track> deleteById(int id) throws TrackNotFoundException {

        Optional<Track> getByIdTrack = musicRepository.findById(id);

        if(getByIdTrack.isEmpty()) {
            throw new TrackNotFoundException("Track does not exist");
        }
        musicRepository.deleteById(id);
        List<Track> trackList = musicRepository.findAll();
        return trackList;

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
    public List<Track> getTrack() throws TrackNotFoundException {
        List<Track> trackList = musicRepository.findAll();
        if(trackList.isEmpty()) {
            throw new TrackNotFoundException("No tracks found");
        }
        return trackList;
    }
    
    

    

    @Override
    public List<Track> getTrackByName(String name) {

        List<Track> user_id = musicRepository.getTrackByName(name);

        return user_id;
    }
}


