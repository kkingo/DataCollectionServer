package com.datacollcet.dao;

import com.datacollcet.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 郭诗韬 on 2018/3/27.
 */
@Mapper
public interface V2iDataMapper {
    int insertData(@Param("data")V2IInformation data);

    int insertNNFingerprint(NNfingerprint nfingerprint);

}
