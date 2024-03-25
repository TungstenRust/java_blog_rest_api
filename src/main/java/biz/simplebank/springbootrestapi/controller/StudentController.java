package biz.simplebank.springbootrestapi.controller;
import biz.simplebank.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    //http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "Shahin",
                "Safaroff"
        );
        return ResponseEntity.ok().header("custom-header", "Shagen").body(student);
    }
    //http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student>students = new ArrayList<>();
        students.add(new Student(1, "Shahin", "Safaroff"));
        students.add(new Student(2, "Asad", "Safaroff"));
        students.add(new Student(3, "Mukhtar", "Safaroff"));
        return ResponseEntity.ok(students);
    }
    //REST API with Path Variable
    //{id} - URI template variable
    //http://localhost:8080/students/1
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name")String lastName){
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }
    //REST API with Request Param
    //http://localhost:8080/students/query?id=1&firstName=Shahin&lastName=Safaroff
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName){
        Student student= new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    //REST API that handles HTTP POST Request - creating new resource
    // @PostMapping and @RequestBody
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
    // REST API that handles HTTP Put Request -updating existing resource
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }
    //REST API handles HTTP DELETE Request - deleting the existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id")int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
