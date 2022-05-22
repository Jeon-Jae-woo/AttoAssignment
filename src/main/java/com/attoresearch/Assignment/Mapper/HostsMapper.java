package com.attoresearch.Assignment.Mapper;

import com.attoresearch.Assignment.Dto.HostDTO;
import com.attoresearch.Assignment.Dto.HostManagementDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HostsMapper {

    //호스트 등록
    int HostInsert(Map<String, Object> host);
    //호스트 전체 조회
    List<HostDTO> HostAllMapper();
    //호스트 단일 조회
    HostDTO HostSearchMapper(int id);
    //호스트 업데이트
    int HostUpdateMapper(HostDTO host);
    //호스트 삭제
    int HostDeleteMapper(int id);

    //호스트 모니터링 상태 전체 조회
    List<HostManagementDTO> AllHostManagementMapper();
    //호스트 모니터링 상태 단일 조회
    HostManagementDTO HostManagementMapper(int id);
    //호스트 상태 업데이트
    int HostAliveUpdateMapper(HostManagementDTO host);

}
