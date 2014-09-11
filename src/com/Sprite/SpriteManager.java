package com.Sprite;

import android.util.SparseArray;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SpriteManager {
    static private SpriteManager _instance;

    ConcurrentHashMap<Integer, ConcurrentHashMap<UUID, ISprite>> _sprites;

    private SpriteManager(){
       _sprites = new ConcurrentHashMap<Integer, ConcurrentHashMap<UUID, ISprite>>();
    }

    public static SpriteManager GetInstance(){
        if(_instance == null)
            _instance = new SpriteManager();

        return _instance;
    }

    public SimpleSpriteToken AddSimpleSprite(String spriteName, int layer){
        ConcurrentHashMap<UUID, ISprite> targetList = _sprites.get(layer);

        if(targetList == null) {
            targetList = new ConcurrentHashMap<UUID, ISprite>();

            SimpleSprite fab = new SimpleSprite(spriteName);
            targetList.put(fab.GetId(), fab);

            _sprites.put(layer, targetList);

            return new SimpleSpriteToken(fab, layer);
        } else {
            SimpleSprite fab = new SimpleSprite(spriteName);
            targetList.put(fab.GetId(), fab);

            return new SimpleSpriteToken(fab, layer);
        }
    }

    public TextSpriteToken AddTextSprite(String spriteName, int layer){
        ConcurrentHashMap<UUID, ISprite> targetList = _sprites.get(layer);

        if(targetList == null) {
            targetList = new ConcurrentHashMap<UUID, ISprite>();

            TextSprite fab = new TextSprite(spriteName);
            targetList.put(fab.GetId(), fab);

            _sprites.put(layer, targetList);

            return new TextSpriteToken(fab, layer);
        } else {
            TextSprite fab = new TextSprite(spriteName);
            targetList.put(fab.GetId(), fab);

            return new TextSpriteToken(fab, layer);
        }
    }

    public AnimatedSpriteToken AddAnimatedSprite(String spriteName, int layer){
        ConcurrentHashMap<UUID, ISprite> targetList = _sprites.get(layer);

        if(targetList == null) {
            targetList = new ConcurrentHashMap<UUID, ISprite>();

            AnimatedSprite fab = new AnimatedSprite(spriteName);
            targetList.put(fab.GetId(), fab);

            _sprites.put(layer, targetList);

            return new AnimatedSpriteToken(fab, layer);
        } else {
            AnimatedSprite fab = new AnimatedSprite(spriteName);
            targetList.put(fab.GetId(), fab);

            return new AnimatedSpriteToken(fab, layer);
        }
    }

    public ScrollingSpriteToken AddScrollingSprite(String spriteName, int layer){
        ConcurrentHashMap<UUID, ISprite> targetList = _sprites.get(layer);

        if(targetList == null) {
            targetList = new ConcurrentHashMap<UUID, ISprite>();

            ScrollingSprite fab = new ScrollingSprite(spriteName);
            targetList.put(fab.GetId(), fab);

            _sprites.put(layer, targetList);

            return new ScrollingSpriteToken(fab, layer);
        } else {
            ScrollingSprite fab = new ScrollingSprite(spriteName);
            targetList.put(fab.GetId(), fab);

            return new ScrollingSpriteToken(fab, layer);
        }
    }

    public void DeleteSprite(ISpriteToken token){
        ConcurrentHashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return;

        spriteList.remove(token.GetId());
    }

    public void Update(float elapsedTime){
        for(int i = 0; i < _sprites.size(); i++){
            ConcurrentHashMap<UUID, ISprite> currentMap = _sprites.get(_sprites.keySet().toArray()[i]);
            for(UUID id : currentMap.keySet()){
                currentMap.get(id).Update(elapsedTime);
            }
        }
    }

    public void Draw(FrameBuffer fb){
        for(int i = 0; i < _sprites.size(); i++){
            ConcurrentHashMap<UUID, ISprite> currentMap = _sprites.get(_sprites.keySet().toArray()[i]);
            Set<UUID> keySet = currentMap.keySet();
            for(UUID id : keySet){
                currentMap.get(id).Draw(fb);
            }
        }
    }

    public void UpdateSpritePosition(SimpleVector newPosition, ISpriteToken token){
        ConcurrentHashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return;

        ISprite subject = spriteList.get(token.GetId());
        if(subject == null)
            return;

        subject.SetPosition(newPosition);
    }

    public void UpdateSpriteScale(float scale, ISpriteToken token){
        ConcurrentHashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return;

        ISprite subject = spriteList.get(token.GetId());
        if(subject == null)
            return;

        subject.SetScale(scale);
    }

    public void UpdateSpriteMessage(String message, ISpriteToken token){
        ConcurrentHashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return;

        ISprite subject = spriteList.get(token.GetId());
        if(subject == null)
            return;

        subject.SetMessage(message);
    }

    public void SwitchSpriteAnimation(int animationIndex, ISpriteToken token){
        ConcurrentHashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return;

        ISprite subject = spriteList.get(token.GetId());
        if(subject == null)
            return;

        subject.SetAnimationIndex(animationIndex);
    }

    public void FireTemporarySpriteAnimation(int animationIndex, ISpriteToken token){
        ConcurrentHashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return;

        ISprite subject = spriteList.get(token.GetId());
        if(subject == null)
            return;

        subject.FireTemporaryAnimation(animationIndex);
    }

    public int GetSpriteScaledPixelWidth(ISpriteToken token){
        ConcurrentHashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return -1;

        ISprite subject = spriteList.get(token.GetId());
        if(subject == null)
            return -1;

        return subject.GetScaledPixelWidth();
    }
    public int GetSpriteScaledPixelHeight(ISpriteToken token){
        ConcurrentHashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return -1;

        ISprite subject = spriteList.get(token.GetId());
        if(subject == null)
            return -1;

        return subject.GetScaledPixelHeight();
    }
    public SimpleVector GetSpriteScaledCentre(ISpriteToken token){
        ConcurrentHashMap<UUID, ISprite> spriteList = _sprites.get(token.GetLayer());

        if(spriteList == null)
            return null;

        ISprite subject = spriteList.get(token.GetId());
        if(subject == null)
            return null;

        return subject.GetScaledCentre();
    }
    
    public void Delete(){
    	_sprites.clear();
    }
}
