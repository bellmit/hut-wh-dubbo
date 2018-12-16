package org.hut.common.entity.pms;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 画册图片表
 * </p>
 *
 * @author hutwanghui123
 * @since 2018-12-16
 */
@TableName("pms_album_pic")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AlbumPic extends Model<AlbumPic> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("album_id")
    private Long albumId;
    private String pic;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AlbumPic{" +
        ", id=" + id +
        ", albumId=" + albumId +
        ", pic=" + pic +
        "}";
    }
}
