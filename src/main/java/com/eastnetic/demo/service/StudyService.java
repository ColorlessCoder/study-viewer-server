package com.eastnetic.demo.service;

import com.eastnetic.demo.dao.StudyDAO;
import com.eastnetic.demo.entity.Study;
import com.eastnetic.demo.util.MessageException;
import com.eastnetic.demo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudyService {
    @Autowired
    StudyDAO studyDAO;
    @Autowired
    PatientService patientService;

    public List<Study> getAllStudies() {
        List<Study> studies = studyDAO.getAll();
        studies.sort((a,b) -> b.getModifiedAt().compareTo(a.getModifiedAt()));
        return studies;
    }

    private void validateStudy(Study study) throws MessageException {
        if(StringUtils.isNullOrEmpty(study.getStudyName())) {
            throw new MessageException("Study Name is required");
        }
        if(study.getPatient() == null) {
            throw new MessageException("Patient is required");
        }
        patientService.validatePatient(study.getPatient());
    }

    private void trimWhiteSpaces(Study study) {
        study.setStudyName(StringUtils.nullSafeTrim(study.getStudyName()));
        study.setStudyDescription(StringUtils.nullSafeTrim(study.getStudyDescription()));
        if(study.getPatient() != null) {
            patientService.trimWhiteSpace(study.getPatient());
        }
    }

    public Study createStudy(Study study) throws MessageException {
        study.setStudyPk(0);
        return saveOrUpdateStudy(study);
    }

    public Study updateStudy(Study study) throws MessageException {
        if(study.getStudyPk() == 0) {
            throw new MessageException("Cannot create in new Study");
        }
        return saveOrUpdateStudy(study);
    }

    private Study saveOrUpdateStudy(Study study) throws MessageException {
        trimWhiteSpaces(study);
        validateStudy(study);
        study.setModifiedAt(LocalDateTime.now());
        studyDAO.saveOrUpdate(study);
        return study;
    }

    public List<Long> deleteStudies(List<Long> studyPkList) {
        studyDAO.batchDelete(studyPkList);
        return studyPkList;
    }
}
