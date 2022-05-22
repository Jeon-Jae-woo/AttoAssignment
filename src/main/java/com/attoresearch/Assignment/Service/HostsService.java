package com.attoresearch.Assignment.Service;

import com.attoresearch.Assignment.Dto.HostDTO;
import com.attoresearch.Assignment.Dto.HostManagementDTO;
import com.attoresearch.Assignment.Mapper.HostsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class HostsService {

    private final HostsMapper hostsMapper;

    //호스트 생성
    public int CreateHost(Map<String, Object> host){
        int result = hostsMapper.HostInsert(host);
        return result;
    }

    //호스트 전체 조회
    public List<HostDTO> HostAll(){
        List<HostDTO> hosts = hostsMapper.HostAllMapper();
        return hosts;
    }

    //호스트 단일 조회
    public HostDTO HostSearch(int id){
        HostDTO host = hostsMapper.HostSearchMapper(id);
        return host;
    }

    //호스트 수정
    public ResponseEntity UpdateHost(HostDTO host){
        int result = hostsMapper.HostUpdateMapper(host);
        return PutMsg(result);
    }

    //호스트 삭제
    public ResponseEntity DeleteHost(int id){
        int result = hostsMapper.HostDeleteMapper(id);
        return PutMsg(result);
    }


    //호스트 상태 조회
    public Map<String, Object> HostStatus(int id){
        HostDTO host = hostsMapper.HostSearchMapper(id);
        Map<String, Object> hostStatus = new HashMap<>();
        if(host != null){
            try {
                InetAddress inetAddress = InetAddress.getByName(host.getIp());
                hostStatus.put("ip", host.getIp());
                hostStatus.put("name", host.getName());
                if(inetAddress.isReachable(1000)){
                    hostStatus.put("aliveStatus", true);
                }else{
                    hostStatus.put("aliveStatus", false);
                }

            }catch (UnknownHostException e){
                hostStatus.put("message", "UnknownHostException");
            } catch (IOException e) {
                hostStatus.put("message", "IOException");
            }

        }else{
            hostStatus.put("message", "Host Not Found");
        }

        return hostStatus;
    }


    //전체 호스트 모니터링 결과 조회
    public List<HostManagementDTO> AllMonitoringResult(){
        List<HostManagementDTO> hostList = hostsMapper.AllHostManagementMapper();

        for(int i=0;i<hostList.size();i++){
            Monitoring(hostList.get(i));
        }
        return hostList;
    }

    //호스트 모니터링 결과 조회
    public HostManagementDTO MonitoringResult(int id){
        HostManagementDTO host = hostsMapper.HostManagementMapper(id);
        Monitoring(host);
        return host;
    }


    //상태 조회
    @Async
    public void Monitoring(HostManagementDTO host){
        try {
            InetAddress inetAddress = InetAddress.getByName(host.getIp());
            if(inetAddress.isReachable(1000)){
                host.setAlive_status(true);
                host.setAlive_time(LocalDateTime.now());
            }else{
                host.setAlive_status(false);
            }

            int result = hostsMapper.HostAliveUpdateMapper(host);
            if(result < 1){
                throw new Exception();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //return message
    public ResponseEntity PutMsg(int result){
        Map<String,Object> map = new HashMap<>();
        ResponseEntity re;
        if(result >= 1){
            map.put("Message", "Success");
            re = new ResponseEntity(map, HttpStatus.OK);
        }else{
            map.put("Message", "Fail");
            re = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        return re;
    }
}
