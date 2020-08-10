package com.datacollcet.service.impl;

import com.datacollcet.dao.V2iDataMapper;
import com.datacollcet.pojo.V2IInformation;
import com.datacollcet.service.V2iService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by 郭诗韬 on 2018/3/27.
 */
@org.springframework.stereotype.Service
public class V2iServiceImpl implements V2iService{
    @Autowired
    V2iDataMapper v2iDataMapper;
    @Override
    public int collectInfo(V2IInformation info) {

        return v2iDataMapper.insertData(info);
    }
}
