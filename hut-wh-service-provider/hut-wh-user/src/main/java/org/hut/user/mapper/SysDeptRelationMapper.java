package org.hut.user.mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.hut.common.entity.SysDeptRelation;
/**
 * Created by hutwanghui on 2018/11/24 14:12.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc:
 */
public interface SysDeptRelationMapper extends BaseMapper<SysDeptRelation> {
    /**
     * 删除部门关系表数据
     *
     * @param id 部门ID
     */
    void deleteAllDeptRealtion(Integer id);

    /**
     * 更改部分关系表数据
     *
     * @param deptRelation
     */
    void updateDeptRealtion(SysDeptRelation deptRelation);
}