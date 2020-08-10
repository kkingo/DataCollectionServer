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

    int insertFingerprint(FingerPrint fingerprint);

    List<FingerPrint> getFingerprint();

    int insertReadings(Readings readings);

    int insertOfflineData(OfflineData data);

    List<Readings> getReadings();

    void insertPosition(Position position);

    void insertMatchedTraj(Position position);

    List<MyFingerPrint> getRefinedFingerprint();

    void getMyFingerprint();

    List<Subarea> getSubarea();

    List<PixelMap> getPixelMap();

    int insertMagReadings(Mag mag);

    void insertNumbers(RecordNumber number);

    void updateFingerprint(Relative relative);

    void insertConvergence(Double convergence);

    void insertStep(Steps step);

    int insertNNFingerprint(NNfingerprint nfingerprint);

    List<Position> getPosition();

    List<GradientFP> getGmap();

    int insertMapsenseData();

    int insertDrivingTraj(TrajInfo trajInfo);
}
