package ru.petkov.bookcompany.dto.mapper;

import org.mapstruct.Mapper;
import ru.petkov.bookcompany.dto.ClientDTO;
import ru.petkov.bookcompany.entity.Client;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toClientDTO(Client client);

    Client toClient(ClientDTO clientDTO);

    List<ClientDTO> toClientsDTO(List<Client> clients);
}
