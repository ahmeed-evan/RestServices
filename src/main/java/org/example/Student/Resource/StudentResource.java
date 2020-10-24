package org.example.Student.Resource;


import org.example.Student.Model.Student;
import org.example.Student.Repository.StudentRepository;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("student")
public class StudentResource {

    private StudentRepository studentRepository;

    public StudentResource() throws Exception{
        studentRepository=new StudentRepository();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student>getAllStudents()throws Exception{
        return studentRepository.getAllStudent();
    }

    @POST
    @Path("create_student")
    @Produces(MediaType.APPLICATION_JSON)
    public Student createStudent(Student student)throws Exception{
        return studentRepository.createStudent(student);
    }

}
