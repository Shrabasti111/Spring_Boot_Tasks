package com.stackroute.service;


import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.MusicRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MusicServiceTest {

    Track track;

    //Create a mock for UserRepository
    @Mock
    MusicRepository musicRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MusicServiceImpl musicService;
    List<Track> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(20);
        track.setName("parade");

        track.setComments("wow!");
        list = new ArrayList<>();
        list.add(track);


    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {

        when(musicRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = musicService.saveTrack(track);
        assertEquals(track, savedTrack);

        //verify here verifies that userRepository save method is only called once
        verify(musicRepository, times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        when(musicRepository.save((Track) any())).thenReturn(null);
        Track savedTrack = musicService.saveTrack(track);
        System.out.println("savedTrack" + savedTrack);
    }

    @Test
    public void testGetAllTracks() throws TrackNotFoundException {

        musicRepository.save(track);
        //stubbing the mock to return specific data
        when(musicRepository.findAll()).thenReturn(list);
        List<Track> userlist = musicService.getTrack();
        assertEquals(list, userlist);
    }

    @Test
    public void deleteTrackTestSuccess() throws TrackAlreadyExistsException {

        musicRepository.delete(track);
        boolean deletedTrack = musicRepository.existsById(20);
        assertEquals(false, deletedTrack);
    }

    @Test
    public void updateTrackTest() throws TrackNotFoundException {
        when(musicRepository.save((Track) any())).thenReturn(track);
        Track updateTrack = null;
        try {
            updateTrack = musicService.saveTrack(track);
        } catch (TrackAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertEquals(track, updateTrack);
    }


}