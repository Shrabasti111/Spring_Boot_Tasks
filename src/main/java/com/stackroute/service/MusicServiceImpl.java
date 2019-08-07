package com.stackroute.service;

import com.stackroute.domain.Track;
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
    public Track saveTrack(Track track) {

        Track savedTrack = musicRepository.save(track);
        return savedTrack;
    }

    @Override
    public List<Track> getTrack() {
        return (List<Track>)musicRepository.findAll();
    }

    @Override
    public Track getById(int id) {

        Optional<Track> getByIdTrack = musicRepository.findById(id);

        return getByIdTrack.get();
    }

    

    @Override
    public List<Track> getTrackByName(String name) {

        List<Track> user_id = musicRepository.getTrackByName(name);

        return user_id;
    }
    
    @Override
    public List<Track> deleteById(int id) {

        musicRepository.deleteById(id);
        return musicRepository.findAll();
    }

    @Override
    public Track updateById(Track track, int id) {

        Optional<Track> updateTrack = musicRepository.findById(id);

        track.setId(id);
        musicRepository.save(track);
        return updateTrack.get();
    }

}


