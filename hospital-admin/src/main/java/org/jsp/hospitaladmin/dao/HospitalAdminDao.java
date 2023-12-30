package org.jsp.hospitaladmin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.hospitaladmin.dto.Admin;
import org.jsp.hospitaladmin.dto.Hospital;

public class HospitalAdminDao {
	private EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();

	public Hospital savehospital(Hospital hospital, int admin_id) {
		Admin a = manager.find(Admin.class, admin_id);
		if (a != null) {
			a.getHospitals().add(hospital);
			hospital.setAdmin(a);
			EntityTransaction transaction = manager.getTransaction();
			manager.persist(hospital);
			transaction.begin();
			transaction.commit();
			return hospital;
		}
		return null;
	}

	public Hospital updateHospital(Hospital hospital) {
		EntityTransaction transaction = manager.getTransaction();
		Hospital hospitaldb = manager.find(Hospital.class, hospital.getId());
		if (hospitaldb != null) {
			hospitaldb.setName(hospital.getName());
			hospitaldb.setFounder(hospital.getFounder());
			hospitaldb.setGst(hospital.getGst());
			hospitaldb.setYear_of_estb(hospital.getYear_of_estb());
			hospitaldb.setAdmin(hospital.getAdmin());

			transaction.begin();
			transaction.commit();
		}
		return hospitaldb;
	}

	public List<Hospital> findhospitalByAdminId(int id) {
		String qry = "select a.hospitals from Admin a where a.id=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, id);
		return q.getResultList();
	}

	public List<Hospital> findhospitalByPhoneAndPassword(long phone, String password) {
		String qry = "select a.hospitals from Admin a where a.phone=?1 and a.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		return q.getResultList();
	}

	public List<Hospital> findHospitalByEmailAndPassword(String email, String password) {
		String qry = "select a.hospitals from Admin a where a.email=?1 and a.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, email);
		q.setParameter(2, password);
		return q.getResultList();
	}
}
