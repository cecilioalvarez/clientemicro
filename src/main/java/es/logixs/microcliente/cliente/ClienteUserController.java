package es.logixs.microcliente.cliente;

import es.logixs.microcliente.cliente.dto.UserMobileDTO;
import es.logixs.webbasica.domain.port.input.proyection.UserProyectionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usersclient")
public class ClienteUserController {

    @Autowired
    RestTemplate plantilla;


    @GetMapping
    public List<UserMobileDTO> getUsersClient() {

       ResponseEntity<UserProyectionDTO[]> listDTOResponse=
                plantilla.getForEntity("http://MICRO1A/users", UserProyectionDTO[].class);
        UserProyectionDTO[] arrayDTO=listDTOResponse.getBody();

        List<UserMobileDTO> lista1=Arrays.stream(arrayDTO)
                .filter(u->u.getLastName().equalsIgnoreCase("Sanchez"))
                .map((u)->new UserMobileDTO(u.getName(), u.getLastName()))
        .collect(Collectors.toList());


        ResponseEntity<UserProyectionDTO[]> listDTOResponse2=
                plantilla.getForEntity("http://MICRO2A/usersvip2", UserProyectionDTO[].class);
        UserProyectionDTO[] arrayDTO2=listDTOResponse2.getBody();



        List<UserMobileDTO> lista2=Arrays.stream(arrayDTO2)

                .map((u)->new UserMobileDTO(u.getName(), u.getLastName()))
                .collect(Collectors.toList());

        lista2.addAll(lista1);
        return lista2;

    }
    @PostMapping
    public void crearUserEnMicro2(@RequestBody  UserProyectionDTO midto) {

        plantilla.postForEntity("http://MICRO2A/usersvip2",midto,UserProyectionDTO.class);


    }
}
