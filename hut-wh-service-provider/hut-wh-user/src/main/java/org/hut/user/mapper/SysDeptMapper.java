package org.hut.user.mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.hut.user.entity.SysDept;

import java.util.List;

/**
 * Created by hutwanghui on 2018/11/24 14:25.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 部门管理接口
 */

public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 关联dept——relation
     *
     * @param delFlag 删除标记
     * @return 数据列表
     */
    List<SysDept> selectDeptDtoList(String delFlag);
}