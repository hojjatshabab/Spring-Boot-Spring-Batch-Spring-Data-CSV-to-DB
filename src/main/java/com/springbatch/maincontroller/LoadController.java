package com.springbatch.maincontroller;

import lombok.Data;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

@RestController
@RequestMapping("/load")
@Data
public class LoadController {

    private final JobLauncher jobLauncher;
    private final Job job;

    @GetMapping
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Long firstTime = currentTimeMillis();
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("Batch is Running...");

        System.out.println("JobExecution: " + jobExecution.getStatus());

        double totalTimeMillis = currentTimeMillis() - firstTime;
        BigDecimal totalTimeMinutes = BigDecimal.valueOf(totalTimeMillis/60000)
                .setScale(2, RoundingMode.HALF_EVEN);
        System.out.println(String.format("Time convert completed on %s minute",totalTimeMinutes));

        return jobExecution.getStatus();
    }
}




