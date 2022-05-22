package com.attoresearch.Assignment.Controller;

import com.attoresearch.Assignment.Dto.HostDTO;
import com.attoresearch.Assignment.Service.HostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HostsController {

    private final HostsService hostsService;

    //호스트 생성
    @PostMapping("/hosts")
    public ResponseEntity CreateHost(@RequestBody Map<String, Object> host){
        int result = hostsService.CreateHost(host);

        Map<String, String> msg = new HashMap<>();
        ResponseEntity re;
        if(result >= 1){
            msg.put("Message","Success");
            re = new ResponseEntity(msg, HttpStatus.CREATED);
        }else{
            msg.put("Message","Create Fail");
            re = new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
        }
        return re;
    }

    //호스트 조회
    @GetMapping("/hosts")
    public ResponseEntity AllSearchHost(){
        //조회
        List<HostDTO> hosts = hostsService.HostAll();
        return new ResponseEntity(hosts, HttpStatus.OK);
    }

    //호스트 단일 조회
    @GetMapping("/hosts/{id}")
    public ResponseEntity SearchHost(@PathVariable(name = "id")int id){
        HostDTO host = hostsService.HostSearch(id);

        return new ResponseEntity(host, HttpStatus.OK);
    }

    //호스트 수정
    @PatchMapping("/hosts/{id}")
    public ResponseEntity UpdateHost(@RequestBody HostDTO host, @PathVariable int id){
        host.setId(id);
        ResponseEntity result = hostsService.UpdateHost(host);
        return result;
    }

    //삭제
    @DeleteMapping("/hosts/{id}")
    public ResponseEntity DeleteHost(@PathVariable(name = "id")int id){
        ResponseEntity result = hostsService.DeleteHost(id);
        return result;
    }


    //호스트 상태 확인
    @GetMapping("/hosts/{id}/alive")
    public ResponseEntity HostCheck(@PathVariable(name = "id")int id){
        Map<String,Object> hostStatus = hostsService.HostStatus(id);
        return new ResponseEntity(hostStatus, HttpStatus.OK);
    }


    //전체 호스트 모니터링 결과 조회
    @GetMapping("/hosts/monitor")
    public ResponseEntity HostsAllMonitoring(){
        return new ResponseEntity(hostsService.AllMonitoringResult(), HttpStatus.OK);
    }

    //단일 호스트 모니터링 결과 조회
    @GetMapping("/hosts/monitor/{id}")
    public ResponseEntity HostsMonitoring(@PathVariable(name = "id")int id){
        return new ResponseEntity(hostsService.MonitoringResult(id), HttpStatus.OK);
    }


}
