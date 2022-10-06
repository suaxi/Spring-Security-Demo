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
@TableName(value = "menu")
@ApiModel(value = "菜单表")
public class Menu implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;

    @TableField(value = "menu_name")
    @ApiModelProperty(value = "菜单名")
    private String menuName;

    @TableField(value = "path")
    @ApiModelProperty(value = "路由地址")
    private String path;

    @TableField(value = "component")
    @ApiModelProperty(value = "组件路径")
    private String component;

    @TableField(value = "visible")
    @ApiModelProperty(value = "菜单状态", notes = "0显示 1隐藏")
    private String visible;

    @TableField(value = "status")
    @ApiModelProperty(value = "菜单状态", notes = "0显示 1停用")
    private String status;

    @TableField(value = "perms")
    @ApiModelProperty(value = "权限标识")
    private String perms;

    @TableField(value = "icon")
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @TableField(value = "remark")
    @ApiModelProperty(value = "备注")
    private String remark;

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
