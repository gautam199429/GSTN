package com.codecube.saathii.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codecube.saathii.dao.ViewInformationDao;
import com.codecube.saathii.entity.ViewInformation;

@Repository
public class ViewInformationDaoImpl implements ViewInformationDao{

	@PersistenceContext	
	private EntityManager entityManager;	

			@Autowired
			SessionFactory sessionFactory;
			
			@Override
			public List<ViewInformation> loadInformations(ViewInformation viewInformation) {
				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				Criteria cq = session.createCriteria(ViewInformation.class);
				ProjectionList projList = Projections.projectionList();
				projList.add(Projections.property("userId").as("userid"));
				projList.add(Projections.property("password").as("password"));
				cq.add(Restrictions.eq("deleted", "N"));
			    cq.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);			   
				List<ViewInformation> informations = cq.list();
				tx.commit();
				session.close();
				return informations;
			}

			@Override
			public ViewInformation getInformationById(String userId) {
				return entityManager.find(ViewInformation.class, userId);
			}			
}
	


