package com.ironhack.project.edgeservice.client;

import com.ironhack.project.edgeservice.controller.dto.MemberDTO;
import com.ironhack.project.edgeservice.controller.dto.MemberGetDTO;
import com.ironhack.project.edgeservice.models.Member;
import com.ironhack.project.edgeservice.models.Player;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("player-service")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface PlayerProxyClient {

    @PostMapping("/players")
    Player createPlayer(@RequestBody Player player);


}
