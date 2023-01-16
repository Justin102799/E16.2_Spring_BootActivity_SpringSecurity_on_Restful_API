package com.example.demo.Controller;

import com.example.demo.Entity.StudentEntity;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;



    @PostMapping("/student")
    public @ResponseBody String addStudents (@RequestParam String studentFname,
                                             @RequestParam String studentLname,
                                             @RequestParam String studentEmail,
                                             @RequestParam String studentCourse,
                                             @RequestParam String gpa){

        return studentService.createStudent(studentFname, studentLname, studentEmail, studentCourse, gpa);
    }

    @GetMapping("/students")
    public @ResponseBody Iterable<StudentEntity> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping("/studentid")
    public ResponseEntity<List<StudentEntity>> getById (@RequestParam int id){
        return studentService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<List<StudentEntity>> deleteStudent(@PathVariable int id){
        return new ResponseEntity<>(studentRepository.deleteByid(id), HttpStatus.OK);
    }

    @PutMapping("/update/{student_id}")
    public ResponseEntity<StudentEntity> updateTask(@PathVariable int student_id,@RequestBody StudentEntity studentDetails ){
        StudentEntity studentEntity = studentRepository.getById((long) student_id);

        studentEntity.setStudentFname(studentDetails.getStudentFname());
        studentEntity.setStudentLname(studentDetails.getStudentLname());
        studentEntity.setStudentEmail(studentDetails.getStudentEmail());
        studentEntity.setStudentCourse(studentDetails.getStudentCourse());
        studentEntity.setGpa(studentDetails.getGpa());
        return new ResponseEntity<>(studentRepository.save(studentEntity), HttpStatus.OK);
    }


}







