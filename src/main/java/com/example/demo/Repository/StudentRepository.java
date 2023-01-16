package com.example.demo.Repository;

import com.example.demo.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findByid(int id);
    List<StudentEntity> deleteByid(int id);

    //List<List<StudentEntity>> deleteById(List<StudentEntity> byid);
    //void deleteStudentById(int id);
}

