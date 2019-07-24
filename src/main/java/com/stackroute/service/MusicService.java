package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;

public interface MusicService  {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getTrack();

    public Track getById(int id) throws TrackNotFoundException;

    public void deleteById(int id) throws TrackNotFoundException;

    public boolean updateById(Track track, int id) throws TrackNotFoundException;

    public List<Track> getTrackByName(String name);


}
