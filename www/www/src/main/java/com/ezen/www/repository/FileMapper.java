package com.ezen.www.repository;

import com.ezen.www.domain.FileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    int insertFile(FileVO fvo);
}
