

package org.hut.common.vo;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by hutwanghui on 2018/11/24 14:11.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: 验证码对象
 */

@Data
public class ImageCode implements Serializable {
    private String code;

    private LocalDateTime expireTime;

    private BufferedImage image;

    public ImageCode(BufferedImage image, String sRand, int defaultImageExpire) {
        this.image = image;
        this.code = sRand;
        this.expireTime = LocalDateTime.now().plusSeconds(defaultImageExpire);
    }
}
