package ru.petkov.bookcompany.repository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.petkov.bookcompany.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
