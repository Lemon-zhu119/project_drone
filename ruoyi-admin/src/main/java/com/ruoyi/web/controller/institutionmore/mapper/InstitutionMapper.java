package com.ruoyi.web.controller.institutionmore.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.web.controller.institutionmore.domain.Institution;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 机构信息详情管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-12-16
 */
@Mapper
public interface InstitutionMapper
{
    /**
     * 查询机构信息详情管理
     *
     * @param id 机构信息详情管理主键
     * @return 机构信息详情管理
     */
    public Institution selectInstitutionById(Integer id);

    /**
     * 查询机构信息详情管理列表
     *
     * @param institution 机构信息详情管理
     * @return 机构信息详情管理集合
     */
    public List<Institution> selectInstitutionList(Institution institution);
    public List<Institution> selectByDesc();

    /**
     * 新增机构信息详情管理
     *
     * @param institution 机构信息详情管理
     * @return 结果
     */
    public int insertInstitution(Institution institution);

    /**
     * 修改机构信息详情管理
     *
     * @param institution 机构信息详情管理
     * @return 结果
     */
    public int updateInstitution(Institution institution);

    /**
     * 删除机构信息详情管理
     *
     * @param id 机构信息详情管理主键
     * @return 结果
     */
    public int deleteInstitutionById(Integer id);

    /**
     * 批量删除机构信息详情管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInstitutionByIds(Integer[] ids);

    int getMaxOrderId();
    Double getAverageScore(Integer institutionId);

    void updateOrderId(@Param("id") Integer id, @Param("orderId") Integer orderId);

    List<String> selectProvinceList();

    int update(Map<String, Object> map);


    Institution selectInstitutionByUserId(int intValue);


    int editUrl(@Param("url") String url, @Param("id") Integer id);
}
