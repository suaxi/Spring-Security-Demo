package com.software.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Wang Hao
 * @date 2022/10/5 17:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
@ApiModel(value = "用户表")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;

    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    @TableField(value = "nickname")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @TableField(value = "password")
    @ApiModelProperty(value = "密码")
    private String password;

    @TableField(value = "status")
    @ApiModelProperty(value = "帐号状态")
    private String status;

    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @TableField(value = "phone")
    @ApiModelProperty(value = "电话")
    private String phone;

    @TableField(value = "sex")
    @ApiModelProperty(value = "性别")
    private String sex;

    @TableField(value = "avatar")
    @ApiModelProperty(value = "头像")
    private String avatar;

    @TableField(value = "user_type")
    @ApiModelProperty(value = "用户类型")
    private String userType;

    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(value = "update_by")
    @ApiModelProperty(value = "更新人id")
    private Long updateBy;

    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @TableField(value = "is_delete")
    @ApiModelProperty(value = "逻辑删除")
    private Integer isDelete;
}
