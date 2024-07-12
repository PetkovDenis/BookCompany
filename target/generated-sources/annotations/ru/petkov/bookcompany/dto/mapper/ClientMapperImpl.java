package ru.petkov.bookcompany.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import ru.petkov.bookcompany.dto.ClientDTO;
import ru.petkov.bookcompany.entity.Client;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-10T09:43:44+1000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Private Build)"
)
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDTO toClientDTO(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setClientId( client.getClientId() );
        clientDTO.setFirstName( client.getFirstName() );
        clientDTO.setLastName( client.getLastName() );
        clientDTO.setContact( client.getContact() );

        return clientDTO;
    }

    @Override
    public Client toClient(ClientDTO clientDTO) {
        if ( clientDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setClientId( clientDTO.getClientId() );
        client.setFirstName( clientDTO.getFirstName() );
        client.setLastName( clientDTO.getLastName() );
        client.setContact( clientDTO.getContact() );

        return client;
    }

    @Override
    public List<ClientDTO> toClientsDTO(List<Client> clients) {
        if ( clients == null ) {
            return null;
        }

        List<ClientDTO> list = new ArrayList<ClientDTO>( clients.size() );
        for ( Client client : clients ) {
            list.add( toClientDTO( client ) );
        }

        return list;
    }
}
