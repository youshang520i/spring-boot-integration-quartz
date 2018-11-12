package top.youshang520i.service;

import org.springframework.stereotype.Service;
import top.youshang520i.pojo.ScheduleTriggerParam;

import java.util.List;
/**
 *  @Author:youshang520i
 *  @Date:$date  15:34
 *  @Description : ScheduleTriggerParamServiceI接口
 */
public interface ScheduleTriggerParamServiceI {
    int deleteByPrimaryKey(Integer paramId);

    int insert(ScheduleTriggerParam record);

    int insertSelective(ScheduleTriggerParam record);

    ScheduleTriggerParam selectByPrimaryKey(Integer paramId);

    int updateByPrimaryKeySelective(ScheduleTriggerParam record);

    int updateByPrimaryKey(ScheduleTriggerParam record);

    List<ScheduleTriggerParam> listByScheduleTriggerId(Integer scheduleTriggerId);
}