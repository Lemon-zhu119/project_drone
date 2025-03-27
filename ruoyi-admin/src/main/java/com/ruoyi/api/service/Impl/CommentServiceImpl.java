package com.ruoyi.api.service.Impl;

import com.ruoyi.api.domain.Course;
import com.ruoyi.api.domain.dto.CommentDto;
import com.ruoyi.api.domain.dto.CommentLike;
import com.ruoyi.api.domain.dto.CommentVo;
import com.ruoyi.api.mapper.CommentLikeMapper;
import com.ruoyi.api.mapper.CommentMapper;
import com.ruoyi.api.service.CommentService;
import com.ruoyi.common.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentLikeMapper commentLikeMapper;

    @Override
    public List<CommentDto> selectCommentList(Integer insId,Integer customerId) {
        List<CommentDto> commentDtos=commentMapper.selectCommentList(insId);
        for(CommentDto c:commentDtos) {
            String img = c.getCommentImg();
            List<String> imgs = new ArrayList<>();
            if (img != null && !img.isEmpty()) {
                StringTokenizer tokenizer = new StringTokenizer(img, ";");
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken().trim();
                    imgs.add(token);
                }
                c.setCommentImgs(imgs);
            }
        }
        for(CommentDto c:commentDtos) {
            String tag = c.getTag();
            List<String> tags = new ArrayList<>();
            if (tag != null && !tag.isEmpty()) {
                StringTokenizer tokenizer = new StringTokenizer(tag, ";");
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken().trim();
                    tags.add(token);
                }
                c.setTags(tags);
            }
        }
        return commentDtos.stream().map(com->{
            CommentVo vo=new CommentVo();
            BeanUtils.copyProperties(com,vo);
            boolean isLiked=commentLikeMapper.exitBycustomerIdAndCommentId(com.getId(),customerId);
            vo.setLiked(isLiked);
            return vo;
        }).collect(Collectors.toList());
    }

    private List<CommentVo> getCommentList(Integer customerId,Integer insId){
        List<CommentDto> list=commentMapper.selectCommentList(insId);
        return list.stream().map(com->{
            CommentVo vo=new CommentVo();
            BeanUtils.copyProperties(com,vo);
            boolean isLiked=commentLikeMapper.exitBycustomerIdAndCommentId(com.getId(),customerId);
            vo.setLiked(isLiked);
            return vo;
        }).collect(Collectors.toList());
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addcomment(CommentDto commentDto){
        processCommentImages(commentDto);
        processCommentTags(commentDto);
        return commentMapper.addcomment(commentDto);
    }

    @Override
    public boolean praise(Integer commentId, Integer customerId) {
        CommentDto comment=commentMapper.selectComentById(commentId);
        if(comment==null){
            throw new ServiceException("评论不存在");
        }
        Integer actually=commentLikeMapper.getActuallyCount(commentId);
        Integer commentAgreeNum=comment.getAgreeNum();
        if(commentAgreeNum==null||!actually.equals(commentAgreeNum)){
            commentMapper.updateAgreeNum(comment.getId(),actually);
        }

        CommentLike exitLike=commentLikeMapper.selectCommentAndCustomer(commentId,customerId);
        if(exitLike==null){
            CommentLike like=new CommentLike();
            like.setCommentId(commentId);
            like.setCustomerId(customerId);
            commentLikeMapper.insert(like);
            commentMapper.updateNum(commentId);
            return true;
        }else {
            commentLikeMapper.deleteById(exitLike.getId());
            commentMapper.decrementNum(commentId);
            return false;
        }

    }

    @Override
    public Integer deleteById(Integer commentId) {
        return commentMapper.delete(commentId);
    }

    private void processCommentImages(CommentDto commentDto){
        List<String> url=commentDto.getCommentImgs();
        if(url!=null){
            String imgs=String.join(";",url);
            commentDto.setCommentImg(imgs);
        }
    }
    private void processCommentTags(CommentDto commentDto){
        List<String> tags=new ArrayList<>();
        if(commentDto.getCoachScore()>=4){
            tags.add("教练态度好;");
        }else if(commentDto.getFeeScore()>=4){
            tags.add("收费透明;");
        }else if(commentDto.getServiceScore()>=4){
            tags.add("服务好");
        }
        if(!tags.isEmpty()){
            String tagList=String.join(";",tags);
            commentDto.setTag(tagList);
        }
    }
}
