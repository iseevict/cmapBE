package practiceProject.cmap.domain.board.service;

import jakarta.validation.Valid;
import practiceProject.cmap.domain.board.dto.BoardParameterDTO;
import practiceProject.cmap.domain.board.dto.BoardResponseDTO;
import practiceProject.cmap.domain.board.entity.Board;

public interface BoardService {

    public Board BoardWrite(@Valid BoardParameterDTO.BoardWriteParamDto param);
    public Board BoardModify(@Valid BoardParameterDTO.BoardModifyParamDto param);
    public void BoardDelete(@Valid BoardParameterDTO.BoardDeleteParamDto param);
}
