package com.excel.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.excel.entity.UploadDate;
import org.apache.ibatis.annotations.Mapper;



import java.util.List;


/**
 * @author yangzx
 */
public interface UploadMapper extends BaseMapper<UploadDate> {
    /**
     * 批量插入
     * @param list
     */
    void save(List<UploadDate> list);


}
