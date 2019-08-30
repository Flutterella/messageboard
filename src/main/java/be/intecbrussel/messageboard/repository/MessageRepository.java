package be.intecbrussel.messageboard.repository;

import be.intecbrussel.messageboard.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAll();

    Message save(Message message);
}
