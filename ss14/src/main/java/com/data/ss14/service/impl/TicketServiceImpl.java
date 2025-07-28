package com.data.ss14.service.impl;

import com.data.ss14.model.dto.request.TicketRequestDTO;
import com.data.ss14.model.dto.response.TicketResponseDTO;
import com.data.ss14.model.entity.ShowTime;
import com.data.ss14.model.entity.Ticket;
import com.data.ss14.model.entity.User;
import com.data.ss14.repository.ShowTimeRepo;
import com.data.ss14.repository.TicketRepository;
import com.data.ss14.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.data.ss14.service.TicketService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final ShowTimeRepo showtimeRepo;

    @Override
    public TicketResponseDTO bookTicket(TicketRequestDTO dto, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ShowTime showtime = showtimeRepo.findById(dto.getShowtimeId())
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        Ticket ticket = Ticket.builder()
                .user(user)
                .showTime(showtime)
                .seatNumber(dto.getSeatNumber())
                .price(dto.getPrice())
                .bookingTime(LocalDateTime.now())
                .build();

        ticketRepository.save(ticket);

        return mapToDTO(ticket);
    }

    @Override
    public List<TicketResponseDTO> getMyTickets(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ticketRepository.findByUser(user).stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<TicketResponseDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    private TicketResponseDTO mapToDTO(Ticket ticket) {
        return TicketResponseDTO.builder()
                .id(ticket.getId())
                .seatNumber(ticket.getSeatNumber())
                .price(ticket.getPrice())
                .bookingTime(ticket.getBookingTime())
                .showtimeId(ticket.getShowTime().getId())
                .username(ticket.getUser().getUsername())
                .build();
    }
}
