package com.example.Board.Repository;

import com.example.Board.Dto.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    @Query("SELECT f " +
            "FROM File f " +
            "WHERE f.file_category=:file_category " +
            "AND f.file_key=:file_key")

    List<File> findFileByFileKey(String file_category, Long file_key);

    // findFileByFileKey("board", 1);

    @Modifying
    @Query("DELETE FROM File f " +
            "WHERE f.file_category = :file_category " +
            "AND f.file_key = :file_key")

    void deleteFileByFileKey(String file_category, Long file_key);


}
