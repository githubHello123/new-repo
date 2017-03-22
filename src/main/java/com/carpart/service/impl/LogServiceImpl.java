package com.carpart.service.impl;

import com.carpart.dao.LogRepository;
import com.carpart.entity.Log;
import com.carpart.entity.User;
import com.carpart.service.LogService;
import com.gx.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by David on 2017/3/22 0022.
 */
@Service
public class LogServiceImpl implements LogService{
    @Autowired
    private LogRepository logRepository;

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }

    @Override
    public Log findOne(Long id) {
        return logRepository.findOne(id);
    }

    @Override
    public void delete(Log log) {
        logRepository.delete(log);
    }

    @Override
    public Log add(Log log) {
        return logRepository.save(log);
    }

    @Override
    public void addLog(String message, Short logType, Short logLevel) {
        HttpServletRequest request = ContextHolderUtils.getRequest();
        String broswer = BrowserUtils.checkBrowse(request);
        Log log = new Log();
        log.setLogcontent(message);
        log.setLoglevel(logLevel);
        log.setOperatetype(logType);
        log.setNote(oConvertUtils.getIp());
        log.setBroswer(broswer);
        log.setOperatetime(DataUtils.gettimestamp());

        User user = ResourceUtil.getSessionUserName();
        if(user != null) log.setUserid(user.getId());
        logRepository.save(log);
    }
}
