package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-application")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getAllJobApplications(){
        return ResponseEntity.ok(jobApplicationService.getAllJobApplications());
    }

    @PostMapping("/add")
    public ResponseEntity addJobApplication(@RequestBody @Valid JobApplication jobApplication , Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        String result = jobApplicationService.addJobApplication(jobApplication);

        if (result.equals("success")) return ResponseEntity.status(201).body(new ApiResponse("Job Application added successfully"));
        else return ResponseEntity.status(400).body(new ApiResponse(result));
    }

    @PostMapping("/apply-job/{userId}/{JobPostId}")
    public ResponseEntity applayJob(@PathVariable Integer userId , @PathVariable Integer JobPostId){

        String result = jobApplicationService.applyJob(userId ,JobPostId);

        if (result.equals("success")) return ResponseEntity.status(201).body(new ApiResponse("Job Applied"));
        else return ResponseEntity.status(400).body(new ApiResponse(result));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobApplication(@PathVariable Integer id , @RequestBody @Valid JobApplication jobApplication , Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        String result = jobApplicationService.updateJobApplication(id,jobApplication);

        if (result.equals("success")) return ResponseEntity.ok(new ApiResponse("Job Application updated successfully"));
        else return ResponseEntity.status(400).body(new ApiResponse(result));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobApplication(@PathVariable Integer id){
        if(jobApplicationService.deleteJobApplication(id)) return ResponseEntity.ok(new ApiResponse("Job Application deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Job Application not found"));
    }
}
