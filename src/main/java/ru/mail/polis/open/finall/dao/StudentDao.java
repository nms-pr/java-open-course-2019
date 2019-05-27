package ru.mail.polis.open.finall.dao;

import java.util.List;

import ru.mail.polis.open.finall.model.Student;

public interface StudentDao {

    void saveStudent(Student stud) throws Exception;

    List<Student> getAllStudents() throws Exception;
    
    void updateStudent(Student stud) throws Exception;

    void delete(Long id) throws Exception;

}
