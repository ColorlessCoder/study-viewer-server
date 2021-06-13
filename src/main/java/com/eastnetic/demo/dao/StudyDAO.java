package com.eastnetic.demo.dao;

import com.eastnetic.demo.entity.Study;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StudyDAO extends BaseDAO<Study>{

    public StudyDAO() {
        super(Study.class);
    }
    public void batchDelete(List<Long> studyPkList) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<Study> criteria = builder.createCriteriaDelete(Study.class);
            Root<Study> root = criteria.from(Study.class);
            criteria.where(root.get("studyPk").in(studyPkList));
            session.createQuery(criteria).executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if(tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
    }
}
