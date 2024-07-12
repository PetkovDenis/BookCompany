package ru.petkov.bookcompany.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.petkov.bookcompany.dto.ClientDTO;
import ru.petkov.bookcompany.entity.Client;

import java.util.List;


@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO toClientDTO(Client client);

    Client toClient(ClientDTO clientDTO);

    List<ClientDTO> toClientsDTO(List<Client> clients);
}
