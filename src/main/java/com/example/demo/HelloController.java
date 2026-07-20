package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot is running smoothly with Maven!";
    }
    @GetMapping("/gpa")
    public String calculateGpa(
            @RequestParam List<Double> credits, 
            @RequestParam List<String> grades) {
        
        if (credits.size() != grades.size()) {
            return "Error: The number of credits must match the number of grades entered.";
        }

        double totalPoints = 0;
        double totalCredits = 0;

        for (int i = 0; i < credits.size(); i++) {
            double credit = credits.get(i);
            String grade = grades.get(i);
            
            totalCredits += credit;
            totalPoints += (credit * getGradePoint(grade));
        }

        if (totalCredits == 0) {
            return "Total credits cannot be zero.";
        }
        double gpa = totalPoints / totalCredits;
        return String.format("University of Moratuwa (Faculty of IT) - Current Semester GPA: %.2f", gpa);
    }

    private double getGradePoint(String grade) {
        switch (grade.toUpperCase()) {
            case "A+": return 4.2; // Adjust these values if your specific student handbook differs
            case "A":  return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B":  return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C":  return 2.0;
            case "C-": return 1.5;
            case "D":  return 1.0;
            default:   return 0.0;
        }
    }
}