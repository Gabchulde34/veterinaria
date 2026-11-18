package com.chulde12.service;

import com.chulde12.model.Mascota;
import com.chulde12.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class MascotaService {

    private MascotaRepository mascotaRepository;

    public MascotaService( MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    //TRAER TODOS
    public List<Mascota> traerTodos(){
        return mascotaRepository.findAll();
    }

    //TRAER UNO
    public Optional<Mascota> traerUno(int id){
        return mascotaRepository.findById(id);
    }

    //GUARDAR
    public Mascota  guardar (Mascota Mascota){
        return mascotaRepository.save(Mascota);
    }

    //EDITAR
    public Optional <Mascota> editar(int id, Mascota magoActualziado){
        return mascotaRepository.findById(id)
                .map( MascotaExiste ->{
                    MascotaExiste.setFuerza( MascotaActualziado.getFuerza());
                    MascotaExiste.setNombre(MascotaActualziado.getNombre());

                    return mascotaRepository.save( MascotaExiste);
                });
    }

    //ELIMINAR
    public boolean eliminar (int id){
        if ( mascotaRepository.existsById(id)){
            mascotaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    //Especial
    public List <Mascota> fuertes (){
        return mascotaRepository.findAll()
                .stream().filter(
                        Mascota -> Mascota.getFuerza()>200
                ).collect(Collectors.toList());
    }


}
