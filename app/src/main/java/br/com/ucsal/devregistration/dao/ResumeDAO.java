package br.com.ucsal.devregistration.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.ucsal.devregistration.domain.Resume;

/**
 * Interface responsável por gerar o sql e fazer as queries solicitadas ao banco de dados.
 */
@Dao
public interface ResumeDAO {
    /**
     * @return todos os currículos que estão cadastrados no banco de dados.
     */
    @Query("SELECT * FROM resume")
    List<Resume> findAll();

    /**
     * Método responsável por inserir um Currículo no banco de dados.
     *
     * @param resume
     */
    @Insert
    void insert(Resume resume);

    /**
     * Método responsável por deletar um Currículo do banco de dados.
     *
     * @param resume
     */
    @Delete
    void delete(Resume resume);

}
