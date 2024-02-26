package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.TodoUser;
import dto.Todotask;

public class TodoDao {
	EntityManagerFactory e = Persistence.createEntityManagerFactory("connect");
	EntityManager m = e.createEntityManager();
	EntityTransaction t = m.getTransaction();

	public void saveData(TodoUser user) {
		t.begin();
		m.persist(user);
		t.commit();
	}
	


	public void saveTask(Todotask task) {
		t.begin();
		m.persist(task);
		t.commit();
		 
	}



	public List<TodoUser> findByEmail(String email) {
		return m.createQuery("select x from TodoUser x where email=?1").setParameter(1, email).getResultList();
		
	}
	
	public List<Todotask> findbytaskid(int id){
		return m.createQuery("select x from Todotask x where user_id=?1").setParameter(1, id).getResultList();
	}



	public void getById(int id) {
		
		Todotask task = m.find(Todotask.class, id );
		task.setStatus(true);
		t.begin();
		m.merge(task);
		t.commit();
		
	}



	public boolean deletById(int id) {
		Todotask task = m.find(Todotask.class, id );
		
		t.begin();
		m.remove(task);
		t.commit();
		
		return true;
	}



	public boolean editDb(String taskname, String discription, int id) {
		Todotask task = m.find(Todotask.class, id );
		task.setTaskName(taskname);
		task.setDescirption(discription);
		t.begin();
		m.merge(task);
		t.commit();
		return true;
	}
	
	
	
}
