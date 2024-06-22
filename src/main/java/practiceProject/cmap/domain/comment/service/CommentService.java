package practiceProject.cmap.domain.comment.service;

import org.springframework.data.domain.Slice;
import practiceProject.cmap.domain.comment.dto.CommentParameterDTO;
import practiceProject.cmap.domain.comment.entity.Comment;

public interface CommentService {

    public Comment CommentWrite(CommentParameterDTO.CommentWriteParamDto param);
    public Comment CommentModify(CommentParameterDTO.CommentModifyParamDto param);
    public void CommentDelete(CommentParameterDTO.CommentDeleteParamDto param);
    public Slice<Comment> BoardCommentList(CommentParameterDTO.BoardCommentListParamDto param);
}
