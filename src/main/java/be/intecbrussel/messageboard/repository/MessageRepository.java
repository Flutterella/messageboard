package be.intecbrussel.messageboard.repository;

import be.intecbrussel.messageboard.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    public List<Message> findAll();

    @Transactional
    public Message save(Message message);
}
