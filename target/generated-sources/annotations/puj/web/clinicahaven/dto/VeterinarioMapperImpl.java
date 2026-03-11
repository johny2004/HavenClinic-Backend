package puj.web.clinicahaven.dto;

import javax.annotation.processing.Generated;
import puj.web.clinicahaven.entity.Veterinario;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T13:18:41-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
public class VeterinarioMapperImpl implements VeterinarioMapper {

    @Override
    public VeterinarioDTO convert(Veterinario veterinario) {
        if ( veterinario == null ) {
            return null;
        }

        VeterinarioDTO veterinarioDTO = new VeterinarioDTO();

        veterinarioDTO.setCorreo( veterinario.getCorreo() );
        veterinarioDTO.setCedula( veterinario.getCedula() );
        veterinarioDTO.setNombre( veterinario.getNombre() );
        veterinarioDTO.setCelular( veterinario.getCelular() );
        veterinarioDTO.setEspecialidad( veterinario.getEspecialidad() );
        veterinarioDTO.setFoto( veterinario.getFoto() );
        veterinarioDTO.setActivo( veterinario.getActivo() );

        return veterinarioDTO;
    }
}
