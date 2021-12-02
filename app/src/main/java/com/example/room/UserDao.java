package com.example.room;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void insert(UserInfo userInfo);

    @Delete
    void delete(UserInfo userInfo);

    @Delete
    void reset(List<UserInfo> list);

    @Query("UPDATE user SET name = :sName WHERE ID = :sID")
    void update(int sID, String sName);

    @Query("SELECT * FROM user")
    List<UserInfo> getAll();

    @Update
    int update(List<UserInfo>list);



}
