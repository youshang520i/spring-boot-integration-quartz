package top.youshang520i.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.youshang520i.dao.ScheduleTriggerParamMapper;
import top.youshang520i.pojo.ScheduleTriggerParam;
import top.youshang520i.service.ScheduleTriggerParamServiceI;
import java.util.List;
/**
 *  @Author:youshang520i
 *  @Date:$date  15:31
 *  @Description : 实现ScheduleTriggerParamServiceI接口
 */
@Service(value = "scheduleTriggerParamServiceI")
public class ScheduleTriggerParamServiceImpl implements ScheduleTriggerParamServiceI{

    @Autowired
    private ScheduleTriggerParamMapper scheduleTriggerParamMapper;

    @Override
    public int deleteByPrimaryKey(Integer paramId) {
        return scheduleTriggerParamMapper.deleteByPrimaryKey(paramId);
    }

    @Override
    public int insert(ScheduleTriggerParam record) {
        return scheduleTriggerParamMapper.insert(record);
    }

    @Override
    public int insertSelective(ScheduleTriggerParam record) {
        return scheduleTriggerParamMapper.insertSelective(record);
    }

    @Override
    public ScheduleTriggerParam selectByPrimaryKey(Integer paramId) {
        return scheduleTriggerParamMapper.selectByPrimaryKey(paramId);
    }

    @Override
    public int updateByPrimaryKeySelective(ScheduleTriggerParam record) {
        return scheduleTriggerParamMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ScheduleTriggerParam record) {
        return scheduleTriggerParamMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<ScheduleTriggerParam> listByScheduleTriggerId(Integer scheduleTriggerId) {
        return scheduleTriggerParamMapper.listByScheduleTriggerId(scheduleTriggerId);
    }
}