package com.scalesampark.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.scalesampark.domains.Participant;

public class ParticipantDao implements Dao<Participant> {
	Session session = null;
	
	private long addParticipant(Session session, Participant bean){
		Participant participant = new Participant();
		participant.setEmail(bean.getEmail());
		participant.setNickname(bean.getNickname());
        session.save(participant);
        return participant.getParticipantId();
    }
	
	@Override
	public Participant get(long id) throws Exception {
		session = SessionUtil.getSession();  
        Query query = session.createQuery("from Participant where id = :id");
        query.setLong("id", id);
        List<Participant> participants =  query.list();
        if(participants != null && participants.size() > 0) {
        	session.close();
        	return participants.get(0);
        } else {
        	throw new Exception();
        }
        
	}

	@Override
	public List<Participant> getAll() {
		session = SessionUtil.getSession();  
        Query query = session.createQuery("from Participant");
        List<Participant> participants =  query.list();
        session.close();
        return participants;
	}

	@Override
	public long save(Participant t) {
		session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		long id = addParticipant(session, t);
		tx.commit();
		session.close();
		return id;
	}

	@Override
	public void update(Participant t) {
		session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.update(t);
		tx.commit();
		session.close();
	}

	@Override
	public int delete(long id) {
		Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Participant where id = :id";
        Query query = session.createQuery(hql);
        query.setLong("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
	}
}
