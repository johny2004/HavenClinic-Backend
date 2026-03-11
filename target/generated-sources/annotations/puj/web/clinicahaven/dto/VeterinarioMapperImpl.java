package puj.web.clinicahaven.dto;

import javax.annotation.processing.Generated;
import puj.web.clinicahaven.entity.Veterinario;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T14:44:20-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
public class VeterinarioMapperImpl implements VeterinarioMapper {

    @Override
    public VeterinarioDTO convert(Veterinario veterinario) {
        if ( veterinario == null ) {
            return null;
        }

        VeterinarioDTO veterinarioDTO = new VeterinarioDTO();

        veterinarioDTO.setActivo( veterinario.getActivo() );
        veterinarioDTO.setCedula( veterinario.getCedula() );
        veterinarioDTO.setCelular( veterinario.getCelular() );
        veterinarioDTO.setCorreo( veterinario.getCorreo() );
        veterinarioDTO.setEspecialidad( veterinario.getEspecialidad() );
        veterinarioDTO.setFoto( veterinario.getFoto() );
        veterinarioDTO.setNombre( veterinario.getNombre() );

        return veterinarioDTO;
    }
}
