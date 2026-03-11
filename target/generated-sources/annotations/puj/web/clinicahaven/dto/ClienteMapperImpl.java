package puj.web.clinicahaven.dto;

import javax.annotation.processing.Generated;
import puj.web.clinicahaven.entity.Cliente;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-09T19:49:35-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDTO convert(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setCedula( cliente.getCedula() );
        clienteDTO.setCelular( cliente.getCelular() );
        clienteDTO.setClienteId( cliente.getClienteId() );
        clienteDTO.setCorreo( cliente.getCorreo() );
        clienteDTO.setNombre( cliente.getNombre() );

        return clienteDTO;
    }
}
