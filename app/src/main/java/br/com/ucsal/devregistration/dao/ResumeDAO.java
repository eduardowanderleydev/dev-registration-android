package br.com.ucsal.devregistration.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.ucsal.devregistration.domain.Resume;

@Dao
public interface ResumeDAO {
    @Query("SELECT * FROM resume")
    List<Resume> findAll();

    @Insert
    void insert(Resume resume);

    @Update
    void update(Resume resume);

    @Delete
    void delete(Resume resume);

}
