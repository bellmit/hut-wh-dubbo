package org.hut.product.service;


import com.alibaba.dubbo.config.annotation.Service;
import org.hut.common.entity.pms.AlbumPic;
import org.hut.product.mapper.AlbumPicMapper;
import org.hut.openapi.user.service.pms.IAlbumPicService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 画册图片表 服务实现类
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
@Service(
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}",
        version = "1.0.0",
        group = "pms"
)
public class AlbumPicServiceImpl extends ServiceImpl<AlbumPicMapper, AlbumPic> implements IAlbumPicService {

}
