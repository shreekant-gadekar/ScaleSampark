package com.scalesampark.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.scalesampark.domains.Participant;

public class ParticipantDao implements Dao<Participant> {
	Session session = null;
	
	private long addParticipant(Session session, Participant bean){
		Participant participant = new Participant();
		participant.setEmail(bean.getEmail());
		participant.setNickname(bean.getNickname());
        session.save(participant);
        return participant.getParticipantUuid();
    }
	
	@Override
	public Participant get(long id) throws Exception {
		session = SessionUtil.getSession();  
        Query query = session.createQuery("from Participant where participantUuid = :id");
        query.setLong("id", id);
        List<Participant> participants =  query.list();
        if(participants != null && participants.size() > 0) {
        	session.close();
        	return participants.get(0);
        } else {
        	throw new Exception();
        }
        
	}

	/*public Participant getByUuid(UUID id) throws Exception {
		session = SessionUtil.getSession();  
//        Query query = session.createQuery("from Participant where id = :id");
//        query.setString("id", id.toString());
        Criteria criteria = session.createCriteria(Participant.class);
        criteria.add(Restrictions.eq("participantUuid", id));
        
//        List<Participant> participants =  query.list();
        
        Participant participant = (Participant) criteria.uniqueResult();
        
//        if(participants != null && participants.size() > 0) {
        if(participant != null) {
        	session.close();
        	return participant;
        } else {
        	throw new Exception();
        }
        
	}*/
	
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
        String deleteParticipantMsgs = "delete from Message where participantUuid = :id";
        Query query1 = session.createQuery(deleteParticipantMsgs);
        query1.setLong("id",id);
//        System.out.println(query1.executeUpdate() + " : Messages deleted related to participant");
        String deleteParticipant = "delete from Participant where participantUuid = :id";
        Query query = session.createQuery(deleteParticipant);
        query.setLong("id",id);
        int rowCount = query.executeUpdate();
//        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
	}
}
