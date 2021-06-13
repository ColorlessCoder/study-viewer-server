package com.eastnetic.demo.dao;

import com.eastnetic.demo.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PatientDAO extends BaseDAO<Patient> {

    public PatientDAO() {
        super(Patient.class);
    }

    public List<Patient> getPatientsForPicker(String query, int limit) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT * FROM patient WHERE concat(person_code, ' ', first_name, ' ',last_name) like :query limit :limit";
            NativeQuery<Patient> nativeQuery = session.createNativeQuery(sql, Patient.class);
            nativeQuery.setParameter("query", query);
            nativeQuery.setParameter("limit", limit);
            List<Patient> list = nativeQuery.list();
            tx.commit();
            return list;
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            session.close();
        }
    }

    public Patient getByPersonCode(String personCode) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Patient> criteria = builder.createQuery(Patient.class);
            Root<Patient> root = criteria.from(Patient.class);
            Predicate predicate = builder.equal(root.get("personCode"), personCode);
            criteria.select(root).where(predicate);
            Patient patient = session.createQuery(criteria).uniqueResult();
            tx.commit();
            return patient;
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
