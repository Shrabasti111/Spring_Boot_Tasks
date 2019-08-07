package com.stackroute.service;

import com.stackroute.domain.Track;

import java.util.List;

public interface MusicService  {

    public Track saveTrack(Track track);

    public List<Track> getTrack();

    public Track getById(int id);

    public Track getById(int id);

    public List<Track> deleteById(int id);

    public List<Track> getTrackByName(String name);


}
