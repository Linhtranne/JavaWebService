package com.data.ss14.repository;

import com.data.ss14.model.entity.Ticket;
import com.data.ss14.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long>{
    List<Ticket> findByUser(User user);
}