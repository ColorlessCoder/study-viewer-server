package com.eastnetic.demo.controller;

import com.eastnetic.demo.entity.Study;
import com.eastnetic.demo.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studies")
public class StudyController {
    @Autowired
    StudyService studyService;

    @GetMapping("")
    public ResponseEntity<List<Study>> getAllStudies() {
        return ResponseEntity.ok(studyService.getAllStudies());
    }

    @PostMapping("")
    public ResponseEntity<Study> createStudy(@RequestBody Study study) throws Exception {
        return ResponseEntity.ok(studyService.createStudy(study));
    }

    @PutMapping("")
    public ResponseEntity<Study> updateStudy(@RequestBody Study study) throws Exception {
        return ResponseEntity.ok(studyService.updateStudy(study));
    }

    @PutMapping("/delete-batch")
    public ResponseEntity<List<Long>> deleteStudies(@RequestBody List<Long> studyPkList) {
        return ResponseEntity.ok(studyService.deleteStudies(studyPkList));
    }
}
