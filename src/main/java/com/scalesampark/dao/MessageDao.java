package com.scalesampark.dao;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.scalesampark.domains.Message;
import com.scalesampark.domains.MessageType;
import com.scalesampark.domains.Participant;
import com.scalesampark.util.EncriptionDecryption;

public class MessageDao implements Dao<Message> {
	Session session = SessionUtil.getSession();
	@Override
	public Message get(long id) throws IndexOutOfBoundsException, Exception {
		return null;
	}

	@Override
	public List<Message> getAll() {
		session = SessionUtil.getSession();
		Query quuery = session.createQuery("from Message");
		List<Message> messages = quuery.list();
		List<Message> decryptdMessages = getDecryptedMessages(messages);
		session.close();
		return decryptdMessages;
	}
	
	public List<Message> getAllUnseenMessagesByParticipant(long participantId) throws Exception {
		session = SessionUtil.getSession();
		ParticipantDao participantDao = new ParticipantDao();
		Participant participant = participantDao.get(participantId);
		Criteria crit = session.createCriteria(Message.class);
		crit.add(Restrictions.ge("createdOn", participant.getLastSeen()));
		crit.add(Restrictions.ne("participantUuid", participant.getParticipantUuid()));
		List<Message> encryptedMessages = crit.list();
		participantDao.update(participant);
		List<Message> decryptdMessages = getDecryptedMessages(encryptedMessages);
		session.close();
		return decryptdMessages;
	}

	private List<Message> getDecryptedMessages(List<Message> encryptedMessages) {
		List<Message> decryptedMessages = new ArrayList();
			encryptedMessages.stream().forEach(msg -> {
				Message message = msg;
					String decryptedMessage = null;
					System.out.println("encrypted msg" + msg.getMessage());
					String k = "Dqr12xyz12key123";
					SecretKey key = new SecretKeySpec(k.getBytes(), "AES");
					try {
						EncriptionDecryption encriptionDecryption = new EncriptionDecryption(key);
						decryptedMessage = encriptionDecryption.decrypt(msg.getMessage());
						message.setMessage(decryptedMessage);
						decryptedMessages.add(message);
					} catch (Exception e) {
						e.printStackTrace();
					}
			});
		return decryptedMessages;
	}

	@Override
	public long save(Message bean) {
		Message message = new Message();
		session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
//		ParticipantDao participantDao = new ParticipantDao();
		String encryptedMessage = null;
//		Participant participant = null;
		try {
//			participant = participantDao.get(bean.getParticipantId());
			String k = "Dqr12xyz12key123";
			SecretKey key = new SecretKeySpec(k.getBytes(), "AES");
			EncriptionDecryption encriptionDecryption = new EncriptionDecryption(key);
			encryptedMessage = encriptionDecryption.encrypt(bean.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		message.setMessage(encryptedMessage);
		message.setParticipantUuid(bean.getParticipantUuid());
		message.setMessageTypeId(bean.getMessageTypeId());
		session.save(message);
//		session.update(participant);
		tx.commit();
		session.close();
		return message.getMessageUuid();
	}

	@Override
	public void update(Message t) {
	}

	@Override
	public int delete(long id) {
		return 0;
	}
	
	public long save(MessageType bean) {
		session = SessionUtil.getSession();
		MessageType messageType = new MessageType();
		Transaction tx = session.beginTransaction();
		messageType.setMessageTypeName(bean.getMessageTypeName());
		session.save(messageType);
		tx.commit();
		session.close();
		return messageType.getMessageTypeId();
	}
	
	public List<MessageType> getAllMessageTypes() {
		session = SessionUtil.getSession();
		Query quuery = session.createQuery("from MessageType");
		List<MessageType> messageType = quuery.list();
		session.close();
		return messageType;
	}
}
