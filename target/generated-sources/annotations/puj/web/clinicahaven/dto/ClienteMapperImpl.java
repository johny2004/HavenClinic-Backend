package puj.web.clinicahaven.dto;

import javax.annotation.processing.Generated;
import puj.web.clinicahaven.entity.Cliente;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T13:18:41-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDTO convert(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setClienteId( cliente.getClienteId() );
        clienteDTO.setCedula( cliente.getCedula() );
        clienteDTO.setNombre( cliente.getNombre() );
        clienteDTO.setCelular( cliente.getCelular() );
        clienteDTO.setCorreo( cliente.getCorreo() );

        return clienteDTO;
    }
}
