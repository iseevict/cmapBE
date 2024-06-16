package practiceProject.cmap.domain.comment.service;

import practiceProject.cmap.domain.comment.dto.CommentParameterDTO;
import practiceProject.cmap.domain.comment.entity.Comment;

public interface CommentService {

    public Comment CommentWrite(CommentParameterDTO.CommentWriteParamDto param);
}
