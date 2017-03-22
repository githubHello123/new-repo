package com.carpart.service;

import com.carpart.entity.Log;
import java.util.List;


/**
 * Created by Administrator on 2017/3/20.
 */
public interface LogService {

    List<Log> findAll();

    Log findOne(Long id);

    void delete(Log log);

    Log add(Log log);

    void addLog(String message,Short logType,Short logLevel);
}
