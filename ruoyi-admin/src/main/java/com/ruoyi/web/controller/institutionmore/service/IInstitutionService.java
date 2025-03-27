package com.ruoyi.web.controller.institutionmore.service;

import java.util.List;

import com.ruoyi.api.domain.dto.QualificationDto;
import com.ruoyi.web.controller.institutionmore.domain.Institution;
import org.springframework.web.multipart.MultipartFile;

/**
 * 机构信息详情管理Service接口
 *
 * @author ruoyi
 * @date 2024-12-16
 */
public interface IInstitutionService
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
     * 批量删除机构信息详情管理
     *
     * @param ids 需要删除的机构信息详情管理主键集合
     * @return 结果
     */
    public int deleteInstitutionByIds(Integer[] ids);

    /**
     * 删除机构信息详情管理信息
     *
     * @param id 机构信息详情管理主键
     * @return 结果
     */
    public int deleteInstitutionById(Integer id);

    public int getMaxOrderId();
    public Double getAverageScore(Integer institutionId);





    List<String> selectProvinceList();

    List<Institution> updateWithRank();

    void qualification(String phone, List<MultipartFile> files);


    int editUrl(String url, Integer id);
}
