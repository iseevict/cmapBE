package practiceProject.cmap.domain.comment.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practiceProject.cmap.domain.comment.dto.CommentParameterDTO;
import practiceProject.cmap.domain.comment.dto.CommentRequestDTO;

@Mapper
public interface CommentDtoConverter {

    CommentDtoConverter INSTANCE = Mappers.getMapper(CommentDtoConverter.class);

    CommentParameterDTO.CommentWriteParamDto toCommentWriteParamDto(CommentRequestDTO.CommentWriteRequestDto request, Long boardId);
    CommentParameterDTO.CommentModifyParamDto toCommentModifyParamDto(CommentRequestDTO.CommentModifyRequestDto request, Long boardId, Long commentId);
    CommentParameterDTO.CommentDeleteParamDto toCommentDeleteParamDto(CommentRequestDTO.CommentDeleteRequestDto request, Long boardId, Long commentId);
    CommentParameterDTO.BoardCommentListParamDto toBoardCommentListParamDto(Long boardId, Integer page, Integer size);
}
