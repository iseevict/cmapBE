package practiceProject.cmap.domain.board.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import practiceProject.cmap.domain.board.dto.BoardParameterDTO;
import practiceProject.cmap.domain.board.dto.BoardRequestDTO;
import practiceProject.cmap.domain.board.dto.BoardResponseDTO;

@Mapper
public interface BoardDtoConverter {

    BoardDtoConverter INSTANCE = Mappers.getMapper(BoardDtoConverter.class);

    BoardParameterDTO.BoardWriteParamDto toBoardWriteParamDto(BoardRequestDTO.BoardWriteRequestDto request);
    BoardParameterDTO.BoardModifyParamDto toBoardModifyParamDto(BoardRequestDTO.BoardModifyRequestDto request, Long boardId);
    BoardParameterDTO.BoardDeleteParamDto toBoardDeleteParamDto(BoardRequestDTO.BoardDeleteRequestDto request, Long boardId);
    BoardParameterDTO.BoardHeartOnParamDto toBoardHeartOnParamDto(BoardRequestDTO.BoardHeartOnRequestDto request, Long boardId);
}
