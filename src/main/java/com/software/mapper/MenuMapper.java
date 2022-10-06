package com.software.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wang Hao
 * @date 2022/10/6 15:51
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询权限信息
     *
     * @param userId 用户id
     * @return
     */
    List<String> queryUserPermissionByUserId(Long userId);
}
