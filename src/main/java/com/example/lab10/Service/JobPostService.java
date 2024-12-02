package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPosts(){
        return jobPostRepository.findAll();
    }
    public JobPost getJobPostById(Integer id){
        return jobPostRepository.findById(id).orElse(null);
    }
    public void addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }

    public Boolean updateJobPost(Integer id,JobPost jobPost){
        if(jobPostRepository.existsById(id)){
            jobPost.setId(id);
            jobPostRepository.save(jobPost);
            return true;
        }
        return false;
    }

    public Boolean deleteJobPost(Integer id){
        if(jobPostRepository.existsById(id)){
            jobPostRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
