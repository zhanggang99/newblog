package com.zhanggang.blog.web;

import com.zhanggang.blog.dao.StudentRespository;
import com.zhanggang.blog.po.Student;
import com.zhanggang.blog.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRespository studentRespository;

    @Autowired
    private StudentService studentService;
    @GetMapping("/students")
    public List<Student> studentList(){
        return studentRespository.findAll();
    }

    @PostMapping("/students")
    public Student studentAdd(@RequestParam String name,@RequestParam Integer age){
        Student student = new Student();
        student.setName(name);
        student.setAge(age);

        return studentRespository.save(student);
    }

    @GetMapping("/students/{id}")
    public Student getOneStudent(@PathVariable("id") Integer id){
       Student student = studentRespository.getOne(id);
       return student;
    }

    @GetMapping("/students/age/{age}")
    public List<Student> getStudentsByAge(@PathVariable Integer age){
        return studentRespository.findByAge(age);
    }
    @PutMapping("/students/{id}")
    public Student updateStudent(@RequestParam("id") Integer id,@RequestParam("name") String name,@RequestParam("age") Integer age){
        Student student = new Student();
        student.setId(id);
        student.setAge(age);
        student.setName(name);
        return studentRespository.save(student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") Integer id){
        studentRespository.deleteById(id);
    }

    @PostMapping("/students/two")
    public void insertTow() throws Exception{
        studentService.insertTowStudent();
    }
}
