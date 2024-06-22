package practiceProject.cmap.domain.board.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import practiceProject.cmap.domain.board.dto.BoardDataDTO;
import practiceProject.cmap.domain.board.dto.BoardParameterDTO;
import practiceProject.cmap.domain.board.dto.BoardResponseDTO;
import practiceProject.cmap.domain.board.entity.Board;
import practiceProject.cmap.domain.board.entity.mapping.BoardHashtag;
import practiceProject.cmap.domain.member.entity.mapping.MemberLikeBoard;

import java.util.List;

public interface BoardService {

    public Board BoardWrite(@Valid BoardParameterDTO.BoardWriteParamDto param);
    public Board BoardModify(@Valid BoardParameterDTO.BoardModifyParamDto param);
    public void BoardDelete(@Valid BoardParameterDTO.BoardDeleteParamDto param);
    public MemberLikeBoard BoardHeartOn(@Valid BoardParameterDTO.BoardHeartOnParamDto param);
    public void BoardHeartOff(@Valid BoardParameterDTO.BoardHeartOffParamDto param);
    public List<Board> HomeRandomBoard();
    public Page<Board> BoardList(BoardParameterDTO.BoardListParamDto param);
    public List<BoardDataDTO.BoardDataDto> BoardTagList(Page<Board> boardPage);
    public Page<Board> BoardListByTag(BoardParameterDTO.BoardListByTagParamDto param);
}
