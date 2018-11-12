package com.example.hubert.gameoflife.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.hubert.gameoflife.model.User;

@Entity(tableName = "users")
public class UserEntity implements User {
    @PrimaryKey
    private int id;
    private int cash;
    private int avatarId;
    private boolean isMen;
    private String name;
    private String everythingElse;


    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    @Override
    public boolean isMen() {
        return isMen;
    }

    public void setMen(boolean men) {
        isMen = men;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEverythingElse() {
        return everythingElse;
    }

    public void setEverythingElse(String everythingElse) {
        this.everythingElse = everythingElse;
    }

    public UserEntity() {}

    public UserEntity(int id, int cash, int avatarId, boolean isMen, String name, String everythingElse) {
        this.id = id;
        this.cash = cash;
        this.avatarId = avatarId;
        this.isMen = isMen;
        this.name = name;
        this.everythingElse = everythingElse;
    }

    public UserEntity(User user) {
        this.id = user.getId();
        this.cash = user.getCash();
        this.avatarId = user.getAvatarId();
        this.isMen = user.isMen();
        this.name = user.getName();
        this.everythingElse = user.getEverythingElse();
    }
}
