package com.example.lab10.Service;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final UserService userService;
    private final JobPostService jobPostService;
    private final JobApplicationRepository jobApplicationRepository;

    public List<JobApplication> getAllJobApplications(){
        return jobApplicationRepository.findAll();
    }
    public JobApplication getJobApplicationById(Integer id){
        return jobApplicationRepository.findById(id).orElse(null);
    }
    public String addJobApplication(JobApplication jobApplication){
        if(userService.getUserById(jobApplication.getUserId()) == null) return "User not found";
        if (jobPostService.getJobPostById(jobApplication.getJobPostId()) == null) return "Job Post not found";

        jobApplicationRepository.save(jobApplication);
        return "success";
    }
    public String applyJob(Integer userId ,Integer jobPostId){
        if(userService.getUserById(userId) == null) return "User not found";
        if (jobPostService.getJobPostById(jobPostId) == null) return "Job Post not found";

        JobApplication jobApplication = new JobApplication();

        jobApplication.setUserId(userId);
        jobApplication.setJobPostId(jobPostId);

        jobApplicationRepository.save(jobApplication);
        return "success";
    }
    public String updateJobApplication(Integer id,JobApplication jobApplication){
        if(userService.getUserById(jobApplication.getUserId()) == null) return "User not found";
        if (jobPostService.getJobPostById(jobApplication.getJobPostId()) == null) return "Job Post not found";

        if(jobApplicationRepository.existsById(id)){
            jobApplication.setId(id);
            jobApplicationRepository.save(jobApplication);
            return "success";
        }
        return "Job Application not found";
    }

    public Boolean deleteJobApplication(Integer id){
        if(jobApplicationRepository.existsById(id)){
            jobApplicationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
