package com.example.lab10.Controller;


import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-post")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity getAllJobPosts(){
        return ResponseEntity.ok(jobPostService.getAllJobPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@RequestBody @Valid JobPost jobPost , Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(201).body(new ApiResponse("Job Post added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id , @RequestBody @Valid JobPost jobPost , Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        if(jobPostService.updateJobPost(id,jobPost)) return ResponseEntity.ok(new ApiResponse("Job Post updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Job Post not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id){
        if(jobPostService.deleteJobPost(id)) return ResponseEntity.ok(new ApiResponse("Job Post deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Job Post not found"));
    }

}
