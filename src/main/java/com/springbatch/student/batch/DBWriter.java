package com.springbatch.student.batch;

import com.springbatch.student.model.Student;
import com.springbatch.student.repository.StudentRepository;
import lombok.Data;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class DBWriter implements ItemWriter<Student> {

    private final StudentRepository studentRepository;

    @Override
    public void write(List<? extends Student> students) throws Exception{
        studentRepository.saveAll(students);
    }
}
