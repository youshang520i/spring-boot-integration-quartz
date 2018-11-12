package top.youshang520i.service;

import org.springframework.stereotype.Service;
import top.youshang520i.pojo.ScheduleTrigger;

import java.util.List;


public interface ScheduleTriggerServiceI {
    int deleteByPrimaryKey(Integer id);

    int insert(ScheduleTrigger record);

    int insertSelective(ScheduleTrigger record);

    ScheduleTrigger selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScheduleTrigger record);

    int updateByPrimaryKey(ScheduleTrigger record);

    List<ScheduleTrigger> queryAll();

    void refreshTrigger();

    void add(ScheduleTrigger scheduleTrigger);
}