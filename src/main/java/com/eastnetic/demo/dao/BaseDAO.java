package com.eastnetic.demo.dao;

import com.eastnetic.demo.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BaseDAO<T extends BaseEntity> {
    @Autowired
    SessionFactory sessionFactory;

    private Class<T> entityClass;

    public BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void saveOrUpdate(T record) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(record);
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

    public void batchSaveOrUpdate(List<T> recordList) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            recordList.forEach(session::saveOrUpdate);
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

    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(entityClass);
            Root<T> root = criteria.from(entityClass);
            criteria.select(root);
            List<T> list = session.createQuery(criteria).list();
            tx.commit();
            return list;
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
