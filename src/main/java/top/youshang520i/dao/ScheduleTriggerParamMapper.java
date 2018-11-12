package top.youshang520i.dao;

import org.springframework.stereotype.Service;
import top.youshang520i.pojo.ScheduleTriggerParam;

import java.util.List;
@Service(value = "scheduleTriggerParamMapper")
public interface ScheduleTriggerParamMapper {
    int deleteByPrimaryKey(Integer paramId);

    int insert(ScheduleTriggerParam record);

    int insertSelective(ScheduleTriggerParam record);

    ScheduleTriggerParam selectByPrimaryKey(Integer paramId);

    int updateByPrimaryKeySelective(ScheduleTriggerParam record);

    int updateByPrimaryKey(ScheduleTriggerParam record);

    List<ScheduleTriggerParam> listByScheduleTriggerId(Integer scheduleTriggerId);
}