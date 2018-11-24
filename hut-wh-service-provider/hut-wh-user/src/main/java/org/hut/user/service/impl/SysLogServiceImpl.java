

package org.hut.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.hut.user.mapper.SysLogMapper;
import org.hut.user.service.SysLogService;
import org.hut.common.constant.CommonConstant;
import org.hut.common.entity.SysLog;
import org.hut.common.entity.Assert;

import java.util.Date;

/**
 * Created by hutwanghui on 2018/11/24 20:09.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 日志服务
 */

@Service(
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        version = "1.0.0",
        group = "sys"
)
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public Boolean updateByLogId(Long id) {
        Assert.isNull(id, "日志ID为空");
        SysLog sysLog = new SysLog();
        sysLog.setId(id);
        sysLog.setDelFlag(CommonConstant.STATUS_DEL);
        sysLog.setUpdateTime(new Date());
        return updateById(sysLog);
    }
}
