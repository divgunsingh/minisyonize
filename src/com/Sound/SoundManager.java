package com.Sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.concurrent.ConcurrentHashMap;

public class SoundManager {
    private static SoundManager _instance;
    public static SoundManager GetInstance(){
        if(_instance == null)
            _instance = new SoundManager();

        return _instance;
    }

    private SoundPool _soundSpace;
    private ConcurrentHashMap<String, Integer> _soundNames;

    private SoundManager(){
        _soundSpace = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        _soundNames = new ConcurrentHashMap<String, Integer>();
    }

    public void LoadSound(String soundName, int soundResourceId, Context context){
        int soundId = _soundSpace.load(context, soundResourceId, 1);
        _soundNames.put(soundName, soundId);
    }

    public void PlaySound(String soundName){
        if(_soundNames.containsKey(soundName))
            _soundSpace.play(_soundNames.get(soundName), 1, 1, 1, 0, 0);
    }
}
