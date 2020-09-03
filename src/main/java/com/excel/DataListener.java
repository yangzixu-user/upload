package com.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.excel.entity.UploadDate;
import com.excel.mapper.UploadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yangzx
 */
public class DataListener extends AnalysisEventListener<UploadDate> {
    private static final Logger logger =  LoggerFactory.getLogger(DataListener.class);
    private static final int BATCH_COUNT=200;

   List<UploadDate> list = new ArrayList<UploadDate>();

   private UploadMapper uploadMapper;

  public DataListener(UploadMapper uploadMapper){
       this.uploadMapper = uploadMapper;
   }

    @Override
    public void invoke(UploadDate data, AnalysisContext context) {
        logger.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        if (list.size()>=BATCH_COUNT){
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
      saveData();
      logger.info("所有数据解析完毕");

    }

    public void saveData(){
      logger.info("{}条数据，开始存储数据库！",list.size());
      uploadMapper.save(list);
      logger.info("存储数据库成功！");
    }
}
