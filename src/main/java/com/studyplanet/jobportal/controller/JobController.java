package com.studyplanet.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.studyplanet.jobportal.model.Job;
import com.studyplanet.jobportal.repository.JobRepository;

@Controller
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    // 🏠 HOME
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    // 📄 JOBS PAGE
    @GetMapping("/jobs")
    public String viewJobs(Model model) {
        model.addAttribute("jobs", jobRepository.findAll());
        return "jobs";
    }

    // 🛠 ADMIN PAGE
    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("jobs", jobRepository.findAll());
        return "admin";
    }

    // 💾 SAVE JOB
    @PostMapping("/saveJob")
    public String saveJob(@ModelAttribute Job job) {
        jobRepository.save(job);
        return "redirect:/admin";
    }

    // ❌ DELETE
    @GetMapping("/deleteJob/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobRepository.deleteById(id);
        return "redirect:/admin";
    }

    // ✏ EDIT
    @GetMapping("/editJob/{id}")
    public String editJob(@PathVariable Long id, Model model) {
        Job job = jobRepository.findById(id).orElse(null);
        model.addAttribute("job", job);
        model.addAttribute("jobs", jobRepository.findAll());
        return "admin";
    }

    // 🔍 DETAILS
    @GetMapping("/job/{id}")
    public String jobDetails(@PathVariable Long id, Model model) {
        Job job = jobRepository.findById(id).orElse(null);
        model.addAttribute("job", job);
        return "job-details";
    }
}