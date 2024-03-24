package biz.simplebank.springbootrestapi.controller;

import biz.simplebank.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    //http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student(
                1,
                "Shahin",
                "Safaroff"
        );
        return student;
    }
    //http://localhost:8080/students
    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student>students = new ArrayList<>();
        students.add(new Student(1, "Shahin", "Safaroff"));
        students.add(new Student(2, "Asad", "Safaroff"));
        students.add(new Student(3, "Mukhtar", "Safaroff"));
        return students;
    }
    //REST API with Path Variable
    //{id} - URI template variable
    //http://localhost:8080/students/1
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name")String lastName){
        return new Student(studentId, firstName, lastName);
    }
    //REST API with Request Param
    //http://localhost:8080/students/query?id=1&firstName=Shahin&lastName=Safaroff
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName){
        return new Student(id, firstName, lastName);
    }

    //REST API that handles HTTP POST Request - creating new resource
    // @PostMapping and @RequestBody
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }
    // REST API that handles HTTP Put Request -updating existing resource
    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }
}
