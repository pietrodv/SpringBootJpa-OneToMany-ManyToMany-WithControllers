package reldb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reldb.ResourceNotFoundException;
import reldb.models.Teacher;
import reldb.repositories.TeacherRepository;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/teachers")
public class TeachersController {

    @Autowired
    TeacherRepository teacherRepository;

    // The root path will show all users
    @GetMapping("")
    public @ResponseBody Iterable<Teacher> getAllTeachers() {
        // This returns a JSON or XML with the users
        return teacherRepository.findAll();
    }

    @PostMapping("/add")
    public @ResponseBody
    String addNewTeacher(@Valid @RequestParam String name, @RequestParam String instrument) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Teacher n = new Teacher();
        n.setName(name);
        n.setInstrument(instrument);
        teacherRepository.save(n);
        return "Teacher Saved";
    }

    @PutMapping("{teacher_id}")
    public @ResponseBody String editTeacher(@Valid @PathVariable(value = "teacher_id") Integer teacherId, String name) {
        teacherRepository.findById(teacherId).map(user -> {
            user.setName(name);
            return teacherRepository.save(user);
        });
        return "Edit successful";
    }

    @DeleteMapping("/{teacher_id}")
    public ResponseEntity<?> deleteTeacherById(@Valid @PathVariable (value = "teacher_id") Integer teacherId) {
        return teacherRepository.findById(teacherId).map(teacher -> {
            teacherRepository.delete(teacher);
            return new ResponseEntity<>("teacher with id " + teacherId + " deleted", OK );
        }).orElseThrow(() -> new ResourceNotFoundException("Teacher with id " + teacherId + " not found"));
    }
}
