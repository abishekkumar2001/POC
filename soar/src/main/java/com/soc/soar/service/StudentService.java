package com.soc.soar.service;

import com.soc.soar.dto.StudentDTO;
import com.soc.soar.entity.Student;
import com.soc.soar.exception.StudentNotFoundException;
import com.soc.soar.repository.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    public Student addStudent(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        return studentRepo.save(student);
    }

    public Student getStudentById(int id) {
        return studentRepo.findById(id).get();
    }

    public Student updateStudent(Student student) {
        Optional<Student> stud = studentRepo.findById(student.getId());

        if (stud.isEmpty()) {
            throw new StudentNotFoundException("Student not found with id " + student.getId());
        }
        return studentRepo.save(student);
    }

    public String deleteStudent(int id) {

        if(!studentRepo.existsById(id)){
            throw new StudentNotFoundException("Cannot delete student with ID " + id + " No student Found");
        }
        studentRepo.deleteById(id);
        return "Deleted Successfully";
    }

    public Student getStudentByName(String name) {
        return studentRepo.findByName(name);
    }

    public List<Student> searchStudent(String name) {
        return studentRepo.findByNameStartingWithIgnoreCase(name);
    }

    public Page<Student> getStudents(Pageable pageable) {
        return studentRepo.findAll(pageable);
    }
}
