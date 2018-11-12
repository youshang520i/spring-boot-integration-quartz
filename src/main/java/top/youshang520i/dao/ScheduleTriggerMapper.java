package top.youshang520i.dao;

import org.springframework.stereotype.Service;
import top.youshang520i.pojo.ScheduleTrigger;

import java.util.List;
@Service(value = "scheduleTriggerMapper")
public interface ScheduleTriggerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScheduleTrigger record);

    int insertSelective(ScheduleTrigger record);

    ScheduleTrigger selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScheduleTrigger record);

    int updateByPrimaryKey(ScheduleTrigger record);

    List<ScheduleTrigger> queryAll();
}